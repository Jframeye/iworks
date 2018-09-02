<template>
  <el-menu class="navbar" mode="horizontal">
    <hamburger class="hamburger-container" :toggleClick="toggleSidebar" :isActive="sidebar.opened"></hamburger>
    <breadcrumb class="breadcrumb-container"></breadcrumb>
    <div class="right-menu">
      <screenfull class="screenfull right-menu-item"></screenfull>
      
      <el-dropdown class="avatar-container right-menu-item" trigger="click">
        <div class="avatar-wrapper">
          <img class="user-avatar" :src="userInfo.avatar">
          <div class="user-info">
            <span>叶宏梁</span>
            <i class="el-icon-caret-bottom"></i>
          </div>
        </div>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/">
            <el-dropdown-item>
              首页
            </el-dropdown-item>
          </router-link>
          <a target='_blank' href="https://github.com/PanJiaChen/vue-element-admin/">
            <el-dropdown-item>
              Githup
            </el-dropdown-item>
          </a>
          <el-dropdown-item divided>
            <span @click="logout" style="display:block;">退出</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </el-menu>
</template>

<script>
import { mapGetters } from "vuex";
import Hamburger from "./hamburger";
import Breadcrumb from "./breadcrumb";
import Screenfull from "./screenfull";

export default {
  name: "headbar",
  components: {
    Breadcrumb,
    Hamburger,
    Screenfull
  },
  computed: {
    ...mapGetters(["sidebar", "userInfo"])
  },
  methods: {
    toggleSidebar() {
      this.$store.dispatch("ToggleSidebar");
    },
    logout() {
      this.$store.dispatch("Logout").then(() => {
        location.reload(); // In order to re-instantiate the vue-router object to avoid bugs
      });
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.navbar {
  height: 50px;
  line-height: 50px;
  border-radius: 0px !important;
  .hamburger-container {
    line-height: 58px;
    height: 50px;
    float: left;
    padding: 0 10px;
  }
  .breadcrumb-container {
    float: left;
  }
  .errLog-container {
    display: inline-block;
    vertical-align: top;
  }
  .right-menu {
    float: right;
    height: 100%;
    display: flex;
    &:focus {
      outline: none;
    }
    .right-menu-item {
      margin: 0 8px;
    }
    .screenfull {
      height: 20px;
      margin-top: 15px;
    }
    .international {
      vertical-align: top;
    }
    .theme-switch {
      vertical-align: 15px;
    }
    .avatar-container {
      height: 50px;
      margin-right: 15px;
      .avatar-wrapper {
        cursor: pointer;
        display: flex;
        .user-avatar {
          width: 25px;
          height: 25px;
          border-radius: 50%;
          margin-top: 12px;
          margin-right: 5px;
        }
        .user-info {
          .el-icon-caret-bottom {
            font-size: 12px;
          }
        }
      }
    }
  }
}
</style>
