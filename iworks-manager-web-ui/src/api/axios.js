/**
 * axios封装
 * 请求拦截、相应拦截、错误统一处理
 */
import axios from 'axios'
import router from '@/router'
import {getToken} from '@/utils/storage'
import {Message} from 'element-ui'

let loadinginstace; //load加载

// 环境的切换
// axios.defaults.baseURL = 'http://localhost:8080'

// 请求超时时间
axios.defaults.timeout = 10000

// post请求头
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'

// 请求拦截器
axios.interceptors.request.use(
  config => {
    // 每次发送请求之前判断是否存在token，
    // 如果存在，则统一在http请求的header都加上token，不用每次请求都手动添加了
    // 即使本地存在token，也有可能token是过期的，所以在响应拦截器中要对返回状态进行判断
    const token = getToken()
    token && (config.headers.Authorization = 'Bearer ' + token)
    return config
  },
  error => {
    return Promise.error(error)
  })

// 响应拦截器
axios.interceptors.response.use(
  response => {
    if (response.status === 200) { // 请求正常
      return Promise.resolve(response.data)
    } else {
      return Promise.reject(response)
    }
  },
  error => {
    return Promise.reject(error)
  }
)

/**
 * get方法，对应get请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function get(url, params) {
  return new Promise((resolve, reject) => {
    axios.get(url, params).then(response => {
      if (response.retcode == '0') {
        resolve(response.data)
      } else {
        reject(response.message)
      }
    }).catch(err => {
      reject(err || '请求异常，请联系开发人员')
    })
  })
}

/**
 * post方法，对应post请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function post(url, params) {
  return new Promise((resolve, reject) => {
    axios.post(url, params).then(response => {
        if (response.retcode == '0') {
          resolve(response.data)
        } else {
          reject(authentication(response))
        }
      }).catch(err => {
      reject(err || '请求异常，请联系开发人员')
    })
  })
}

/**
 * 登录失效处理
 * 登录过期处理
 * 无权限处理
 * @param permissions
 * @param route
 * @returns {boolean}
 */
const authentication = (response) => {
  /*switch (response.retcode) {
    // '': 登录失效处理
    // 登录失效, 跳转登录页面，并携带当前页面的路径
    // 在登录成功后返回当前页面，这一步需要在登录页操作。
    case '1':
      router.replace({
        path: '/login',
        query: { redirect: router.currentRoute.fullPath }
      })
      break
    // '' token过期
    // 登录过期对用户进行提示
    // 清除本地token和清空vuex中token对象
    // 跳转登录页面
    case '2':

      break
    case 404: // 404请求不存在
      Message.error('网络请求不存在')
      break
    default: // 其他错误，直接抛出错误提示
      Message.error(response.message)
  }*/
  return response.message
}
