<template>
  <el-container class="avue-contail">
    <el-aside :style="{width: collapsed ? '65px' : '230px'}">
      <!-- 左侧导航栏 -->
      <sidebar class="avue-sidebar" :collapsed="collapsed"></sidebar>
    </el-aside>
    <el-container>
      <el-header height="auto" class="avue-tabs">
        <!-- 顶部导航栏 -->
        <top :collapsed="collapsed" @on-collapsed-change="handleCollapsedChange" />
        <!-- 顶部标签卡 -->
        <navtags />
      </el-header>
      <el-main class="avue-main">
        <!-- 主体视图层 -->
        <keep-alive>
          <transition name="fade-transverse">
            <router-view class="avue-view" v-if="$route.meta.keepAlive" />
          </transition>
        </keep-alive>
        <transition name="fade-transverse">
          <router-view class="avue-view" v-if="!$route.meta.keepAlive" />
        </transition>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import sidebar from "./sidebar/";
import top from "./top/";
import navtags from "./navtags";

export default {
  components: {
    sidebar,
    top,
    navtags
  },
  name: "index",
  data() {
    return {
      collapsed: false
    };
  },
  watch: {
    $route(newRoute) {
      // TODO
    }
  },
  methods: {
    handleCollapsedChange(state) {
      this.collapsed = state;
    }
  }
};
</script>

<style lang="scss" scoped>
.avue-contail {
  height: 100%;
}
.avue-sidebar {
  height: 100%;
}
.avue-tabs {
  padding: 0;
}
.avue-main {
  position: relative;
  padding: 0;
}
.avue-view {
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
}
</style>