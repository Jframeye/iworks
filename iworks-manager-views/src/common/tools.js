/**
 * 生成随机数
 * @param {*} len 长度
 * @param {*} date 是否加上时间后缀
 */
export const randomNumber = (len, date) => {
    let random = '';
    random = Math.ceil(Math.random() * 100000000000000).toString().substr(0, len ? len : 4);
    if (date) random = random + Date.now();
    return random;
}

/**
 * 判断数组是否包含指定元素
 * @param {*} target 
 * @param {*} arr 
 */
export const contains = (target, arr) => {
    return arr.indexOf(target) !== -1
}

/**
 * 是否为空
 * @param {*} val 
 */
export function isBlank(val) {
    if (typeof val == 'boolean') {
        return false;
    }
    if (val instanceof Array) {
        if (val.length == 0) return true;
    } else if (val instanceof Object) {
        if (JSON.stringify(val) === '{}') return true;
    } else {
        if (val == 'null' || val == null || val == 'undefined' || val == undefined || val == '') return true;
        return false;
    }
    return false;
};
