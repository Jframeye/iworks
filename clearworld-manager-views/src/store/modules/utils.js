/**
 * 递归过滤权限路由表，返回有权限的路由表
 * @param routerMap
 * @param permissions
 */
export const filterRouters = (routerMap, permissions) => {
    const routers = routerMap.filter(route => {
        if (hasPermission(permissions, route)) {
            if (route.children && route.children.length) {
                route.children = filterRouters(route.children, permissions)
            }
            return true
        }
        return false
    })
    return routers
}

/**
 * 通过 meta.permission 判断是否与当前用户权限匹配
 * @param permissions
 * @param route
 */
export const hasPermission = (permissions, route) => {
    if (route.meta && route.meta.permission) {
        return permissions.indexOf(route.meta.permission) !== -1
    } else {
        return true
    }
}