<template>
  <el-breadcrumb separator="/" class="tags-breadcrumb-list">
    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item><a href="/">活动管理</a></el-breadcrumb-item>
    <el-breadcrumb-item>活动列表</el-breadcrumb-item>
    <el-breadcrumb-item>活动详情</el-breadcrumb-item>
    <!-- <template v-for="(item, index) in tagCurrent">
      <el-breadcrumb-item :to="{ path: item.path }" :key="index">{{item.title}}</el-breadcrumb-item>
    </template> -->
  </el-breadcrumb>
</template>
<script>
export default {
  name: "breadcrumb",
  data() {
    return {};
  },
  created() {},
  mounted() {},
  watch: {},
  computed: {
  },
  methods: {}
};
</script>
<style lang="scss" scoped>

</style>


