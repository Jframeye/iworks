import { get, post } from '../axios'

/**
 * 分页查询系统常量信息
 * @param p
 * @returns {*}
 */
export const listConstantByPage = p => post('http://localhost:8080/api/basic/app_constant/list', p)

/**
 * 更新系统常量信息
 * @param p
 * @returns {*}
 */
export const updateConstant = p => post('http://localhost:8080/api/basic/app_constant/update', p)

/**
 * 删除系统常量信息
 * @param p
 * @returns {*}
 */
export const deleteConstant = p => post('http://localhost:8080/api/basic/app_constant/delete', p)

/**
 * 查询系统常量信息
 * @param p
 * @returns {*}
 */
export const findConstant = p => post('http://localhost:8080/api/basic/app_constant/find', p)