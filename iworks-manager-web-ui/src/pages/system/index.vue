<template>
  <el-row class="row menu-bar" style="margin-top: 10px">
    <el-tabs style="height: 50px;">
      <el-tab-pane name="" label="菜单管理">
        <constant-view></constant-view>
      </el-tab-pane>
      <el-tab-pane name="" label="权限管理"></el-tab-pane>
      <el-tab-pane name="" label="常量管理"></el-tab-pane>
      <el-tab-pane name="" label="字段管理"></el-tab-pane>
      <el-tab-pane name="" label="日志管理"></el-tab-pane>
      <el-tab-pane name="" label="设置"></el-tab-pane>
      <!--<keep-alive>
        <router-view></router-view>
      </keep-alive>-->
    </el-tabs>
  </el-row>
</template>

<script>
  import constant_view from './constant_list.vue'
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
