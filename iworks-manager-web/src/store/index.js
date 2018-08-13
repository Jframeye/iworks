import Vue from 'vue'
import Vuex from 'vuex'

import biz from './module/biz'
import app from './module/app'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    //
  },
  mutations: {
    //
  },
  actions: {
    //
  },
  modules: {
    biz,
    app
  }
})
