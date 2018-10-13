/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/pages/layout/Layout'

const recordRouter = {
  path: '/system',
  component: Layout,
  redirect: '/system/users',
  name: 'system',
  meta: {
    title: '系统管理',
    icon: 'menu',
    permission: 'sysuser'
  },
  children: [
    {
      path: 'users',
      component: () => import('@/views/system/user/index.vue'),
      name: 'users',
      meta: { title: '用户管理', icon: 'sub_menu', permission: 'sysuser' }
    },
    {
      path: 'users/detail/:id(\\d+)',
      component: () => import('@/views/system/user/detail.vue'),
      name: 'update',
      hidden: true,
      meta: { title: '用户信息', permission: 'sysuser' }
    },
    {
      path: 'users/insert',
      component: () => import('@/views/system/user/insert.vue'),
      name: 'update',
      hidden: true,
      meta: { title: '新增用户信息', permission: 'sysuser' }
    },
    {
      path: 'users/update/:id(\\d+)',
      component: () => import('@/views/system/user/update.vue'),
      name: 'update',
      hidden: true,
      meta: { title: '修改用户信息', permission: 'sysuser' }
    },
    {
      path: 'resources',
      component: () => import('@/views/record/update.vue'),
      name: 'resources',
      hidden: true,
      meta: { title: '资源管理', icon: 'sub_menu', permission: 'sysuser' }
    },
    {
      path: 'permissions',
      component: () => import('@/views/record/insert.vue'),
      name: 'permissions',
      hidden: false,
      meta: { title: '权限管理', icon: 'sub_menu', permission: 'sysuser' }
    },
    {
      path: 'dictionary',
      component: () => import('@/views/record/insert.vue'),
      name: 'dictionary',
      hidden: false,
      meta: { title: '字典管理', icon: 'sub_menu', permission: 'sysuser' }
    }
  ]
}
export default recordRouter
