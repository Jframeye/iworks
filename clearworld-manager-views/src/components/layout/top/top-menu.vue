<template>
  <div class="pull-auto top-menu">
    <el-menu :default-active="activeIndex" mode="horizontal" text-color="#606266">
      <template v-for="(item, index) in items">
        <el-menu-item :index="item.index" @click.native="openMenu(item)" :key="index">
          <template slot="title">
            <i :class="item.icon"></i>
            <span slot="title">{{item.title}}</span>
          </template>
        </el-menu-item>
      </template>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "top-menu",
  data() {
    return {
      activeIndex: "0",
      items: [
        // {
        //   path: "/",
        //   name: "home",
        //   title: '首页',
        //   icon: "el-icon-loading",
        //   index: 0
        // }
      ]
    };
  },
  created() {},
  computed: {},
  methods: {
    openMenu(item) {}
  }
};
</script>

<style scoped="scoped" lang="scss">
.top-menu {
  margin-top: -4px;
  box-sizing: border-box;
}
</style>