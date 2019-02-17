/**
 * 权限路由
 *
 * authentication: true 表示需要鉴权
 * 根据 name 是否匹配来判断是否有权限
 */
const routers = [
  {
    path: '/',
    component: () => import('@/pages/index.vue'),
    redirect: 'index',
    children: [
      {
        path: 'index',
        name: 'index',
        component: () => import('@/pages/index/index.vue')
      },
      {
        path: 'system',
        name: 'system',
        meta: {name: '系统管理', authentication: 'system'},
        component: resolve => require(['@/pages/system/index.vue'], resolve),
      },
      {
        path: 'permission',
        name: 'permission',
        meta: {name: '权限管理', authentication: 'permission'},
        component: resolve => require(['@/pages/demo/index.vue'], resolve),
      },
      {
        path: 'wxcard',
        name: 'wxcard',
        meta: {name: '微信卡券', authentication: 'wxcard'},
        component: resolve => require(['@/pages/demo/index.vue'], resolve),
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/pages/login/index.vue')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('@/pages/login/index.vue')
  },
  {
    path: '/password',
    name: 'password',
    component: () => import('@/pages/login/index.vue')
  },
  {
    path: '/401',
    name: '401',
    component: () => import('@/pages/error/401.vue')
  },
  {
    path: '/404',
    name: '404',
    component: () => import('@/pages/error/404.vue')
  },
  {
    path: '/*',
    name: '*',
    component: () => import('@/pages/error/404.vue')
  }
]


export default routers
