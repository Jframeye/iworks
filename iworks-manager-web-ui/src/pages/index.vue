<template>
  <div v-cloak>
    <el-row class="row header-bar">
      <el-col class="col logo-bar">
        <img src="@/assets/index_logo.png">
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
                    {{current}}
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
            <router-link to="/index">
              <svg-icon :icon-class="current == 'index' ? 'index_activite' : 'index'" style="width: 32px; height: 32px; margin-top: 15px;"></svg-icon>
              <br>
              后台首页
            </router-link>
          </el-row>
          <el-row v-for="item in permissions" :key="item.menu_code" class="row menu-item">
            <router-link :to="{ name: item.menu_code, query: { menu_code: item.menu_code }}">
              <svg-icon :icon-class="current == item.menu_code ? item.menu_icon + '_activite' : item.menu_icon" style="width: 32px; height: 32px; margin-top: 15px;"></svg-icon>
              <br>
              {{item.menu_name}}
            </router-link>
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
  import { mapGetters } from "vuex";
  export default {
    name: 'console',
    components: {
    },
    data() {
      return {
        current: this.$route.name,
        menu_list: []
      }
    },
    watch: {
      $route(to, from) {
        this.current = this.$route.name
      }
    },
    created() {
    },
    computed: {
      ...mapGetters(["permissions"])
    },
    methods: {
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  .header-bar { height: 60px;line-height: 60px; }
  .logo-bar { width: 100px;line-height: 60px;text-align: center;background-color:#17B9E6; }
  .logo-bar img { margin-top: 10px }
  .header-info-bar { width: calc(100vw - 100px);background-color: white;box-shadow: rgba(0, 0, 0, 0.15) 0px 1px 3px 0px; }
  .back-btn { font-size: 20px;float: left;margin-left: 20px;margin-top: 8px; }
  .header-info { font-size: 15px;font-weight: bold;color:#17B9E6; }
  .user-info { height: 60px;text-align: right;padding-right: 20px; }
  .user-info img { width: 40px;height: 40px; border-radius:50%;margin-top: 10px; }
  .container-box { height: calc(100vh - 60px);position: fixed; }
  .container-box .menu-items { width: 100px;background-color: #333333; }
  .container-box .menu-items .menu-item { height: 80px; text-align: center;font-size: 14px;cursor: pointer; }
  .container-box .menu-items .menu-item a { text-decoration: none; color: #888888; }
  .container-box .content-region { width: calc(100vw - 100px);overflow-y: hidden; }

  .router-link-active { text-decoration: none; color: #ffffff!important; }
</style>
