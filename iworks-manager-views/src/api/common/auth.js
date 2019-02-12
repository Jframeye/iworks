import { get, post } from '../axios'

/**
 * 登录接口
 * @param p
 * @returns {*}
 */
export const loginByUsername = p => post('http://localhost:8080/api/basic/user_login_info/loginByUsername', p)

/**
 * 登出接口
 */
export const logout = () => get('http://localhost:8080/api/basic/user_login_info/logout')
