import Mock from 'mockjs'
/**
 * 登录获取 token
 */
Mock.mock('api/loginByUsername', (req, res) => {
    return {
        code: 200,
        data: 'token'
    }
})
Mock.mock('api/logout', (req, res) => {
    return {
        code: 200,
        data: null
    }
})

/**
 * 获取用户基本信息以及权限信息
 */
Mock.mock('api/system/usermenu/getUserInfo', (req, res) => {
    return {
        code: 200,
        data: {
            userInfo: {
                user_id: 1,
                username: 'admin',
                nickname: '叶宏梁',
                avatar: 'https://gitee.com/uploads/61/632261_smallweigit.jpg'
            },
            permissions: ['system','sysuser','sysmenu', 'permission', 'dictionary', 'website', 'zlgedu']
        },
        msg: ''
    }
})
