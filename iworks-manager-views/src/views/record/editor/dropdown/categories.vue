<template>
  <el-dropdown :show-timeout="100" trigger="click">
    <el-button plain>
      {{categories ? categories : "分类"}}
      <i class="el-icon-caret-bottom el-icon--right"></i>
    </el-button>
    <el-dropdown-menu class="no-padding no-border" style="width:400px" slot="dropdown">
      <el-form-item label-width="45px" label="分类:" class="postInfo-container-item">
        <el-select v-model="postForm.author" filterable remote placeholder="搜索分类" :remote-method="getRemoteUserList">
          <el-option v-for="(item,index) in userListOptions" :key="item+index" :label="item" :value="item"></el-option>
        </el-select>
      </el-form-item>
    </el-dropdown-menu>
  </el-dropdown>
</template>

<script>
export default {
  name: "categories",
  props: ["value"],
  computed: {
    categories: {
      get() {
        return this.value;
      },
      set(val) {
        this.$emit("input", val);
      }
    }
  }
};
</script>
