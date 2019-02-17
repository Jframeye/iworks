// 引入所有的路由
import routers from '@/router/routers'
import * as storage from '@/utils/storage'

import * as authentication from '@/api/common/authentication'

const app = {
  state: {
    routers: routers,
    permissions: []
  },

  mutations: {
    /** 设置 token */
    SET_TOKEN: (state, token) => {
      storage.setToken(token) // 缓存到 cookie
    },
    /** 设置用户基本信息 */
    SET_USERIFNO: (state, userInfo) => {
      state.userInfo = userInfo;
    },
    /** 设置用户权限 */
    SET_PERMISSIONS: (state, permissions) => {
      state.permissions = [].concat(permissions);
    }
  },

  actions: {
    /**
     * 初始化数据[拉取用户信息,用户权限信息等]
     * @param commit
     * @param state
     * @param dispatch
     * @returns {Promise}
     * @constructor
     */
    Initialization_Data({ commit, state, dispatch }) {
      return new Promise((resolve, reject) => {
        authentication.init_userinfo_and_permission().then((result) => {
          commit('SET_USERIFNO', result.userInfo);
          commit('SET_PERMISSIONS', result.permissions);
          resolve();
        }).catch(err => {
          reject(err)
        })
      })
    },
    /**
     * 账号密码登录
     * @param commit
     * @param state
     * @param dispatch
     * @param params
     * @returns {Promise}
     * @constructor
     */
    Login_By_Username({ commit, state, dispatch }, params) {
      return new Promise((resolve, reject) => {
        authentication.login_by_username(params).then(result => {
          commit('SET_TOKEN', result); // 设置 token
          resolve();
        }).catch(err => {
          reject(err)
        })
      })
    },
    /**
     * 异常退出系统
     * @param commit
     * @returns {Promise}
     * @constructor
     */
    Fed_Logout({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        storage.setToken('')
        resolve()
      })
    },
  }
}

export default app
