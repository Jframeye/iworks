<template>
  <div class="menu-wrapper">
    <template v-for="(item, index) in menus">
      <el-menu-item v-if="!hasChildren(item)" :index="filterPath(item.path, index)" @click="open(item)" :key="item.name">
        <i :class="item.icon"></i>
        <span slot="title">{{item.title}}</span>
      </el-menu-item>
      <el-submenu v-else :index="filterPath(item.path, index)" :key="item.name">
        <template slot="title">
          <i :class="item.icon"></i>
          <span slot="title" :class="{'el-menu--display':collapsed}">{{item.title}}</span>
        </template>
        <template v-for="(child, cindex) in item.children">
          <el-menu-item v-if="!hasChildren(item)" :class="{'siderbar-active':navtag == child.path}" :index="filterPath(child.path, cindex)" @click="open(child)" :key="cindex">
            <i :class="child.icon"></i>
            <span slot="title">{{child.title}}</span>
          </el-menu-item>
          <sidebar-item v-else :menus="[child]" :key="cindex" :collapsed="collapsed"></sidebar-item>
        </template>
      </el-submenu>
    </template>
  </div>
</template>
<script>
import { resolvePath, setUrlPath } from "@/common/utils";

export default {
  name: "SidebarItem",
  data() {
    return {};
  },
  props: {
    menus: {
      type: Array
    },
    collapsed: {
      type: Boolean
    }
  },
  computed: {
    navtag: function() {
      return resolvePath(this.$route);
    }
  },
  methods: {
    hasChildren(item) {
      return item.children && item.children.length !== 0;
    },
    filterPath(path, index) {
      return path == null ? index + "" : path;
    },
    open(item) {
      this.$router.push({
        path: item.path
      });
    },
    close() {
      console.log('关闭')
    }
  }
};
</script>
<style lang="scss" scoped>
//刷新激活状态
.siderbar-active {
  i,
  span {
    color: #409eff;
  }
}
</style>

