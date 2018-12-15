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
              <el-form-item label="账号">
                <el-input style="width: 250px;" v-model="search.user_name" placeholder="请输入用户账号"></el-input>
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
        <el-button size="mini" icon="el-icon-plus" @click="insertUser">新增</el-button>
        <el-button type="danger" size="mini" icon="el-icon-delete" @click="deleteUser">删除</el-button>
      </el-button-group>
    </div>
    <el-table size='mini' :data="table_datas" v-loading.body="table_loading" border fit highlight-current-row style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="user_code" align="center" label="编号"></el-table-column>
      <el-table-column prop="user_name" align="center" label="账号" width="200"></el-table-column>
      <el-table-column prop="nick_name" align="center" label="姓名"></el-table-column>
      <el-table-column prop="user_sex" align="center" label="性别" :formatter="formatter"></el-table-column>
      <el-table-column prop="mobile" align="center" label="手机号码"></el-table-column>

      <el-table-column align="center" label="状态">
        <template slot-scope="scope">
          <span><el-tag type="info">{{scope.row.state}}</el-tag></span>
        </template>
      </el-table-column>

      <el-table-column prop="create_time" label="创建时间" width="140"></el-table-column>

      <el-table-column align="center" label="操作" width="200">
        <template slot-scope="scope">
          <el-button-group>
            <el-button type="text" size='mini' icon="el-icon-edit" @click="updateUser(scope.row)">修改</el-button>
            <el-button type="text" size='mini' icon="el-icon-edit">权限设置</el-button>
            <el-button type="text" size='mini' icon="el-icon-edit">重置密码</el-button>
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
        <el-input v-model="saveorupdate.form.id" v-show="false"></el-input>
        <el-form-item label="名称" prop="nick_name">
          <el-input v-model="saveorupdate.form.nick_name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="user_sex">
          <el-select v-model="saveorupdate.form.user_sex" placeholder="请选择性别">
            <el-option label="男" value="1"></el-option>
            <el-option label="女" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="state">
          <el-select v-model="saveorupdate.form.state" placeholder="请选择状态">
            <el-option label="正常" value="1"></el-option>
            <el-option label="冻结" value="2"></el-option>
            <el-option label="注销" value="3"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="saveorupdate.saveorupdate_dialog_show = false">取 消</el-button>
        <el-button type="primary" @click="submitData('form-data')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listUserByPage, deleteUser } from "@/api/system/user";
import { getDict } from "@/api/system/dict";

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
        user_name: "",
        nick_name: "",
        mobile: '',
        state: ""
      },
      selected: [],
      saveorupdate: {
        saveorupdate_dialog_show: false,
        title: '对话框',
        form: {
          id: '',
          nick_name: '',
          user_sex: '',
          state: ""
        },
        new_form: {
          id: '',
          nick_name: '',
          user_sex: '',
          state: ""
        }
      },
      rules: {
        nick_name: [
          { required: true, message: '请输入用户名称', trigger: 'blur' }
        ],
        user_sex: [
          { type: 'array', required: true, message: '请选择性别', trigger: 'change' }
        ],
        state: [
          { type: 'array', required: true, message: '请选择状态', trigger: 'change' }
        ]
      },
      dictionary: {
        
      }
    };
  },
  created() {
    // 查询字典
    this.listDatas(); // 查询数据
  },
  methods: {
    formatter(row, column) {
      return this.dictionary[column.prpoerty].value;
    },
    listDatas() {
      this.table_loading = true;
      listUserByPage(this.search).then(result => {
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
    insertUser() {
      this.saveorupdate.title = '新增用户';
      this.saveorupdate.form = Object.assign({}, this.saveorupdate.new_form)
      this.saveorupdate.saveorupdate_dialog_show = true;
    },
    updateUser(data) {
      this.saveorupdate.title = '修改用户';
      this.saveorupdate.form = Object.assign({}, data)
      this.saveorupdate.saveorupdate_dialog_show = true;
    },
    submitData(form) {
      this.$refs[form].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            return false;
          }
        });
    },
    deleteUser() {
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
              ids.push(row.id);
            });
            deleteUser(ids).then(result => {
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
