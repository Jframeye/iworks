<template>
  <el-row class="row menu-bar" style="margin-top: 10px">
    <el-tabs v-model="current" style="height: 50px;">
      <el-tab-pane v-for="item in menu_list" :key="item.menu_code" :name="item.menu_code" :label="item.menu_name">
        <transition name="component-fade" mode="out-in">
          <keep-alive>
            <component :is="current_pane(item.menu_code)"></component>
          </keep-alive>
        </transition>
      </el-tab-pane>
      <el-tab-pane name="" :label="'current:' + current"></el-tab-pane>
      <el-tab-pane name="" :label="'parent_menu_code:' + parent_menu_code"></el-tab-pane>
    </el-tabs>
  </el-row>
</template>

<script>
  import { mapGetters } from "vuex";
  export default {
    name: 'system',
    data() {
      return {
        current: '',
        parent_menu_code: this.$route.query.menu_code,
        menu_list: []
      }
    },
    watch: {
      $route(to, from) {
        this.parent_menu_code = this.$route.query.menu_code
        // 判断一级菜单参数是否存在, 不存在,则清除二级菜单
        if(!this.parent_menu_code) {
          this.menu_list = [];
        } else { // 判断二级菜单是否存在,不存在,则重新获取
          if(this.menu_list.length === 0) {
            this.list_menus();
          }
        }
      }
    },
    created() {
      if(this.parent_menu_code) { // 一级菜单参数存在的情况下,拉起二级菜单数据
        this.list_menus();
      }
    },
    mounted() {
    },
    computed: {
      ...mapGetters(["permissions"]),
      current_pane() {
        return function (name) {
          return require('./list_' + name + '.vue').default
        }
      }
    },
    methods: {
      list_menus() {
        var _that = this;
        // 获取一级菜单
        var menu = _that.permissions.find(function (item) {
          return item.menu_code === _that.parent_menu_code
        });
        if(menu) {
          this.menu_list = menu.children;
          this.current = this.menu_list[0].menu_code
        }
      },
    }
  }
</script>

<style>
  .menu-bar .el-tabs__header {
    background-color: white;
  }
  .menu-bar .el-tabs__nav-scroll {
    padding-left: 20px;
    padding-right: 20px;
  }
  .menu-bar .el-tabs__header {
    margin-bottom: 5px;
  }
</style>
