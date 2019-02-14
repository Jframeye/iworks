// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// 滚动条
import vuescroll from 'vuescroll';

// 全局样式
import './assets/css/common.css';
import './assets/font-awesome/css/font-awesome.min.css';
import animated from 'animate.css';

// svg 图标
import '@/assets/icons/index.js'

import App from './App'
import router from './router'

Vue.config.productionTip = false

Vue.use(ElementUI);
Vue.use(vuescroll); // install the vuescroll first
Vue.prototype.$vuescrollConfig = {
  bar: {
    background: '#86827f'
  }
};
Vue.use(animated);

new Vue({
  el: '#app',
  router,
  render: h => h(App)
});

