<template>
  <div class="app-container">
    <div class="search-box">
      <el-collapse accordion>
        <el-collapse-item>
          <template slot="title">
          <i class="header-icon el-icon-search" style="margin-right: 5px;"></i>查询
          </template>
          <div style="margin: 10px 10px -25px">
            <el-form :inline=true size="mini" v-model="search">
              <el-form-item label="字典编码">
                <el-input style="width: 250px;" v-model="search.dict_code" placeholder="请输入字典编码"></el-input>
              </el-form-item>
              <el-form-item label="字典名称">
                <el-input style="width: 250px;" v-model="search.dict_name" placeholder="请输入字典名称"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button icon="el-icon-search" round>查询</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-collapse-item>
      </el-collapse>
    </div>
    <div class="operationBox">
      <el-button-group style="border-bottom: 0px;">
        <el-button size="mini" icon="el-icon-plus" @click="updateData()">新增</el-button>
        <el-button type="danger" size="mini" icon="el-icon-delete" @click="deleteData">删除</el-button>
      </el-button-group>
    </div>
    <el-table size='mini' :data="table_datas" v-loading.body="table_loading" border fit highlight-current-row style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="dict_name" align="center" label="字典名称"></el-table-column>
      <el-table-column prop="dict_code" align="center" label="字典编码">
        <template slot-scope="scope">
          <router-link class="link-type" :to="'dictionary_data?'+scope.row.dict_code">
            <span>{{ scope.row.dict_code }}</span>
          </router-link>
        </template>
      </el-table-column>

      <el-table-column align="center" label="状态">
        <template slot-scope="scope">
          <span><el-tag :type="scope.row.tag_type">{{formatter(scope.row).dict_value}}</el-tag></span>
        </template>
      </el-table-column>
      <el-table-column prop="dict_desc" align="center" label="备注"></el-table-column>

      <el-table-column prop="modify_time" label="修改时间" width="140"></el-table-column>
      <el-table-column prop="modify_by" label="修改人" width="140"></el-table-column>

      <el-table-column align="center" label="操作" width="200">
        <template slot-scope="scope">
          <el-button-group>
            <el-button type="text" size='mini' icon="el-icon-edit" @click="updateData(scope.row)">修改</el-button>
          </el-button-group>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page.offset"
        :page-sizes="[10,20,30, 50]" :page-size="page.limit" layout="total, sizes, prev, pager, next, jumper" :total="page.total">
      </el-pagination>
    </div>

    <!-- 编辑框 -->
    <el-dialog :title="saveorupdate.title" :visible.sync="saveorupdate.saveorupdate_dialog_show">
      <el-form ref="form-data" :rules="rules" :model="saveorupdate.form" label-position="right" label-width="80px">
        <el-input v-model="saveorupdate.form.pkid" v-show="false"></el-input>
        <el-form-item label="字典编码" prop="dict_code">
          <el-input v-model="saveorupdate.form.dict_code" autocomplete="off" :disabled="saveorupdate.update"></el-input>
        </el-form-item>
        <el-form-item label="字典名称" prop="dict_name">
          <el-input v-model="saveorupdate.form.dict_name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="dict_desc">
          <el-input v-model="saveorupdate.form.dict_desc" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="state">
          <el-radio-group v-model="saveorupdate.form.state">
            <el-radio :label="item.dict_key" v-for="(item, key, index) in dictionary.state" :key="index">{{item.dict_value}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="saveorupdate.saveorupdate_dialog_show = false">取 消</el-button>
        <el-button type="primary" @click="submitData">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDictByPage, insertDict, updateDict, deleteDict } from "@/api/system/dict";

export default {
  data() {
    return {
      table_datas: null,
      table_loading: true,
      page: {
        total: 0,
        offset: 1,
        limit: 10
      },
      search: {
        dict_code: "",
        dict_name: ""
      },
      selected: [],
      saveorupdate: {
        saveorupdate_dialog_show: false,
        update: false,
        title: '对话框',
        form: {
          pkid: '',
          dict_code: '',
          dict_name: '',
          dict_desc: '',
          state: ""
        },
        new_form: {
          pkid: '',
          dict_code: '',
          dict_name: '',
          dict_desc: '',
          state: ""
        }
      },
      rules: {
        dict_code: [
          { required: true, message: '请输入字典编码', trigger: 'blur' }
        ],
        dict_name: [
          { required: true, message: '请输入字典名称', trigger: 'blur' }
        ],
        state: [
          { required: true, message: '请选择状态' }
        ]
      },
      dictionary: {
        state: [
          { dict_code: 'constant_state', dict_key: '1', dict_value: '正常', tag_type: 'success' }, 
          { dict_code: 'constant_state', dict_key: '2', dict_value: '禁用', tag_type: 'danger' }
        ]
      }
    };
  },
  created() {
    this.listDatas(); // 查询数据
  },
  methods: {
    formatter(row, column) {
      return this.dictionary.state.find(function(dict) {
        return dict.dict_key = row.state
      });
    },
    listDatas() {
      this.table_loading = true;
      listDictByPage(this.search).then(result => {
        this.table_datas = result.datas;
        this.page.total = result.total;
        this.table_loading = false;
      });
    },
    handleSizeChange(value) {
      this.page.limit = value;
      this.listDatas();
    },
    handleCurrentChange(value) {
      this.page.offset = value;
      this.listDatas();
    },
    handleSelectionChange(value) {
      this.selected = value;
    },
    updateData(data) {
      if(data) {
        this.saveorupdate.title = '修改系统常量';
        this.saveorupdate.form = Object.assign({}, data);
        this.saveorupdate.update = true;
      } else {
        this.saveorupdate.title = '新增系统常量';
        this.saveorupdate.form = Object.assign({}, this.saveorupdate.new_form)
        this.saveorupdate.update = false;
      }
      this.saveorupdate.saveorupdate_dialog_show = true;
      this.$nextTick(()=>{
        this.$refs['form-data'].resetFields();
      })
    },
    submitData() {
      let _this = this;
      this.$refs['form-data'].validate((valid) => {
          if (valid) {
            if(_this.saveorupdate.update) {
              updateDict(_this.saveorupdate.form).then(result => {
                this.$message({
                  type: "success",
                  message: "修改成功!"
                });
                _this.saveorupdate.saveorupdate_dialog_show = false;
                _this.listDatas();
              }).catch(err => {
                this.$message.error(err);
              });
            } else {
              insertDict(_this.saveorupdate.form).then(result => {
                this.$message({
                  type: "success",
                  message: "新增成功!"
                });
                _this.saveorupdate.saveorupdate_dialog_show = false;
                _this.listDatas();
              }).catch(err => {
                this.$message.error(err);
              });
            }
          } else {
            return false;
          }
        });
    },
    deleteData() {
      let _this = this;
      if (_this.selected.length === 0) {
        this.$message({
          message: "请至少选择一条待删除的数据",
          type: "warning"
        });
      } else {
        this.$confirm("此操作将永久删除选中的数据, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            let ids = [];
            _this.selected.forEach(row => {
              ids.push(row.pkid);
            });
            deleteDict(ids).then(result => {
              this.$message({
                type: "success",
                message: "删除成功!"
              });
              _this.listDatas();
            });
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除"
            });
          });
      }
    }
  }
};
</script>

<style scoped>
.edit-input {
  padding-right: 100px;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
</style>
