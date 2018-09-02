<template>
  <span>
    <i class="el-icon-warning" style="cursor: pointer" @click="handleLock"></i>
    <el-dialog title="设置锁屏密码" :visible.sync="show" width="30%" append-to-body>
      <el-form :model="form" ref="form" label-width="80px">
        <el-form-item label="锁屏密码" prop="password" :rules="[{ required: true, message: '锁屏密码不能为空'}]">
          <el-input v-model="form.password" placeholder="请输入锁屏密码"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleSetLock">确 定</el-button>
      </span>
    </el-dialog>
  </span>
</template>
<script>
import { isBlank } from "@/common/tools";

export default {
  name: "top-lock",
  data() {
    return {
      show: false,
      form: {
        password: ""
      }
    };
  },
  methods: {
    handleSetLock() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.$store.commit("SET_LOCK_PASSWORD", this.form.password);
          this.handleLock();
        }
      });
    },
    handleLock() {
      let lockpassword = this.$store.getters.lockpassword;
      if (isBlank(lockpassword)) {
        this.show = true;
        return;
      }
      this.$store.commit("SET_LOCK");
      setTimeout(() => {
        this.$router.push({ path: "/locked" });
      }, 100);
    }
  },
  components: {}
};
</script>

<style lang="scss" scoped>
</style>
