import Mock from 'mockjs';

/**
 * 用户登出
 */
Mock.mock('manager/authentication/login_out', (req, res) => {
  return {
    retcode: '0',
    message: 'success'
  }
});
/**
 * 账号密码登录
 */
Mock.mock('manager/authentication/login_by_username', (req, res) => {
  return {
    retcode: '0',
    data: 'user token',
    message: 'success'
  }
});
/**
 * 初始化数据
 */
Mock.mock('manager/authentication/init_userinfo_and_permission', (req, res) => {
  return {
    retcode: '0',
    data: {
      userInfo: {
        user_id: 1,
        username: 'xiaoye08050141@126.com',
        nickname: '叶宏梁',
        avatar: 'https://gitee.com/uploads/61/632261_smallweigit.jpg'
      },
      permissions: [
        {
          menu_type: 0,
          menu_code: 'system',
          menu_name: '系统管理',
          menu_icon: 'system',
          children: [
            {
              menu_type: 1, menu_code: 'system_menu', menu_name: '菜单管理', menu_icon: 'menu',
              children: [
                {menu_type: 2, menu_code: 'system_menu_insert', menu_name: '新增菜单', menu_icon: 'el-icon-plus'},
                {menu_type: 2, menu_code: 'system_menu_update', menu_name: '更新菜单', menu_icon: 'el-icon-edit'},
                {menu_type: 2, menu_code: 'system_menu_delete', menu_name: '删除菜单', menu_icon: 'el-icon-delete'}
              ]
            },
            {
              menu_type: 1, menu_code: 'system_dict', menu_name: '字典管理', menu_icon: 'dict',
              children: [
                {menu_type: 2, menu_code: 'system_dict_insert', menu_name: '新增', menu_icon: 'el-icon-plus'},
                {menu_type: 2, menu_code: 'system_dict_update', menu_name: '修改', menu_icon: 'el-icon-edit'},
                {menu_type: 2, menu_code: 'system_dict_delete', menu_name: '删除', menu_icon: 'el-icon-delete'},
              ]
            },
            {
              menu_type: 1, menu_code: 'system_para', menu_name: '参数设置', menu_icon: 'para',
              children: [
                {menu_type: 2, menu_code: 'system_para_insert', menu_name: '新增', menu_icon: 'el-icon-plus'}
              ]
            }
          ]
        },
        {
          menu_type: 0,
          menu_code: 'permission',
          menu_name: '权限管理',
          menu_icon: 'permission',
          children: [
            {
              menu_type: 1, menu_code: 'permission_menu', menu_name: '菜单权限', menu_icon: 'permission_menu',
              children: [
                {menu_type: 2, menu_code: 'permission_menu_setting', menu_name: '权限设置', menu_icon: 'permission_setting'}
              ]
            },
            {
              menu_type: 1, menu_code: 'permission_data', menu_name: '数据权限', menu_icon: 'permission_data',
              children: [
                {menu_type: 2, menu_code: 'permission_data_setting', menu_name: '权限设置', menu_icon: 'permission_setting'}
              ]
            }
          ]
        },
        {
          menu_type: 0,
          menu_code: 'wxcard',
          menu_name: '微信卡券',
          menu_icon: 'card',
          children: [
            {
              menu_type: 1, menu_code: 'wxcard_member', menu_name: '会员卡', menu_icon: 'wxcard'
            },
            {
              menu_type: 1, menu_code: 'wxcard_coupon', menu_name: '优惠券', menu_icon: 'wxcard'
            }
          ]
        }
      ]
    },
    message: 'success'
  }
});
