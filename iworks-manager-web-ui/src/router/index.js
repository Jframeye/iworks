import Vue from 'vue'
import Router from 'vue-router'
import store from '../store'

import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

import { Message } from 'element-ui'

import routers from './routers'

import { getToken } from '@/utils/storage'

Vue.use(Router)

const router = new Router({
  mode: 'history', // require service support
  scrollBehavior: () => ({y: 0}),
  routes: routers
})

const white_routers = ['/login', '/register', '/password'] // 无需登录校验的请求

router.beforeEach((to, from, next) => {
  NProgress.start() // start progress bar
  // 判断是否已登录,通过token判断
  let token = getToken(); // 获取token
  if (token) {
    if (to.path === '/login') { // 如果是跳转登录页面,直接重定向到首页
      next({path: '/'})
      NProgress.done();
    } else {
      if (store.getters.permissions.length === 0) { // 如果没有权限信息,则重新拉取
        store.dispatch('Initialization_Data').catch((err) => { // 是否拉取失败
          store.dispatch('Fed_Logout').then(() => { // 清空登录信息,重新登录
            Message.error('数据初始化失败,请重新登录')
            next({path: '/login'})
          })
        })
      }
      // 鉴权: 判断是否有访问权限
      if (authentication(store.getters.permissions, to)) {
        next()
      } else {
        next({path: '/401', replace: true, query: {noGoBack: true}})
      }
    }
  } else {
    debugger
    if (white_routers.indexOf(to.path) !== -1) { // 在免登录白名单，直接进入
      next()
    } else {
      next('/login') // 否则全部重定向到登录页
      NProgress.done();
    }
  }
})

router.afterEach(() => {
  NProgress.done()
})

/**
 * 鉴权
 * @param permissions
 * @param route
 * @returns {boolean}
 */
const authentication = (permissions, route) => {
  if (route.meta && route.meta.authentication) {
    if(permissions) {
      return check(permissions, route.meta.authentication)
    } else {
      return false
    }
  } else {
    return true
  }
}

/**
 * 校验是否有权限[此处只分析一级菜单]
 * @param permissions
 * @param permission
 * @returns {boolean}
 */
const check = (permissions, permission) => {
  for(var i=0; i < permissions.length ; i++) {
    if(permissions[i].menu_code == permission) {
      return true
    }
  }
  return false
}

export default router
