import Mock from 'mockjs'

/**
 * 登录获取 token
 */
Mock.mock('api/login', (req, res) => {
    return {
        code: 200,
        data: {
            token: 'admin'
        },
        msg: ''
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
                avator: 'https://avatars0.githubusercontent.com/u/20942571?s=460&v=4'
            },
            permission: []
        },
        msg: ''
    }
})

