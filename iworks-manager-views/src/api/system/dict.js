import { get, post } from '../axios'

/**
 * 分页查询字典常量信息
 * @param p
 * @returns {*}
 */
export const listDictByPage = p => post('api/system/user/list', {})

/**
 * 新增字典常量信息
 * @param p
 * @returns {*}
 */
export const insertDict = p => post('api/system/user/insert', {})

/**
 * 修改字典常量信息
 * @param p
 * @returns {*}
 */
export const updateDict = p => post('api/system/user/update', {})

/**
 * 删除字典常量信息
 * @param p
 * @returns {*}
 */
export const deleteDict = p => post('api/system/user/delete', {})

/**
 * 查询字典常量信息
 * @param p
 * @returns {*}
 */
export const findDict = p => post('api/system/user/list', {})