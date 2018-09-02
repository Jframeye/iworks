import Layout from '@/pages/layout/Layout'

const commonRouterMap = [
  {
    path: '/login',
    component: () => import('@/pages/login/index'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/pages/error/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/pages/error/401'),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: 'index',
    children: [
      {
        path: 'index',
        component: () => import('@/views/index/index'),
        name: 'index',
        meta: { title: '首页', icon: 'index', noCache: true, unclose: true }
      }
    ]
  }
]
export default commonRouterMap