import { get, post } from '../axios'

/**
 * 获取用户信息
 * @param p
 * @returns {*}
 */
export const getUserInfo = () => post('api/system/usermenu/getUserInfo', {})

/**
 * 分页查询用户信息
 * @param p
 * @returns {*}
 */
export const listUserByPage = p => post('api/system/user/list', {})

/**
 * 新增用户信息
 * @param p
 * @returns {*}
 */
export const insertUser = p => post('api/system/user/insert', {})

/**
 * 修改用户信息
 * @param p
 * @returns {*}
 */
export const updateUser = p => post('api/system/user/update', {})

/**
 * 删除用户信息
 * @param p
 * @returns {*}
 */
export const deleteUser = p => post('api/system/user/delete', {})

/**
 * 查询用户信息
 * @param p
 * @returns {*}
 */
export const findUser = p => post('http://localhost:8080/api/basic/user_basic_info/find', {})
