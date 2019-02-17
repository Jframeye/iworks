import Cookies from 'js-cookie'
import { isBlank } from './tools'

/**
 * 设置 token 到 cookie 中, 不传值,则删除 cookie 中的 token
 * @param token
 * @returns {*}
 */
export const setToken = (token) => {
  Cookies.remove('TOKEN_KEY'); // 刪除 token
  if (token) {
    Cookies.set('TOKEN_KEY', token, {expires: 30}) // cookie 30分钟有效
    return token
  }
  return '';
}

/**
 * 从 cookie 中获取 token
 * @returns {*}
 */
export const getToken = () => {
  return Cookies.get('TOKEN_KEY')
}

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
 * 删除本地缓存
 * @param {*} name
 */
const remove = (name) => {
  window.localStorage.removeItem(name);
}
