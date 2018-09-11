import Vue from 'vue'
import Router from 'vue-router'

import store from '../store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar

import { hasPermission } from '@/store/modules/utils'
import { getToken } from '@/common/store'

Vue.use(Router)

/* Layout */
import Layout from '@/pages/layout/Layout'

/* Router Modules */
import commonRouterMap from './modules/common'
import recordRouterMap from './modules/record'
import demoRouterMap from './modules/demo'

/** note: submenu only apppear when children.length >= 1
 *  detail see  https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 **/

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    permission: permission_code     will control the page permission
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
    noCache: true                if true ,the page will no be cached(default is false)
  }
**/

/**
 * 通用路由
 */
export const commonRouter = commonRouterMap

/**
 * 需要权限的路由
 */
export const permissionRouter = [
  recordRouterMap,
  demoRouterMap
]

const router = new Router({
  mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: commonRouter
})

const whiteList = ['/login', '/authredirect']// no redirect whitelist

router.beforeEach((to, from, next) => {
  const token = getToken();
  NProgress.start() // start progress bar
  if (token) { // determine if there has token
    /* has token*/
    if (to.path === '/login') {
      next({ path: '/' })
      NProgress.done() // if current page is index will not trigger	afterEach hook, so manually handle it
    } else {
      if (store.getters.permissions.length === 0) { // 判断当前用户是否已拉取完user_info信息
        store.dispatch('GetUserInfo').then(result => { // 拉取user_info
          const permissions = result.permissions // note: permissions must be a array! such as: ['editor','develop']
          store.dispatch('GenerateRoutes', permissions).then(() => { // 根据roles权限生成可访问的路由表
            router.addRoutes(store.getters.addRouters) // 动态添加可访问路由表
            next({ ...to, replace: true }) // hack方法 确保addRoutes已完成 ,set the replace: true so the navigation will not leave a history record
          })
        }).catch((err) => {
          store.dispatch('FedLogout').then(() => {
            Message.error(err || 'Verification failed, please login again')
            next({ path: '/' })
          })
        })
      } else {
        // 没有动态改变权限的需求可直接next() 删除下方权限判断 ↓
        if (hasPermission(store.getters.permissions, to)) {
          next()
        } else {
          next({ path: '/401', replace: true, query: { noGoBack: true } })
        }
        // 可删 ↑
      }
    }
  } else {
    /* has no token*/
    if (whiteList.indexOf(to.path) !== -1) { // 在免登录白名单，直接进入
      next()
    } else {
      next('/login') // 否则全部重定向到登录页
      NProgress.done() // if current page is login will not trigger afterEach hook, so manually handle it
    }
  }
})

router.afterEach(() => {
  NProgress.done() // finish progress bar
})

export default router


