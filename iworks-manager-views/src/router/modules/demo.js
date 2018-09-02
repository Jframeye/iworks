/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/pages/layout/Layout'

const tableRouter = {
  path: '/demo',
  component: Layout,
  redirect: '/demo/demo1',
  name: 'demo',
  meta: {
    title: '示例组件',
    icon: 'demo',
    permission: 'sysuser'
  },
  children: [
    {
      path: 'demo1',
      component: () => import('@/views/demo.vue'),
      name: 'demo1',
      meta: { title: '示例', permission: 'sysuser' }
    },
    {
      path: 'demo2',
      component: () => import('@/views/demo.vue'),
      name: 'demo2',
      meta: { title: '示例', permission: 'sysuser' }
    },
    {
      path: 'demo3',
      component: () => import('@/views/demo.vue'),
      name: 'demo3',
      meta: { title: '示例', permission: 'sysuser' }
    },
    {
      path: 'demo4',
      component: () => import('@/views/demo.vue'),
      name: 'demo4',
      meta: { title: '示例', permission: 'sysuser' }
    },
    {
      path: 'demo5',
      component: () => import('@/views/demo.vue'),
      name: 'demo5',
      meta: { title: '示例', permission: 'sysuser' }
    },
    {
      path: 'demo6',
      component: () => import('@/views/demo.vue'),
      name: 'demo6',
      meta: { title: '示例', permission: 'sysuser' }
    },
    {
      path: 'demo7',
      component: () => import('@/views/demo.vue'),
      name: 'demo7',
      meta: { title: '示例', permission: 'sysuser' }
    },
    {
      path: 'demo8',
      component: () => import('@/views/demo.vue'),
      name: 'demo8',
      meta: { title: '示例', permission: 'sysuser' }
    },
    {
      path: 'demo9',
      component: () => import('@/views/demo.vue'),
      name: 'demo9',
      meta: { title: '示例', permission: 'sysuser' }
    }
  ]
}
export default tableRouter
