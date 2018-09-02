import { get, post } from '../axios'

/**
 * 登录接口
 * @param p
 * @returns {*}
 */
export const loginByUsername = p => post('api/loginByUsername', p)

export const logout = () => get('api/logout', {})
