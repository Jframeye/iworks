import { contains } from './tools'

export const loadStyle = url => {
  const link = document.createElement('link');
  link.type = 'text/css';
  link.rel = 'stylesheet';
  link.href = url;
  const head = document.getElementsByTagName('head')[0];
  head.appendChild(link);
}

/**
 * 获取路由菜单信息
 * @param {*} list 
 * @param {*} access 
 */
export const getMenuByRouter = (list, root, permissions) => {
  let menus = [];
  list.forEach(function (item, i) {
    if (item.meta && !item.meta.hideInMenu) {
      let path = item.path;
      if (path.indexOf('/') !== -1) {
        path = root + path
      } else {
        path = root + '/' + item.path
      }
      let menu = {
        path: path,
        name: item.name,
        icon: (item.meta && item.meta.icon) || '',
        title: (item.meta && item.meta.title) || ''
      }
      if (hasChildren(item)) {
        menu.children = getMenuByRouter(item.children, menu.path, permissions)
      }
      if (hasPermission(item, permissions)) menus.push(menu)
    }
  })
  return menus
}
const hasChildren = (item) => {
  return item.children && item.children.length !== 0
}
const hasPermission = (item, permissions) => {
  if (item.meta && item.meta.menu_code && item.meta.menu_code.length) {
    if (contains(item.meta.menu_code, permissions)) return true
    else return false
  } else return true
}

export const permission = (item, permissions, routes) => {
  let permission = true
  routes.forEach(route => {
    if (item.path === route.path && !contains(route.meta.menu_code, permissions)) {
      permission = false
    }
  })
  return permission
}

/**
 * 路径转换
 * @param {*} $route 
 */
export const resolvePath = ($route) => {
  let path = "";
  if ($route.query.redirect) {
    path = $route.query.redirect;
  } else {
    path = $route.path;
  }
  return path;
}