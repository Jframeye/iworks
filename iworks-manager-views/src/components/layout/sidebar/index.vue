<template>
  <div class="sidebar-container" :class="{'is-active':collapsed}">
    <logo :collapsed="collapsed"></logo>
    <el-menu unique-opened :default-active="resolvePath" mode="vertical" :show-timeout="200" background-color="#00142a" text-color="hsla(0,0%,100%,.65)" active-text-color="#409eff" :collapse="collapsed">
      <sidebar-item :menus="menus" :collapsed="collapsed"></sidebar-item>
    </el-menu>
  </div>
</template>

<script>
import {resolvePath} from "@/common/utils";
import logo from "./logo";
import SidebarItem from "./sidebarItem";

export default {
  name: "sidebar",
  components: { logo, SidebarItem },
  props: {
    collapsed: {
      type: Boolean
    }
  },
  data() {
    return {};
  },
  created() {},
  computed: {
    menus() {
      return this.$store.getters.menus;
    },
    resolvePath() {
      // TODO
      return resolvePath(this.$route);
    }
  }
};
</script>
<style lang="scss" scoped>
</style>

