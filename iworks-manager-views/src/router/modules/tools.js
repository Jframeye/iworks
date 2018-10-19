/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/pages/layout/Layout'

const recordRouter = {
  path: '/tools',
  component: Layout,
  redirect: '/tools/generate',
  name: 'tools',
  meta: {
    title: '系统工具',
    icon: 'menu',
    alwaysShow: true,
    permission: 'sysuser'
  },
  children: [
    {
      path: 'generate',
      component: () => import('@/views/generate/index.vue'),
      name: 'generate',
      meta: { title: '代码生成器',icon: 'sub_menu', permission: 'sysuser' }
    }
  ]
}
export default recordRouter
