import * as storage from '@/utils/storage'

const getters = {
  routers: state => state.app.routers,
  userInfo: state => state.app.userInfo,
  permissions: state => {
    if (!state.app.permissions || state.app.permissions.length == 0) {
      state.app.permissions = storage.get('USER_PERMISSIONS')
    }
    return state.app.permissions
  }
}
export default getters
