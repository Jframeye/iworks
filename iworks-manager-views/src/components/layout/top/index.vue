<template>
  <div class="top">
    <div class="top-button is-left">
      <!-- breadcrumb按钮和面包屑 -->
      <div class="tags-breadcrumb">
        <i class="icon-navicon tag-collapse" :class="!collapsed ? 'el-icon-d-arrow-left' : 'el-icon-d-arrow-right'" @click="handleCollapsed"></i>
        <top-breadcrumb class="tags-breadcrumb-list"></top-breadcrumb>
      </div>
    </div>
    <h1 class="top-title">
      <topMenu></topMenu>
    </h1>
    <div class="top-button is-right">
      <span class="top-item">
        <top-lock></top-lock>
      </span>
      <span class="top-item">
        <i :class="false?'icon-tuichuquanping':'icon-quanping'" @click="handleScreen"></i>
      </span>
      <img class="top-userImg" :src="userInfo.avator">
      <el-dropdown>
        <span class="el-dropdown-link">
          {{userInfo.nickname}}
          <i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>
            <router-link to="/info/index">个人信息</router-link>
          </el-dropdown-item>
          <el-dropdown-item @click.native="logout" divided>退出系统</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import topMenu from "./top-menu";
import topLock from "./top-lock";
import topBreadcrumb from "./top-breadcrumb";

export default {
  components: { topMenu, topLock, topBreadcrumb },
  name: "top",
  props: {
    collapsed: {
      type: Boolean
    }
  },
  data() {
    return {};
  },
  filters: {},
  created() {},
  mounted() {},
  computed: {
    ...mapGetters(["userInfo"])
  },
  methods: {
    handleCollapsed () {
      this.$emit('on-collapsed-change', !this.collapsed)
    },
    handleScreen() {},
    logout() {
      this.$confirm("是否退出系统, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$store.dispatch("Logout").then(() => {
          this.$router.push({ path: "/login" });
        });
      });
    }
  }
};
</script>

<style lang="scss" scoped>
</style>

