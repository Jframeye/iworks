<template>
  <el-form class="login-form" status-icon :rules="loginRules" ref="loginForm" :model="loginForm" label-width="0">
    <el-form-item prop="user_name">
      <el-input size="small" @keyup.enter.native="handleLogin" v-model="loginForm.user_name" auto-complete="off" placeholder="请输入用户名">
        <i slot="prefix" class="icon-yonghu"></i>
      </el-input>
    </el-form-item>
    <el-form-item prop="pass_word">
      <el-input size="small" @keyup.enter.native="handleLogin" :type="passwordType" v-model="loginForm.pass_word" auto-complete="off" placeholder="请输入密码">
        <i class="el-icon-view el-input__icon" slot="suffix" @click="showPassword"></i>
        <i slot="prefix" class="icon-mima"></i>
      </el-input>
    </el-form-item>
    <el-form-item prop="verify_code">
      <el-row :span="24">
        <el-col :span="14">
          <el-input size="small" @keyup.enter.native="handleLogin" :maxlength="verifycode.length" v-model="loginForm.verify_code" auto-complete="off" placeholder="请输入验证码">
            <i slot="prefix" class="icon-yanzhengma"></i>
          </el-input>
        </el-col>
        <el-col :span="10">
          <div class="login-code">
            <span class="login-code-img" @click="refreshCode" v-if="verifycode.type == 'text'">{{verifycode.value}}</span>
            <img :src="verifycode.src" class="login-code-img" @click="refreshCode" v-else/>
            <!-- <i class="icon-shuaxin login-code-icon" @click="refreshCode"></i> -->
          </div>
        </el-col>
      </el-row>

    </el-form-item>
    <!-- <el-checkbox v-model="checked">记住账号</el-checkbox> -->
    <el-form-item>
      <el-button type="primary" size="small" @click.native.prevent="handleLogin" class="login-submit">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { randomNumber } from "@/common/tools";

export default {
  name: "userlogin",
  data() {
    const validateVerifyCode = (rule, value, callback) => {
      if (this.verifycode.value != value) {
        this.loginForm.verify_code = "";
        this.refreshCode();
        callback(new Error("验证码错误"));
      } else {
        callback();
      }
    };
    return {
      loginForm: {
        user_name: "",
        pass_word: "",
        verify_code: ""
      },
      checked: false,
      verifycode: {
        src: "",
        value: "",
        length: 4,
        type: "text" // 线上无需设置值
      },
      loginRules: {
        user_name: [{ required: true, message: "请输入账号", trigger: "blur" }],
        pass_word: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, message: "密码长度最少为6位", trigger: "blur" }
        ],
        verify_code: [
          { required: true, message: "请输入验证码", trigger: "blur" },
          { min: 4, max: 4, message: "验证码长度为4位", trigger: "blur" },
          { required: true, trigger: "blur", validator: validateVerifyCode }
        ]
      },
      passwordType: "password"
    };
  },
  created() {
    this.refreshCode();
  },
  mounted() {},
  computed: {},
  props: [],
  methods: {
    refreshCode() {
      // 当验证码由后端给与时，设置“ this.verifycode.type == "" ”
      if (this.verifycode.type === "text") {
        this.verifycode.value = randomNumber(this.verifycode.len);
        this.loginForm.verify_code = this.verifycode.value;
      } else {
        this.$store.dispatch("RefreshVerifyCode").then(result => {
          this.verifycode.src = result;
        });
      }
    },
    showPassword() {
      this.passwordType == ""
        ? (this.passwordType = "password")
        : (this.passwordType = "");
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.$store.dispatch("LoginByUsername", this.loginForm)
            .then(result => {
              this.$router.push({ path: "index" });
            }).catch(err => {
              this.$message({
                type: "error",
                message: err
              });
            });
        }
      });
    }
  }
};
</script>

<style>
</style>
