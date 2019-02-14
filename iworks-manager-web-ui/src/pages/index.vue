<template>
  <div class="container" v-cloak>
    <el-row class="row header-bar">
      <el-col class="col logo-bar">
        <img src="http://doclever.cn:8090/logo.png">
      </el-col>
      <el-col class="col header-info-bar">
        <el-row class="row" style="text-align: center">
          <el-col class="col" :span="6">
            <el-tooltip class="item" effect="dark" content="返回" placement="bottom">
              <el-button icon="el-icon-back" type="text" size="medium" class="back-btn"></el-button>
            </el-tooltip>
          </el-col>
          <el-col class="col header-info" :span="12">
            这里可以试操作提示或者菜单名称
          </el-col>
          <el-col class="col user-info" :span="6">
            <img src="@/assets/logo_22.png">&nbsp;
            <el-dropdown style="top: -15px;">
              <span class="el-dropdown-link" style="color: #50bfff;cursor: pointer">
                <span>
                    叶宏梁
                </span>
                <i class="el-icon-caret-bottom el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="update">检查更新</el-dropdown-item>
                <el-dropdown-item command="help">帮助中心</el-dropdown-item>
                <el-dropdown-item command="quit">退出</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-row class="row container-box">
      <el-col class="col menu-items">
        <vue-scroll>
          <el-row class="row menu-item">
            <svg-icon icon-class="xiangmu" style="width: 32px; height: 32px; margin-top: 15px;"></svg-icon>
            <br>
            系统管理
          </el-row>
          <el-row class="row menu-item">
            <svg-icon icon-class="xiangmu" style="width: 32px; height: 32px; margin-top: 15px;"></svg-icon>
            <br>
            系统管理
          </el-row>
          <el-row class="row menu-item">
            <svg-icon icon-class="xiangmu" style="width: 32px; height: 32px; margin-top: 15px;"></svg-icon>
            <br>
            系统管理
          </el-row>
          <el-row class="row menu-item">
            <svg-icon icon-class="xiangmu" style="width: 32px; height: 32px; margin-top: 15px;"></svg-icon>
            <br>
            系统管理
          </el-row>
          <el-row class="row menu-item">
            <svg-icon icon-class="xiangmu" style="width: 32px; height: 32px; margin-top: 15px;"></svg-icon>
            <br>
            系统管理
          </el-row>
          <el-row class="row menu-item">
            <svg-icon icon-class="xiangmu" style="width: 32px; height: 32px; margin-top: 15px;"></svg-icon>
            <br>
            系统管理
          </el-row>
          <el-row class="row menu-item">
            <svg-icon icon-class="xiangmu" style="width: 32px; height: 32px; margin-top: 15px;"></svg-icon>
            <br>
            系统管理
          </el-row>
          <el-row class="row menu-item">
            <svg-icon icon-class="xiangmu" style="width: 32px; height: 32px; margin-top: 15px;"></svg-icon>
            <br>
            系统管理
          </el-row>
          <el-row class="row menu-item">
            <svg-icon icon-class="xiangmu" style="width: 32px; height: 32px; margin-top: 15px;"></svg-icon>
            <br>
            系统管理
          </el-row>
          <el-row class="row menu-item">
            <svg-icon icon-class="xiaoxi" style="width: 32px; height: 32px; margin-top: 15px;"></svg-icon>
            <br>
            消息
          </el-row>
        </vue-scroll>
      </el-col>
      <el-col class="col content-region">
        <transition name="fade-transform" mode="out-in">
          <keep-alive>
            <router-view></router-view>
          </keep-alive>
        </transition>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: 'console',
    components: {
    },
    data() {
      return {
        type: 0
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  .header-bar { height: 60px;line-height: 60px; }
  .logo-bar { width: 100px;line-height: 60px;text-align: center;background-color:#17B9E6; }
  .logo-bar img { width:40px;height:40px;margin-top: 10px }
  .header-info-bar { width: calc(100vw - 100px);background-color: white;box-shadow: rgba(0, 0, 0, 0.15) 0px 1px 3px 0px; }
  .back-btn { font-size: 20px;float: left;margin-left: 20px;margin-top: 8px; }
  .header-info { font-size: 15px;font-weight: bold;color:#17B9E6; }
  .user-info { height: 60px;text-align: right;padding-right: 20px; }
  .user-info img { width: 40px;height: 40px; border-radius:50%;margin-top: 10px; }
  .container-box { height: calc(100vh - 60px);position: fixed; }
  .container-box .menu-items { width: 100px;background-color: #333333; }
  .container-box .menu-items .menu-item { height: 80px; text-align: center;font-size: 14px;cursor: pointer; color: #888888; }

  .container-box .content-region { width: calc(100vw - 100px);overflow-y: hidden; }
</style>
