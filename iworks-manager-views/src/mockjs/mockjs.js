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

//export const listByPage = () => post('api/functions/record/listByPage', {})
Mock.mock('api/functions/record/listByPage', (req, res) => {
    return {
        code: 200,
        data: {
            total: 10,
            offset: 0,
            limit: 10,
            datas: [
                { title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' },
                { title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' },
                { title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' },
                { title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' },
                { title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' },
                { title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' },
                { title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' },
                { title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' },
                { title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' },
                { title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' }
            ]
        },
        msg: ''
    }
})

/**
 * 系统管理相关接口
 */

Mock.mock('api/system/user/list', (req, res) => {
    return {
        code: 200,
        data: {
            total: 10,
            offset: 0,
            limit: 10,
            datas: [
                { id: 1, title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' },
                { id: 2, title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' },
                { id: 3, title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' },
                { id: 4, title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' },
                { id: 5, title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' },
                { id: 6, title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' },
                { id: 7, title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' },
                { id: 8, title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' },
                { id: 9, title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' },
                { id: 10, title: 'aaa', author: 'yehl', state: '1', create_time: '2018-09-06 14:00:00' }
            ]
        },
        msg: ''
    }
});

Mock.mock('api/system/menu/list', (req, res) => {
    return {
        code: 200,
        data: {
            total: 10,
            offset: 0,
            limit: 10,
            datas: [
                { id: 1, title: '用户管理', order: 1, url: 'http://localhost:8080/#', type: 1, state: '1', permission: 'system:user:view', create_time: '2018-09-06 14:00:00',
                    children: [
                        {id: 11, title: '用户管理', order: 1, url: 'http://localhost:8080/#', type: 2, state: '1', permission: 'system:user:view', create_time: '2018-09-06 14:00:00' },
                        {id: 12, title: '用户管理', order: 2, url: 'http://localhost:8080/#', type: 2, state: '1', permission: 'system:user:view', create_time: '2018-09-06 14:00:00' },
                        {id: 13, title: '用户管理', order: 3, url: 'http://localhost:8080/#', type: 2, state: '1', permission: 'system:user:view', create_time: '2018-09-06 14:00:00' },
                        {id: 14, title: '用户管理', order: 4, url: 'http://localhost:8080/#', type: 2, state: '1', permission: 'system:user:view', create_time: '2018-09-06 14:00:00',
                            children: [
                                {id: 141, title: '用户管理', order: 1, url: 'http://localhost:8080/#', type: 3, state: '1', permission: 'system:user:view', create_time: '2018-09-06 14:00:00' },
                                {id: 142, title: '用户管理', order: 2, url: 'http://localhost:8080/#', type: 3, state: '1', permission: 'system:user:view', create_time: '2018-09-06 14:00:00' },
                                {id: 143, title: '用户管理', order: 3, url: 'http://localhost:8080/#', type: 3, state: '1', permission: 'system:user:view', create_time: '2018-09-06 14:00:00' }
                            ]
                        }
                    ] 
                },
                { id: 2, title: '用户管理', order: 2, url: 'http://localhost:8080/#', type: 1, state: '2', permission: 'system:user:view', create_time: '2018-09-06 14:00:00' },
                { id: 3, title: '用户管理', order: 3, url: 'http://localhost:8080/#', type: 1, state: '2', permission: 'system:user:view', create_time: '2018-09-06 14:00:00' },
                { id: 4, title: '用户管理', order: 4, url: 'http://localhost:8080/#', type: 1, state: '1', permission: 'system:user:view', create_time: '2018-09-06 14:00:00' },
                { id: 5, title: '用户管理', order: 5, url: 'http://localhost:8080/#', type: 1, state: '1', permission: 'system:user:view', create_time: '2018-09-06 14:00:00' },
                { id: 6, title: '用户管理', order: 6, url: 'http://localhost:8080/#', type: 1, state: '1', permission: 'system:user:view', create_time: '2018-09-06 14:00:00' },
                { id: 7, title: '用户管理', order: 7, url: 'http://localhost:8080/#', type: 1, state: '2', permission: 'system:user:view', create_time: '2018-09-06 14:00:00' },
                { id: 8, title: '用户管理', order: 8, url: 'http://localhost:8080/#', type: 1, state: '1', permission: 'system:user:view', create_time: '2018-09-06 14:00:00' },
                { id: 9, title: '用户管理', order: 9, url: 'http://localhost:8080/#', type: 1, state: '2', permission: 'system:user:view', create_time: '2018-09-06 14:00:00' },
                { id: 10, title: '用户管理', order: 10, url: 'http://localhost:8080/#', type: 1, state: '1', permission: 'system:user:view', create_time: '2018-09-06 14:00:00' }
            ]
        },
        msg: ''
    }
})