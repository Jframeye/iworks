<template>
  <el-scrollbar wrapClass="scrollbar-wrapper">
    <div>asdasd</div>
    <el-menu
      mode="vertical"
      :show-timeout="200"
      :default-active="$route.path"
      :collapse="collapsed"
      background-color="#304156"
      text-color="#bfcbd9"
      active-text-color="#409EFF"
    >
      <sidebar-item v-for="route in routers" :key="route.name" :item="route" :base-path="route.path"></sidebar-item>
    </el-menu>
  </el-scrollbar>
</template>

<script>
import { mapGetters } from "vuex";
import SidebarItem from "./sidebaritem";

export default {
  name: "Sidebar",
  components: { SidebarItem },
  computed: {
    ...mapGetters(["routers", "sidebar"]),
    collapsed() {
      return !this.sidebar.opened
    }
  }
};
</script>
