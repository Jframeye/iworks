/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/pages/layout/Layout'

const tableRouter = {
  path: '/table',
  component: Layout,
  redirect: '/table/complex-table',
  name: 'table',
  meta: {
    title: 'Table',
    icon: 'table',
    permission: 'sysuser'
  },
  children: [
    {
      path: 'dynamic-table',
      component: () => import('@/views/demo.vue'),
      name: 'dynamicTable',
      meta: { title: 'dynamicTable', permission: 'sysuser' }
    },
    {
      path: 'drag-table',
      component: () => import('@/views/demo.vue'),
      name: 'dragTable',
      meta: { title: 'dragTable', permission: 'sysuser' }
    },
    {
      path: 'inline-edit-table',
      component: () => import('@/views/demo.vue'),
      name: 'inlineEditTable',
      meta: { title: 'inlineEditTable', permission: 'sysuser' }
    },
    {
      path: 'tree-table',
      component: () => import('@/views/demo.vue'),
      name: 'treeTableDemo',
      meta: { title: 'treeTable', permission: 'sysuser' }
    },
    {
      path: 'custom-tree-table',
      component: () => import('@/views/demo.vue'),
      name: 'customTreeTableDemo',
      meta: { title: 'customTreeTable', permission: 'sysuser' }
    },
    {
      path: 'complex-table',
      component: () => import('@/views/demo.vue'),
      name: 'complexTable',
      meta: { title: 'complexTable', permission: 'sysuser' }
    }
  ]
}
export default tableRouter
