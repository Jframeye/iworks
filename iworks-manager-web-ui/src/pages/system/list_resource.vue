<template>
  <el-row class="row" style="padding: 0 10px 0 10px;">
    <el-col class="col" :span="6" style="padding-right: 5px;">
      <el-row class="row" style="height: 35px;line-height: 35px">
        <el-tooltip class="item" effect="dark" content="新增分组" placement="bottom">
          <el-button size="mini" type="primary" @click.native="addGroup">
            <i class="el-icon-plus" style="font-weight:900"></i>
          </el-button>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="导入分组" placement="bottom">
          <el-button size="mini" type="primary" style="margin-left: 3px" @click.native="importGroup">
            <i class="el-icon-download" style="font-weight:900"></i>
          </el-button>
        </el-tooltip>
      </el-row>
      <el-row class="row" style="background-color: white;padding: 5px;border-radius: 5px;height: calc(100vh - 150px);overflow-y: auto;margin-top: 5px">
        <el-input size="small" placeholder="" style="width: 100%;margin-bottom: 10px">
          <template slot="prepend">
            <label>名称</label>
          </template>
        </el-input>
        <list></list>
      </el-row>
    </el-col>
    <el-col :span="bMax?24:18" class="col" style="height: calc(100vh - 110px);">
      <el-row class="row">
        <transition name="component-fade" mode="out-in">
          <edit-resource></edit-resource>
        </transition>
      </el-row>
    </el-col>
  </el-row>
</template>

<script>
  import edit from './components/edit_resource.vue';
  export default {
    name: 'resource',
    components: {
      'edit-resource': edit
    },
    data () {
      return {
        msg: 'Welcome to Your Vue.js App'
      }
    }
  }
</script>

<style>
  #searchInterface {
    padding-left: 5px;
    padding-right: 5px;
  }
  #interfaceContent .component-fade-enter-active, .component-fade-leave-active {
    transition: opacity .3s ease;
  }
  #interfaceContent .component-fade-enter, .component-fade-leave-to
    /* .component-fade-leave-active for below version 2.1.8 */ {
    opacity: 0;
  }
</style>
