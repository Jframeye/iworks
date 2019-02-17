<template>
  <div class="errPage-container">
    <el-button @click="back" icon='arrow-left' class="pan-back-btn">返回</el-button>
    <el-row>
      <el-col :span="12">
        <h1 class="text-jumbo text-ginormous">天啊!</h1>
        <h2>你没有权限去该页面</h2>
      </el-col>
      <el-col :span="12">
        <img :src="err_gif" width="313" height="428" alt="Girl has dropped her ice cream.">
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import err_gif from "@/assets/images/401_images/401.gif";

  export default {
    name: "401",
    data() {
      return {
        err_gif: err_gif + "?" + +new Date(),
      };
    },
    methods: {
      back() {
        if (this.$route.query.noGoBack) {
          this.$router.push({path: "/"});
        } else {
          this.$router.go(-1);
        }
      }
    }
  };
</script>

<style scoped>
</style>
