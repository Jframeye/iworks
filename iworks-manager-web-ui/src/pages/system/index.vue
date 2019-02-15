<template>
  <el-row class="row menu-bar" style="margin-top: 10px">
    <el-tabs style="height: 50px;">
      <el-tab-pane name="" label="菜单管理"></el-tab-pane>
      <el-tab-pane name="" label="权限管理"></el-tab-pane>
      <el-tab-pane name="" label="字典管理"></el-tab-pane>
      <el-tab-pane name="" label="日志管理"></el-tab-pane>
      <el-tab-pane name="" label="参数设置"></el-tab-pane>
      <keep-alive>
        <router-view></router-view>
      </keep-alive>
    </el-tabs>
  </el-row>
</template>

<script>
  import constant_view from './list_parameter.vue'
  export default {
    name: 'system',
    components: {
      "constant-view": constant_view
    },
    data() {
      return {
        type: 0
      }
    }
  }
</script>

<style>
  .menu-bar .el-tabs__header {
    background-color: white;
  }
  .menu-bar .el-tabs__nav-scroll {
    padding-left: 20px;
    padding-right: 20px;
  }
  .menu-bar .el-tabs__header {
    margin-bottom: 5px;
  }
</style>
