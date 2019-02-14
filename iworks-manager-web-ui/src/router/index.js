import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: () => import('@/pages/index.vue'),
      redirect: 'system',
      children: [
        { path: 'system', component: () => import('@/pages/system/index.vue') },
      ]
    }
  ]
})
