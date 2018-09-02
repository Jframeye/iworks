import Vue from 'vue'
import svgicon from '@/components/svgicon'// svg组件

// register globally
Vue.component('svg-icon', svgicon)

const all = requireContext => requireContext.keys().map(requireContext)
const svg = require.context('./svg', false, /\.svg$/)
all(svg)