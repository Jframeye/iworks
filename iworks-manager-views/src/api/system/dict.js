import { get, post } from '../axios'

/**
 * 分页查询字典分类信息
 * @param p
 * @returns {*}
 */
export const listDictByPage = p => post('http://localhost:8080/api/basic/dict_constant/list', {})

/**
 * 分页查询字典分类数据信息
 * @param p
 * @returns {*}
 */
export const listDictDataByPage = p => post('http://localhost:8080/api/basic/dict_constant/list_dict', {})

/**
 * 新增字典分类信息
 * @param p
 * @returns {*}
 */
export const insertDict = p => post('http://localhost:8080/api/basic/dict_constant/insert', {})

/**
 * 新增字典分类数据信息
 * @param p
 * @returns {*}
 */
export const insertDictData = p => post('http://localhost:8080/api/basic/dict_constant/insert_dict', {})

/**
 * 修改字典分类数据信息
 * @param p
 * @returns {*}
 */
export const updateDict = p => post('http://localhost:8080/api/basic/dict_constant/update', {})

/**
 * 修改字典分类数据信息
 * @param p
 * @returns {*}
 */
export const updateDictData = p => post('http://localhost:8080/api/basic/dict_constant/update_dict', {})

/**
 * 删除字典分类信息
 * @param p
 * @returns {*}
 */
export const deleteDict = p => post('http://localhost:8080/api/basic/dict_constant/delete', {})

/**
 * 删除字典分类数据信息
 * @param p
 * @returns {*}
 */
export const deleteDictData = p => post('http://localhost:8080/api/basic/dict_constant/delete_dict', {})

/**
 * 查询字典分类信息
 * @param p
 * @returns {*}
 */
export const findDict = p => post('http://localhost:8080/api/basic/dict_constant/find', {})

/**
 * 查询字典分类数据信息
 * @param p
 * @returns {*}
 */
export const findDictData = p => post('http://localhost:8080/api/basic/dict_constant/find_dict', {})