// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import iView from 'iview'

import 'iview/dist/styles/iview.css'
import 'static/icons/iconfont.css'

Vue.config.productionTip = false

// 模拟数据
process.env.NODE_ENV === 'development' ? require('@/mockjs/mockjs.js') : ''

Vue.use(iView)

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})