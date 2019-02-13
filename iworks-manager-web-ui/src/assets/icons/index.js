import Vue from 'vue'
import svgicon from '../../components/svgicon.vue'

//全局注册
Vue.component('svg-icon', svgicon)
const requireAll = requireContext => requireContext.keys().map(requireContext)
const req = require.context('./svg', false, /\.svg$/)
requireAll(req)
