import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: () => import('@/pages/index.vue'),
      redirect: 'system',
      children: [
        {
          path: 'system',
          name: 'system', // 菜单编码,用以权限控制
          component: () => import('@/pages/system/index.vue'),
          redirect: 'system/constant',
          children: [
            {
              path: 'menu',
              name: 'menu',
              component: () => import('@/pages/system/constant_list.vue')
            },
            {
              path: 'permission',
              name: 'permission',
              component: () => import('@/pages/system/constant_list.vue')
            },
            {
              path: 'constant',
              name: 'constant',
              component: () => import('@/pages/system/constant_list.vue')
            },
            {
              path: 'dict',
              name: 'dict',
              component: () => import('@/pages/system/constant_list.vue')
            },
            {
              path: 'logger',
              name: 'logger',
              component: () => import('@/pages/system/constant_list.vue')
            },
            {
              path: 'setting',
              name: 'setting',
              component: () => import('@/pages/system/constant_list.vue')
            }
          ]
        },
      ]
    }
  ]
})
