import Vue from 'vue'
import 'es6-promise/auto'
import Vuex from 'vuex'

import app from './modules/app'
import getters from './getters'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app
  },
  getters
})

export default store
