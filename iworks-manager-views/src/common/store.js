import Cookies from 'js-cookie'
import { isBlank } from './tools'

const TOKEN_KEY = 'TOKEN_KEY'
export const setToken = (token) => {
  if (token) {
    Cookies.set(TOKEN_KEY, token, { expires: 30 }) // cookie 30分钟有效
    return token
  }
  Cookies.remove(TOKEN_KEY);
  return '';
}
export const getToken = () => {
  return Cookies.get(TOKEN_KEY)
}
export const removeToken = () => {
  return Cookies.remove(TOKEN_KEY)
}

const USER_KEY = 'USER_KEY'
export const userInfoSet = (userInfo) => {
  set(USER_KEY, userInfo, false);
  return userInfo
}
export const userInfoGet = () => {
  let userInfo = get(USER_KEY);
  if (undefined == userInfo) return {}
  return userInfo;
}
export const userInfoRemove = () => {
  remove(USER_KEY)
}

const PERMISSION_KEY = 'PERMISSION_KEY'
export const permissionSet = (permissions) => {
  set(PERMISSION_KEY, permissions, false);
  return permissions
}
export const permissionGet = () => {
  let permissions = get(PERMISSION_KEY);
  if (undefined == permissions) return []
  return permissions;
}
export const permissionRemove = () => {
  remove(PERMISSION_KEY);
}

const MENU_KEY = 'MENU_KEY'
export const menuSet = (menus) => {
  set(MENU_KEY, menus, false);
  return menus
}
export const menuGet = () => {
  let menus = get(MENU_KEY);
  if (undefined == menus) return []
  return menus;
}
export const menuRemove = () => {
  remove(MENU_KEY)
}

const SCREEN_LOCKED_KEY = 'SCREEN_LOCKED_KEY'
export const screenlockedSet = (locked) => {
  set(SCREEN_LOCKED_KEY, locked, false);
  return locked
}
export const screenlockedGet = () => {
  let locked = get(SCREEN_LOCKED_KEY);
  if (undefined == locked) return false
  return locked;
}
export const screenlockedRemove = () => {
  remove(SCREEN_LOCKED_KEY);
}

const PASSWORD_KEY = 'PASSWORD_KEY'
export const passwordSet = (password) => {
  set(PASSWORD_KEY, password, false);
  return password
}
export const passwordGet = () => {
  let password = get(PASSWORD_KEY);
  if (undefined == password) return ''
  return password;
}
export const passwordRemove = () => {
  remove(PASSWORD_KEY);
}

const ACTIVITYTAG_KEY = 'ACTIVITYTAG_KEY'
export const activitytagSet = (route) => {
  const activitytag = {
    path: route.path,
    title: route.meta.title
  }
  set(ACTIVITYTAG_KEY, activitytag, false);
  return activitytag
}
export const activitytagGet = () => {
  let activitytag = get(ACTIVITYTAG_KEY);
  if (undefined == activitytag) return {}
  return activitytag;
}
export const activitytagRemove = () => {
  remove(ACTIVITYTAG_KEY);
}

const NAVTAGS_KEY = 'NAVTAGS_KEY'
export const navtagsSet = (navtags) => {
  set(NAVTAGS_KEY, navtags, false);
  return navtags
}
export const navtagsGet = () => {
  let navtags = get(NAVTAGS_KEY);
  if (undefined == navtags) return []
  return navtags;
}
export const navtagsRemove = () => {
  remove(NAVTAGS_KEY);
}

export const sidebar_status = "sidebar_status";

/**
 * 设置本地缓存
 * @param {*} name 
 * @param {*} data 
 */
export const set = (name, data) => {
  if (data) {
    window.localStorage.setItem(name, JSON.stringify(data));
  } else {
    remove(name);
  }
}

/**
 * 获取本地缓存
 * @param {*} name 
 */
export const get = (name) => {
  const data = window.localStorage.getItem(name);
  if (isBlank(data)) return '';
  return JSON.parse(data);
}

/**
 * 清空本地缓存
 */
export const clear = () => {
  removeToken();
  window.localStorage.clear();
}

/**
 * 删除本地缓存
 * @param {*} name 
 */
const remove = (name) => {
  window.localStorage.removeItem(name);
}