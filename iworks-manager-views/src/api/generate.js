import { get, post } from './axios'

/**
 * 获取文章列表
 * @param p
 * @returns {*}
 */
export const listByPage = () => post('api/functions/record/listByPage', {})
