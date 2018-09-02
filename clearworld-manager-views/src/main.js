// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

// 加载图标
import Icon from 'vue2-svg-icon/Icon.vue'

import '@/styles/index.scss' // global css

// 模拟数据
require('@/mockjs/mockjs.js')

Vue.config.productionTip = false

Vue.use(Element, {
  size: 'medium'
})

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
