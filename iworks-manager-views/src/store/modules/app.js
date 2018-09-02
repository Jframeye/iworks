import * as store from '@/common/store'
import { filterRouters } from './utils'
import { permissionRouter, commonRouter } from '@/router'

const app = {
  state: {
    routersMap: commonRouter,
    addRouters: [],
    sidebar: {
      opened: store.get(store.sidebar_status) ? true : false,
      withoutAnimation: false
    },
    visitedViews: [],
    cachedViews: [],
  },

  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers
      state.routersMap = commonRouter.concat(routers)
    },
    TOGGLE_SIDEBAR: state => {
      if (state.sidebar.opened) {
        store.set(store.sidebar_status, false)
      } else {
        store.set(store.sidebar_status, true)
      }
      state.sidebar.opened = !state.sidebar.opened
      state.sidebar.withoutAnimation = false
    },
    CLOSE_SIDEBAR: (state, withoutAnimation) => {
      store.set(store.sidebar_status, true)
      state.sidebar.opened = false
      state.sidebar.withoutAnimation = withoutAnimation
    },
    ADD_VISITED_VIEWS: (state, view) => {
      if (state.visitedViews.some(v => v.path === view.path)) return
      state.visitedViews.push(
        Object.assign({}, view, {
          title: view.meta.title || 'no-name',
          path: view.path,
          close: !view.meta.unclose
        })
      )
      // 如果首页不在，则加上
      if (!state.visitedViews.some(v => v.path === "/index")) {
        state.visitedViews.unshift(
          {
            title: '首页',
            path: '/index',
            close: false
          }
        );
      }
      if (!view.meta.noCache) {
        state.cachedViews.push(view.name)
      }
    },
    DEL_VISITED_VIEWS: (state, view) => {
      for (const [i, v] of state.visitedViews.entries()) {
        if (v.path === view.path && v.path != '/index') {
          state.visitedViews.splice(i, 1)
          break
        }
      }
      for (const i of state.cachedViews) {
        if (i === view.name) {
          const index = state.cachedViews.indexOf(i)
          state.cachedViews.splice(index, 1)
          break
        }
      }
    },
    DEL_OTHERS_VIEWS: (state, view) => {
      for (const [i, v] of state.visitedViews.entries()) {
        if (v.path === view.path) {
          state.visitedViews = state.visitedViews.slice(i, i + 1)
          break
        }
      }
      // 如果首页不在，则加上
      if (!state.visitedViews.some(v => v.path === "/index")) {
        state.visitedViews.unshift(
          {
            title: '首页',
            path: '/index',
            close: false
          }
        );
      }
      for (const i of state.cachedViews) {
        if (i === view.name) {
          const index = state.cachedViews.indexOf(i)
          state.cachedViews = state.cachedViews.slice(index, index + 1)
          break
        }
      }
    },
    DEL_ALL_VIEWS: state => {
      state.visitedViews = []
      state.cachedViews = []
    },
    TOGGLE_DEVICE: (state, device) => {
      state.device = device
    },
  },

  actions: {
    GenerateRoutes({ commit }, permissions) {
      return new Promise(resolve => {
        const accessedRouters = filterRouters(permissionRouter, permissions)
        commit('SET_ROUTERS', accessedRouters)
        resolve()
      })
    },
    ToggleSidebar({ commit }) {
      commit('TOGGLE_SIDEBAR')
    },
    AddVisitedViews({ commit }, view) {
      commit('ADD_VISITED_VIEWS', view)
    },
    DelVisitedViews({ commit, state }, view) {
      return new Promise(resolve => {
        commit('DEL_VISITED_VIEWS', view)
        resolve([...state.visitedViews])
      })
    },
    DelOthersViews({ commit, state }, view) {
      return new Promise(resolve => {
        commit('DEL_OTHERS_VIEWS', view)
        resolve([...state.visitedViews])
      })
    },
    DelAllViews({ commit, state }) {
      return new Promise(resolve => {
        commit('DEL_ALL_VIEWS')
        resolve([...state.visitedViews])
      })
    },
    ToggleDevice({ commit }, device) {
      commit('TOGGLE_DEVICE', device)
    },
  }
}

export default app
