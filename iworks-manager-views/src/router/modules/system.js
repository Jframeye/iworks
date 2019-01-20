/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/pages/layout/Layout'

const recordRouter = {
  path: '/system',
  component: Layout,
  redirect: '/system/constant',
  name: 'system',
  meta: {
    title: '系统管理',
    icon: 'menu',
    permission: 'sysuser'
  },
  children: [
    {
      path: 'constant',
      component: () => import('@/views/system/constant/index.vue'),
      name: 'constant',
      meta: { title: '系统常量', icon: 'sub_menu', permission: 'sysuser' }
    },
    {
      path: 'dictionary',
      component: () => import('@/views/system/dictionary/index.vue'),
      name: 'dictionary',
      meta: { title: '字典常量', icon: 'sub_menu', permission: 'sysuser' }
    },
    {
      path: 'user',
      component: () => import('@/views/system/user/index.vue'),
      name: 'user',
      meta: { title: '用户管理', icon: 'sub_menu', permission: 'sysuser' }
    },
    {
      path: 'user/detail/:id(\\d+)',
      component: () => import('@/views/system/user/detail.vue'),
      name: 'user_detail',
      hidden: true,
      meta: { title: '用户详情', icon: 'sub_menu', permission: 'sysuser' }
    },
    {
      path: 'user/insert',
      component: () => import('@/views/system/user/insert.vue'),
      name: 'user_insert',
      hidden: true,
      meta: { title: '新增用户', icon: 'sub_menu', permission: 'sysuser' }
    },
    {
      path: 'user/update/:id(\\d+)',
      component: () => import('@/views/system/user/update.vue'),
      name: 'user_update',
      hidden: true,
      meta: { title: '修改用户', icon: 'sub_menu', permission: 'sysuser' }
    },

    {
      path: 'menu',
      component: () => import('@/views/system/menu/index.vue'),
      name: 'menu',
      meta: { title: '菜单管理', icon: 'sub_menu', permission: 'sysuser' }
    },
    {
      path: 'menu/detail/:id(\\d+)',
      component: () => import('@/views/system/menu/detail.vue'),
      name: 'menu_detail',
      hidden: true,
      meta: { title: '用户详情', icon: 'sub_menu', permission: 'sysuser' }
    },
    {
      path: 'menu/insert',
      component: () => import('@/views/system/menu/insert.vue'),
      name: 'menu_insert',
      hidden: true,
      meta: { title: '新增用户', icon: 'sub_menu', permission: 'sysuser' }
    },
    {
      path: 'menu/update/:id(\\d+)',
      component: () => import('@/views/system/menu/update.vue'),
      name: 'menu_update',
      hidden: true,
      meta: { title: '修改用户', icon: 'sub_menu', permission: 'sysuser' }
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
