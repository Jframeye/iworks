import * as store from '@/common/store'
import { loginByUsername, logout } from '@/api/common/auth'
import { refreshCode } from '@/api/common/unauth'
import { getUserInfo } from '@/api/system/user'

const biz = {
  state: {
    userInfo: {},
    permissions: [],
  },

  mutations: {
    /** 设置 token */
    SET_TOKEN: (state, token) => {
      store.setToken(token) // 缓存到 cookie
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
     * 刷新验证码
     * @param {*} param0 
     */
    RefreshVerifyCode({ commit, state, dispatch }) {
      return new Promise((resolve, reject) => {
        refreshCode().then(result => {
          resolve(result);
        })
      })
    },
    /**
     * 根据账号登录
     * @param {*} param0 
     * @param {*} userInfo 
     */
    LoginByUsername({ commit, state, dispatch }, userInfo) {
      return new Promise((resolve, reject) => {
        loginByUsername(userInfo).then(result => {
          commit('SET_TOKEN', result); // 设置 token
          resolve();
        }).catch(err => {
          reject(err)
        })
      })
    },
    /**
     * 获取用户基本信息以及权限信息
     * @param {*} param0 
     */
    GetUserInfo({ commit, state, dispatch }) {
      return new Promise((resolve, reject) => {
        getUserInfo().then((result) => {
          commit('SET_USERIFNO', result.userInfo);
          commit('SET_PERMISSIONS', result.permissions);
          resolve(result);
        }).catch(err => {
          reject(err)
        })
      })
    },
    /**
     * 退出系统
     * @param {*} param0 
     */
    Logout({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '');
          commit('SET_USERIFNO', '');
          commit('SET_PERMISSIONS', []);
          store.removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    /**
     * 异常退出系统
     * @param {*} param0 
     */
    FedLogout({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        store.removeToken()
        resolve()
      })
    },
  }
}

export default biz
