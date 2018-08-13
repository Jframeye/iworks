<template>
  <div class="login" @keydown.enter="handleSubmit">
    <div class="login-con">
      <Card icon="log-in" title="欢迎登录" :bordered="false">
        <div class="form-con">
          <Form ref="loginForm" :model="form" :rules="rules">
            <FormItem prop="username">
              <Input prefix="md-person" v-model="form.username" placeholder="请输入用户名" />
            </FormItem>
            <FormItem prop="password">
              <Input type="password" prefix="ios-lock" v-model="form.password" placeholder="请输入密码" />
            </FormItem>
            <FormItem>
              <Button @click="handleSubmit" type="primary" long>登录</Button>
            </FormItem>
          </Form>
          <p class="login-tip">输入任意用户名和密码即可</p>
        </div>
      </Card>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  data() {
    return {
      form: {
        username: "admin",
        password: "admin"
      },
      // 校验规则
      usernameRules: {
        required: true,
        message: "账号不能为空",
        trigger: "blur"
      },
      passwordRules: {
        required: true,
        message: "密码不能为空",
        trigger: "blur"
      }
    };
  },
  computed: {
    rules() {
      return {
        username: this.usernameRules,
        password: this.passwordRules
      };
    }
  },
  methods: {
    ...mapActions(["handleLogin", "getUserInfo"]),
    handleSubmit() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          let username = this.form.username;
          let password = this.form.password;
          // 用户登录
          this.handleLogin({ username, password }).then(result => {
            // 登录成功之后，拉取用户数据
            this.getUserInfo().then(result => {
              this.$router.push({
                name: "index"
              });
            });
          });
        }
      });
    }
  }
};
</script>

<style lang="less">
@import "./login.less";
</style>
