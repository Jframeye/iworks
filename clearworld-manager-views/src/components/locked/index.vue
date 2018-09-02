<template>
  <div class="lock-container pull-height">
    <div class="lock-form animated bounceInDown">
      <div class="animated" :class="{'shake':wrong,'bounceOut':right}">
        <h3 class="text-white">{{userInfo.username}}</h3>
        <el-input placeholder="请输入登录密码" type="password" class="input-with-select animated" v-model="password" @keyup.enter.native="handleLogin">
          <el-button slot="append" icon="el-icon-question" @click="handleLogin"></el-button>
          <el-button slot="append" icon="el-icon-d-arrow-right" @click="handleLogout"></el-button>
        </el-input>
      </div>

    </div>
  </div>
</template>
<script>
import { mapGetters, mapState } from "vuex";
export default {
  name: "lock",
  data() {
    return {
      password: "",
      wrong: false,
      right: false
    };
  },
  computed: {
    ...mapState({
      userInfo: state => state.biz.userInfo
    }),
    ...mapGetters(["lockpassword"])
  },
  methods: {
    handleLogout() {
      this.$confirm("是否退出系统, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$store.dispatch("Logout").then(() => {
          this.$router.push({ path: "/login" });
        });
      });
    },
    handleLogin() {
      if (this.password != this.lockpassword) {
        this.password = "";
        this.$message({
          message: "解锁密码错误,请重新输入",
          type: "error"
        });
        this.wrong = true;
        setTimeout(() => {
          this.wrong = false;
        }, 1000);
        return;
      }
      this.right = true;
      setTimeout(() => {
        this.$store.commit("CLEAR_LOCK");
        this.$router.push({ path: "/" }); // 待修改，应该跳转到锁屏时所选的页面
      }, 1000);
    }
  }
};
</script>

<style lang="scss">
.lock-container {
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(0, 0, 0, 0.6);
  position: relative;
}
.lock-container::before {
  z-index: -999;
  content: "";
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-image: url("/static//img/login.png");
  background-size: cover;
}
.lock-form {
  width: 300px;
}
</style>