<template>
  <el-row class="row">
    <el-row class="row" style="height: 50px;line-height: 50px; padding-right: 20px">
      <el-button type="primary" size="small" style="margin-left: 20px;" @click="showAdd=true">
        <i class="el-icon-plus" style="font-weight:900"></i>&nbsp;新增项目
      </el-button>
      <el-button size="small" style="margin-left: 10px;">
        <strong><i class="el-icon-download" style="font-weight:900"></i></strong>&nbsp;导入项目
      </el-button>
    </el-row>
    <el-row class="row" style="margin: 10px 20px 10px 20px;width: calc(100% - 40px);height: calc(100vh - 175px);overflow-y: auto">
      <expand :expand="1" style="background-color: white;border-radius: 5px;box-shadow: 0 2px 4px 0 rgba(0,0,0,.12), 0 0 6px 0 rgba(0,0,0,.04);">
        <div slot="title" style="font-size: 14px">
          我创建的项目
        </div>
        <el-radio-group size="mini" slot="append" style="margin-right: 20px">
          <el-radio-button :label="0">时间&nbsp;↓</el-radio-button>
          <el-radio-button :label="1">名称&nbsp;↑</el-radio-button>
        </el-radio-group>
        <el-row class="row">
          <div>内容</div>
        </el-row>
      </expand>
      <expand :expand="1" style="background-color: white;margin-top: 20px;border-radius: 5px;box-shadow: 0 2px 4px 0 rgba(0,0,0,.12), 0 0 6px 0 rgba(0,0,0,.04);">
        <div slot="title" style="font-size: 14px">
          我加入的项目
        </div>
        <el-radio-group size="mini" slot="append" style="margin-right: 20px">
          <el-radio-button :label="0">时间&nbsp;↓</el-radio-button>
          <el-radio-button :label="1">名称&nbsp;↑</el-radio-button>
        </el-radio-group>
        <el-row class="row">
          <div style="height: 600px;">内容</div>
        </el-row>
      </expand>
    </el-row>
    <el-dialog title="新建项目" :visible.sync="showAdd" width="50%" append-to-body>
      <el-form label-position="top" ref="form" label-width="100px">
        <el-form-item label="名称">
          <el-input  style="width: 100%" placeholder="请输入新项目的名称"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input type="textarea" :rows="2"  style="width: 100%" placeholder="请输入新项目的简介"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
            <el-button @click="showAdd = false">取 消</el-button>
            <el-button type="primary">确 定</el-button>
        </span>
    </el-dialog>
  </el-row>
</template>

<script>
  import expand from '@/components/expand.vue'
  export default {
    name: 'system',
    components: {
      "expand": expand
    },
    data() {
      return {
        showAdd: false
      }
    }
  }
</script>
