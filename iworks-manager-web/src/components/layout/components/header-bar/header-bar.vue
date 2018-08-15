<template>
  <div class="header-bar">
    <a @click="handleCollpasedChange" type="text" :class="['sider-trigger-a', collapsed ? 'collapsed' : '']">
      <Icon type="navicon-round" size="26" />
    </a>
    <div class="custom-bread-crumb" style="margin-left: 30px;">
      <Breadcrumb style="{ fontSize: 14px }">
        <BreadcrumbItem v-for="item in breadCrumbList" :to="item.to" :key="`bread-crumb-${item.name}`">
          <common-icon style="margin-right: 4px;" :type="item.icon || ''"/>
          {{ title(item) }}
        </BreadcrumbItem>
      </Breadcrumb>
    </div>
    <div class="custom-content-con">
      <slot></slot>
      <div class="user-avator-dropdown">
        <Dropdown @on-click="handleClick">
          <Avatar :src="userInfo.userAvator"/>
          <Icon :size="12" type="arrow-down-b"></Icon>
          <DropdownMenu slot="list">
            <DropdownItem name="logout">退出登录</DropdownItem>
          </DropdownMenu>
        </Dropdown>
      </div>
    </div>
  </div>
</template>
<script>
import { mapActions } from 'vuex'
import CommonIcon from '../../../common-icon'
import customBreadCrumb from './custom-bread-crumb'
export default {
  name: 'HeaderBar',
  components: {
    CommonIcon,
    customBreadCrumb
  },
  props: {
    collapsed: Boolean
  },
  computed: {
    breadCrumbList () {
      return this.$store.state.app.breadCrumbList
    },
    userInfo () {
      return this.$store.state.biz.userInfo
    },
  },
  methods: {
    ...mapActions([
      'handleLogOut'
    ]),
    handleClick (name) {
      switch (name) {
        case 'logout':
          this.handleLogOut().then(() => {
            this.$router.push({
              name: 'login'
            })
          })
          break
      }
    },
    handleCollpasedChange () {
      this.$emit('on-collapsed-change', !this.collapsed)
    },
    title (item) {
      return (item.meta && item.meta.title) || item.name
    },
  }
}
</script>
<style lang="less">
@import './header-bar.less'
</style>
