<template>
  <el-row class="row">
    <el-row class="row" style="height: 50px;line-height: 50px; padding-right: 20px">
      <el-button type="primary" size="small" style="margin-left: 20px;" @click="showAdd=true">
        <i class="el-icon-plus" style="font-weight:900"></i>&nbsp;新增常量
      </el-button>
      <el-button type="danger" size="small" style="margin-left: 10px;">
        <strong><i class="el-icon-delete" style="font-weight:900"></i></strong>&nbsp;删除常量
      </el-button>
    </el-row>
    <el-row class="row" style="margin: 10px 20px 10px 20px;width: calc(100% - 40px);height: calc(100vh - 175px);overflow-y: auto">
      <expand :expand="1" style="background-color: white;border-radius: 5px;box-shadow: 0 2px 4px 0 rgba(0,0,0,.12), 0 0 6px 0 rgba(0,0,0,.04);">
        <div slot="title" style="font-size: 14px">
          数据查询
        </div>
        <el-row class="row">
          <el-form :inline=true size="small" v-model="search">
            <el-form-item label="常量编码">
              <el-input style="width: 250px;" v-model="search.state" placeholder="请输入常量编码"></el-input>
            </el-form-item>
            <el-form-item label="常量值">
              <el-input style="width: 250px;" v-model="search.state" placeholder="请输入常量值"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button icon="el-icon-search" round>查询</el-button>
            </el-form-item>
          </el-form>
        </el-row>
      </expand>
      <expand :expand="1" style="background-color: white;margin-top: 20px;border-radius: 5px;box-shadow: 0 2px 4px 0 rgba(0,0,0,.12), 0 0 6px 0 rgba(0,0,0,.04);">
        <div slot="title" style="font-size: 14px">
          数据结果
        </div>
        <div slot="append" style="margin-right: 20px; font-size: 14px">
          状态:
          <el-select v-model="search.state" size="mini" style="width: 80px; margin-left: 5px;">
            <el-option v-for="state in states" :key="state.key" :label="state.value" :value="state.key"></el-option>
          </el-select>
        </div>
        <el-row class="row">
          <el-table :data="tableData" border style="width: 100%" size="mini">
            <el-table-column fixed type="selection" width="55" align="center"></el-table-column>
            <el-table-column prop="date" label="日期" width="150"></el-table-column>
            <el-table-column prop="name" label="姓名" width="120"></el-table-column>
            <el-table-column prop="province" label="省份" width="120"></el-table-column>
            <el-table-column prop="city" label="市区" width="120"></el-table-column>
            <el-table-column label="地址" width="300">
              <template slot-scope="scope">
                <dot type="success"></dot>{{scope.row.address}}
              </template>
            </el-table-column>
            <el-table-column label="邮编" width="120">
              <template slot-scope="scope">
                <dot type="danger"></dot>{{scope.row.zip}}
              </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="120">
              <template slot-scope="scope">
                <el-button type="text" size="small">移除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div class="pagination-container">
            <el-pagination background :current-page="1" :page-sizes="[10,20,30, 50]" :page-size="10" layout="sizes, prev, pager, next, total, jumper" :total="100">
            </el-pagination>
          </div>
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
  import expand from '@/components/expand.vue';
  import dot from '@/components/dot.vue';
  export default {
    name: 'system',
    components: {
      "expand": expand,
      "dot": dot
    },
    data() {
      return {
        showAdd: false,
        tableData: [{
          date: '2016-05-03',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          date: '2016-05-02',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          date: '2016-05-04',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          date: '2016-05-01',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          date: '2016-05-08',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          date: '2016-05-06',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          date: '2016-05-07',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }],
        states: [{
          key: '',
          value: '全部'
        }, {
          key: '1',
          value: '正常'
        }, {
          key: '2',
          value: '禁用'
        }],
        search: {
          state: ''
        }
      }
    }
  }
</script>
