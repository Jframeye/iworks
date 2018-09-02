import { get, post } from '../axios'

/**
 * 刷新验证码
 * @param p
 * @returns {*}
 */
export const refreshCode = p => post('api/refreshCode', p)