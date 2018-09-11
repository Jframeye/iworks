<template>
  <el-dropdown trigger="click" :show-timeout="100">
    <el-button plain>{{title}}
      <i class="el-icon-caret-bottom el-icon--right"></i>
    </el-button>
    <el-dropdown-menu class="no-padding" slot="dropdown">
      <el-dropdown-item>
        <slot></slot>
      </el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
</template>

<script>
export default {
  name: "dropdown",
  props: {
    title: {
      type: String,
      required: true
    }
  },
  computed: {}
};
</script>
