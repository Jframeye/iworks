import { getUserInfo } from '@/api/system/user'
import { login, logout } from '@/api/common/common'
import { setToken, getToken, clean } from '@/api/store'

export default {
  state: {
    token: '',
    permission: [], // 权限数组
    userInfo: {} // 用户信息
  },
  mutations: {
    setUserInfo (state, userInfo) {
      state.userInfo = userInfo
    },
    setPermission (state, permission) {
      state.permission = permission
    },
    setToken (state, token) {
      state.token = token
      // setToken(token) // token 保存到Cookie中
    }
  },
  actions: {
    /**
     * 登录操作
     * @param commit
     * @param userName
     * @param password
     * @returns {Promise<any>}
     */
    handleLogin ({ commit }, {username, password}) {
      username = username.trim()
      return new Promise((resolve, reject) => {
        login({
          username,
          password
        }).then(result => {
          const data = result.data
          commit('setToken', data.token) // 保存token到vuex
          resolve()
        }).catch(err => {
          reject(err)
        })
      })
    },
    /**
     * 登出操作
     * @param state
     * @param commit
     * @returns {Promise<any>}
     */
    handleLogOut ({ state, commit }) {
      return new Promise((resolve, reject) => {
        logout().then(() => {
          cleanStore()
          resolve()
        }).catch(err => {
          reject(err)
        })
        // 如果你的退出登录无需请求接口，则可以直接使用下面三行代码而无需使用logout调用接口
        // commit('setToken', '')
        // commit('setAccess', [])
        // resolve()
      })
    },
    /**
     * 获取用户信息
     * @param state
     * @param commit
     * @returns {Promise<any>}
     */
    getUserInfo ({ state, commit }) {
      return new Promise((resolve, reject) => {
        getUserInfo().then(res => {
          const data = res.data
          commit('setUserInfo', data.userInfo)
          commit('setPermission', data.permission)
          resolve()
        }).catch(err => {
          reject(err)
        })
      })
    }
  }
}
