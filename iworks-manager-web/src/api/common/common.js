import { get, post } from '../axios'

/**
 * 登录接口
 * @param p
 * @returns {*}
 */
export const login = p => post('api/login', p)

export const logout = () => get('api/logout', {})
