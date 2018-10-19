import { get, post } from '../axios'

/**
 * 分页查询资源信息
 * @param p
 * @returns {*}
 */
export const listMenuByPage = p => post('api/system/menu/list', {})

/**
 * 更新资源信息
 * @param p
 * @returns {*}
 */
export const updateMenu = p => post('api/system/menu/update', {})

/**
 * 删除资源信息
 * @param p
 * @returns {*}
 */
export const deleteMenu = p => post('api/system/menu/delete', {})

/**
 * 查询资源信息
 * @param p
 * @returns {*}
 */
export const findMenu = p => post('api/system/menu/find', {})
