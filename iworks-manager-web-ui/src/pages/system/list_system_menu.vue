<template>
  <el-row class="row section-box">
    <el-col class="col" :span="full_box ? 0 : 6">
      <el-row class="row tools-box">
        <el-button size="small" type="primary" @click.native="add_menu">
          <i class="el-icon-plus"></i>&nbsp;新增菜单组
        </el-button>
      </el-row>
      <el-row class="row search-box">
        <el-input size="small" placeholder="" style="margin-bottom: 10px">
          <template slot="prepend">
            <label>名称</label>
          </template>
        </el-input>
        <list :data="menu_list"></list>
      </el-row>
    </el-col>
    <el-col :span="full_box ? 24 : 18" class="col">
      <el-row class="row">
        <transition name="component-fade" mode="out-in">
          <edit-menu></edit-menu>
        </transition>
      </el-row>
    </el-col>
  </el-row>
</template>

<script>
  import list from './components/menu_item.vue';
  import edit from './components/edit_menu.vue';
  export default {
    name: 'menu',
    components: {
      'edit-menu': edit,
      'list': list
    },
    data () {
      return {
        full_box: false,
        menu_list: [
          {
            name: '系统管理',
            expand: false,
            show_tools: false,
            data: [
              { type: 0, name: '测试', expand: false, show_tools: false, data: [{ type: 1, name: '日志管理', show_tools: false }]},
              { type: 1, name: '菜单管理', show_tools: false },
              { type: 2, name: '字典管理', show_tools: false }
            ]
          },
          {
            name: '权限管理',
            expand: true,
            show_tools: false,
            data: [
              { type: 1, name: '菜单权限', show_tools: false },
              { type: 2, name: '数据权限', show_tools: false }
            ]
          }
        ]
      }
    }
  }
</script>

<style>
  .search-box { background-color: white;padding: 5px;border-radius: 5px;height: calc(100vh - 150px);overflow-y: auto;margin-top: 5px; }
</style>
