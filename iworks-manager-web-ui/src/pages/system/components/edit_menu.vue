<template>
  <el-row class="row" style="padding-left: 5px;">
    <el-row class="row" style="height: 35px;line-height: 35px">
      <el-button size="mini" type="text" icon="fa fa-arrows-alt" style="margin-left: 5px;font-size: 15px" title="放大/缩小"></el-button>
      <el-button type="primary" size="mini" style="float: right;margin-top: 4px;margin-right: 5px;margin-left: 0px">
        <span v-if="!savePending">保存</span>
        <i class="el-icon-loading" v-else></i>
      </el-button>
    </el-row>
    <el-row class="row" style="margin-top: 5px;overflow-y: auto;height: calc(100vh - 150px);padding-bottom: 80px">
      <el-row class="row" style="background-color: white;border-radius: 5px;box-shadow: 0 2px 4px 0 rgba(0,0,0,.12), 0 0 6px 0 rgba(0,0,0,.04)">
        <el-row class="row" style="height:40px;line-height: 40px;padding-left: 10px;font-size: 14px;color: #17B9E6">
          基本信息&nbsp;
          <el-tooltip class="item" effect="dark" placement="bottom" trigger="hover">
            <i class="el-icon-info" style="font-size: 12px;"></i>
          </el-tooltip>
        </el-row>
        <el-row class="row" style="height: 1px;background-color: lightgray"></el-row>
        <el-form label-position="top" label-width="80px" style="padding: 10px 100px 20px 10px">
          <el-row class="row">
            <el-col class="col" :span="12">
              <el-form-item label="名称">
                <el-input style="width: 90%" size="small" placeholder="请输入接口名称"></el-input>
              </el-form-item>
            </el-col>
            <el-col class="col" :span="12">
              <el-form-item label="简介">
                <el-tooltip class="item" effect="dark" placement="bottom" :disabled="false">
                  <el-input style="width: 90%" size="small">
                    <i slot="suffix" class="el-input__icon el-icon-edit" style="cursor: pointer"></i>
                  </el-input>
                </el-tooltip>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="row">
            <el-col class="col" :span="12">
              <el-form-item label="分组">
              </el-form-item>
            </el-col>
            <el-col class="col" :span="12">
              <el-form-item label="状态">
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="row">
            <el-form-item label="路径">
              <el-input size="small" style="width: calc(75% - 14px);margin-left: 10px" placeholder="请输入接口路径(不包含BaseUrl)"></el-input>
            </el-form-item>
          </el-row>
          <el-row class="row">
            <el-form-item label="分享">
              <el-input size="small" style="width: 95%" disabled>
                <i slot="suffix" class="el-input__icon fa fa-copy" title="复制" style="cursor: pointer"></i>
              </el-input>
            </el-form-item>
          </el-row>
        </el-form>
      </el-row>
    </el-row>
  </el-row>
</template>

<script>
  export default {
    name: 'edit-menu',
    components: {
    },
    data () {
      return {
        savePending: true
      }
    }
  }
</script>

<style>
  #interfaceBasicInfo .el-form-item__label {
    padding-bottom: 0px;
    height: 30px;
  }
  #interfaceBasicInfo .el-form-item {
    margin-bottom: 0;
  }
  #mainParam .el-tabs__new-tab
  {
    color: rgb(80, 191, 255);
    border: 1px rgb(80, 191, 255) solid;
    margin-right: 5px;
  }
  #mainParam .el-tabs__nav-wrap
  {
    border-radius: 5px;
  }
  #mainParam .el-tabs__header {
    border-radius: 5px;
  }
  #mainParam .el-tabs__nav-scroll {
    padding-left: 0px;
    padding-right: 0px;
  }
  #mainParam.el-tabs--border-card {
    border: none
  }
  #mainParam .el-tabs__content {
    padding: 0 5px 20px 10px;
  }
</style>
