import { get, post } from '../axios'

/**
 * 获取用户信息
 * @param p
 * @returns {*}
 */
export const getUserInfo = () => post('api/system/usermenu/getUserInfo', {})
