import Cookies from 'js-cookie'
import { isBlank } from './tools'
import { aes_encrypt, aes_decrypt} from '@/utils/crypto'

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
    window.sessionStorage.setItem(name, aes_encrypt(data));
  } else {
    remove(name);
  }
}

/**
 * 获取本地缓存
 * @param {*} name
 */
export const get = (name) => {
  const data = window.sessionStorage.getItem(name);
  if (isBlank(data)) return '';
  return JSON.parse(aes_decrypt(data));
}

/**
 * 删除本地缓存
 * @param {*} name
 */
const remove = (name) => {
  window.sessionStorage.removeItem(name);
}
