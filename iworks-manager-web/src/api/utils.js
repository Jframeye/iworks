/**
 * @param {Array} target 目标数组
 * @param {Array} arr 需要查询的数组
 * @description 判断要查询的数组是否至少有一个元素包含在目标数组中
 */
export const contains = (target, arr) => {
  return target.some(_ => arr.indexOf(_) > -1)
}

/**
 * 循环数组
 * @param {*} arr 
 * @param {*} fn 
 */
export const forEach = (arr, fn) => {
  if (!arr.length || !fn) return
  let i = -1
  let len = arr.length
  while (++i < len) {
    let item = arr[i]
    fn(item, i, arr)
  }
}