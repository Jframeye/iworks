<template>
  <div>
    <div class="sidebar-logo">
      <img class="max" v-if="!collapsed" src="@/assets/logo/max-logo.png" alt="">
      <img class="min" v-else src="@/assets/logo/min-logo.png" alt="">
    </div>
    <el-scrollbar wrapClass="scrollbar-wrapper" style="height: 100%">
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
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import SidebarItem from "./sidebaritem";

export default {
  name: "Sidebar",
  components: { SidebarItem },
  data() {
    return {
      max_logo: "@/assets/logo/max-logo.png",
      min_logo: "@/assets/logo/min-logo.png"
    }
  },
  computed: {
    ...mapGetters(["routers", "sidebar"]),
    collapsed() {
      return !this.sidebar.opened
    }
  }
};
</script>
