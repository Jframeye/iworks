/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/pages/layout/Layout'

const recordRouter = {
  path: '/record',
  component: Layout,
  redirect: '/record/list',
  name: 'record',
  meta: {
    title: '随笔记录',
    icon: 'demo',
    permission: 'sysuser'
  },
  children: [
    {
      path: 'list',
      component: () => import('@/views/record/index.vue'),
      name: 'list',
      meta: { title: '记录列表', permission: 'sysuser' }
    },
    {
      path: 'update/:id(\\d+)',
      component: () => import('@/views/record/update.vue'),
      name: 'update',
      hidden: true,
      meta: { title: '修改记录', permission: 'sysuser' }
    },
    {
      path: 'insert',
      component: () => import('@/views/record/insert.vue'),
      name: 'insert',
      hidden: false,
      meta: { title: '新增记录', permission: 'sysuser' }
    }
  ]
}
export default recordRouter
