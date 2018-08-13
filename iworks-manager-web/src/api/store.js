import store from '@/store/index'
import Cookies from 'js-cookie'
import { contains, forEach } from './utils'

export const TOKEN_KEY = 'token'

/**
 * 登出或登录失效时清除必要数据
 */
export const cleanStore = () => {
  Cookies.remove(TOKEN_KEY) // 清除 cookie 中的数据
  store.commit('setToken', '') // 清除 token
}

/**
 * 设置 token 到 cookie
 * @param token
 */
export const setToken = (token) => {
  // Cookies.set(TOKEN_KEY, token, {expires: config.cookieExpires || 1})
}

/**
 * 获取 token
 * @returns {*}
 */
export const getToken = () => {
  let token = Cookies.get(TOKEN_KEY)
  if (token) {
    return token
  } else {
    token = store.state.biz.token
    if (token) {
      return token
    }
    return false
  }
}

/**
 * @param {*} name 即将跳转的路由name
 * @param {*} access 用户权限数组
 * @param {*} routes 路由列表
 * @description 用户是否可跳转到该页
 */
export const canTurnTo = (name, permission, routes) => {
  const permissionRouteNames = (list) => {
    let res = []
    list.forEach(item => {
      if (item.children && item.children.length) {
        res = [].concat(res, permissionRouteNames(item.children))
      } else {
        if (item.meta && item.meta.permission) {
          if (hasPermission(permission, item)) res.push(item.name)
        } else {
          res.push(item.name)
        }
      }
    })
    return res
  }
  const canTurnToNames = permissionRouteNames(routes)
  return canTurnToNames.indexOf(name) > -1
}

/**
 * @param {*} access 用户权限数组，如 ['super_admin', 'admin']
 * @param {*} route 路由列表
 */
const hasPermission = (permission, route) => {
  if (route.meta && route.meta.permission) return contains(permission, route.meta.permission)
  else return true
}

/**
 * @param {Array} list 通过路由列表得到菜单列表
 * @returns {Array}
 */
export const getMenuByRouter = (list, permission) => {
  let res = []
  forEach(list, item => {
    if (!item.meta || (item.meta && !item.meta.hideInMenu)) {
      let obj = {
        icon: (item.meta && item.meta.icon) || '',
        name: item.name,
        meta: item.meta
      }
      if ((hasChildren(item) || (item.meta && item.meta.showAlways)) && showThisMenuOrNot(item, permission)) {
        obj.children = getMenuByRouter(item.children, permission)
      }
      if (item.meta && item.meta.href) obj.href = item.meta.href
      if (showThisMenuOrNot(item, permission)) res.push(obj)
    }
  })
  return res
}

/**
 * 是否有子菜单
 * @param {*} item 
 */
export const hasChildren = (item) => {
  return item.children && item.children.length !== 0
}

/**
 * 是否显示菜单
 * @param {*} item 
 * @param {*} permission 
 */
const showThisMenuOrNot = (item, permission) => {
  if (item.meta && item.meta.permission && item.meta.permission.length) {
    if (contains(item.meta.permission, permission)) {
      return true
    } else {
      return false
    }
  } else {
    return true
  }
}
