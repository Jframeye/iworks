import { get, post } from '../axios'

/**
 * 用户登出
 */
export const login_out = () => get('manager/authentication/login_by_username', {})

/**
 * 账号密码登录
 * @param p
 */
export const login_by_username = p => post('manager/authentication/login_by_username', p)

/**
 * 初始化数据
 */
export const init_userinfo_and_permission = () => post('manager/authentication/init_userinfo_and_permission', {})
