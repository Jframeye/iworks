<template>
  <div class="page-container" id="app" v-cloak>
    <div class="login-register-bg">
        <el-row>
            <el-col :span="24" :xs="24">
                <h2 style="text-align: center;margin: 0;padding: 50px 0 30px"><a href="/"><img src="@/assets/logo.png"></a></h2>
                <el-row>
                    <el-col :span="8" :xs="4" :sm="7" :md="8">&nbsp;</el-col>
                    <el-col :span="8" class="login-box-bg" :xs="16" :sm="10" :md="8">
                        <el-row>
                            <el-col :span="2" :xs="2">&nbsp;</el-col>
                            <el-col :span="20" class="login-box" :xs="20">
                                <el-form >
                                    <el-form-item label="请输入用户名" prop="username">
                                        <el-input v-model="username"></el-input>
                                    </el-form-item>
                                    <el-form-item label="请输入密码" prop="password" style="margin-bottom: 30px;">
                                        <el-input v-model="pwd" type="password"></el-input>
                                    </el-form-item>
                                    <el-form-item >
                                        <el-button type="warning" style="width: 100%" @click.prevent="login" :loading="loginPending" id="login">登录</el-button>
                                    </el-form-item>
                                    <el-form-item >
                                         <el-checkbox class="fl" v-model="remember" :true-label="1" :false-label="0">记住我</el-checkbox>
                                         <!--<img @click="qqLogin()"  target="_blank" class="fr" style="padding-top: 5px;cursor: pointer" id="qqLoginBtn" src="../../resource/pic/qq.png"> </a>-->
                                    </el-form-item >
                                    <el-form-item >
                                        <a href="#"  >忘记密码？</a>&nbsp;&nbsp;<span style="color: #ccc">|</span>&nbsp;&nbsp;
                                        <a href="#" >没有账号？立即注册</a>
                                        <a href="#" class="fr">管理总后台</a>
                                    </el-form-item >
                                </el-form>
                            </el-col>
                        </el-row>
                    </el-col>
                </el-row>
            </el-col>
        </el-row>
    </div>
</div>
</template>

<script>
export default {
  name: 'login',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.tc{text-align: center}
.page-container {
    margin: 0;
    padding: 0;
    width: 100%;
    height:calc(100vh)
}
.fr{float: right}
.fl{float: left;}
.login-register-bg{background: linear-gradient(60deg,#541466 0,#17b9e6 100%);height: 100%;}
.login-box-bg{background-color: rgba(255,255,255,0.1);border-radius: 10px;padding: 30px 0;}
.login-box label{color: #fff}
.login-box .el-form-item {
    margin-bottom: 0px;
}
.login-box .el-form-item a{color: #fff}
</style>
