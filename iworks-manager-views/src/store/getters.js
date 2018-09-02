const getters = {
    routers: state => state.app.routersMap,
    addRouters: state => state.app.addRouters,
    sidebar: state => state.app.sidebar,
    
    permissions: state => state.biz.permissions,
    userInfo: state => state.biz.userInfo,
}
export default getters