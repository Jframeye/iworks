<template>
  <el-dropdown trigger="click" :show-timeout="100">
    <el-button plain>{{!publicity_disabled ? '公开' : '私密'}}
      <i class="el-icon-caret-bottom el-icon--right"></i>
    </el-button>
    <el-dropdown-menu class="no-padding" slot="dropdown">
      <el-dropdown-item>
        <el-radio-group style="padding: 10px;" v-model="publicity_disabled">
          <el-radio :label="true">公开</el-radio>
          <el-radio :label="false">私密</el-radio>
        </el-radio-group>
      </el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
</template>

<script>
export default {
  name: "publicity",
  props: ["value"],
  computed: {
    publicity_disabled: {
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
