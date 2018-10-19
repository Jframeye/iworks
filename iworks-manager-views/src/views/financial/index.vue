<template>
  <div class="app-container">
    <div class="search-box">
      <el-row :class="shrinkBox ? 'shrink' : ''">
        <el-button type="text" style="margin-left: 5px;" @click="togggeSearchBox">查询
          <i class="el-icon-arrow-up" style="margin-left: 5px;" v-show="shrinkBox"></i>
          <i class="el-icon-arrow-down" style="margin-left: 5px;" v-show="!shrinkBox"></i>
        </el-button>
      </el-row>
      <el-row style="padding: 10px 15px 0;" v-show="shrinkBox">
        <el-form :inline=true size="mini" v-model="search">
          <el-form-item label="账号">
            <el-input style="width: 250px;" v-model="search.user_name" placeholder="请输入用户账号"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-icon-search" round>查询</el-button>
          </el-form-item>
        </el-form>
      </el-row>
    </div>
    <tree-table :data="table_datas" first="title" border>
      <el-table-column label="排序" width="60">
        <template slot-scope="scope">
          <span>{{scope.row.order}}</span>
        </template>
      </el-table-column>
      <el-table-column label="请求地址" width="240">
        <template slot-scope="scope">
          <span>{{scope.row.url}}</span>
        </template>
      </el-table-column>
      <el-table-column label="类型" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.type == 1">目录</el-tag>
          <el-tag type="success" v-if="scope.row.type == 2">菜单</el-tag>
          <el-tag type="warning" v-if="scope.row.type == 3">按钮</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="状态" width="80">
        <template slot-scope="scope">
          <el-tag type="success" v-if="scope.row.state == 1">显示</el-tag>
          <el-tag type="danger" v-if="scope.row.state == 2">隐藏</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="权限标识" width="180">
        <template slot-scope="scope">
          <span>{{scope.row.permission}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="260">
        <template slot-scope="scope">
          <el-button-group>
            <el-button type="primary" size="small" icon="el-icon-edit" @click="updateMenu(scope.row)">修改</el-button>
            <el-button type="danger" size="small" icon="el-icon-delete" @click="deleteMenu(scope.row.id)">删除</el-button>
          </el-button-group>
        </template>
      </el-table-column>
    </tree-table>

    <div class="pagination-container">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page.offset"
        :page-sizes="[10,20,30, 50]" :page-size="page.limit" layout="total, sizes, prev, pager, next, jumper" :total="page.total">
      </el-pagination>
    </div>

    <el-dialog :title="title" :visible.sync="showDialog">
      <el-form :model="dialog_form" size="mini">
        <el-form-item label="上级菜单">
          <el-tree :load="listParentMenu" lazy show-checkbox @check-change="handleCheckChange"></el-tree>
        </el-form-item>
        <el-form-item label="菜单类型">
          <el-radio-group v-model="dialog_form.type">
            <el-radio :label="1">目录</el-radio>
            <el-radio :label="2">菜单</el-radio>
            <el-radio :label="3">按钮</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="菜单名称">
          <el-input v-model="dialog_form.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="请求地址">
          <el-input v-model="dialog_form.url" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="权限标识">
          <el-input v-model="dialog_form.permission" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="显示顺序">
          <el-input-number v-model="dialog_form.order" controls-position="right" :min="1"></el-input-number>
        </el-form-item>
        <el-form-item label="菜单状态">
          <el-radio v-model="dialog_form.state" label="1">显示</el-radio>
          <el-radio v-model="dialog_form.state" label="2">隐藏</el-radio>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="showDialog = false">取 消</el-button>
        <el-button type="primary" @click="submitData">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMenuByPage } from "@/api/system/menu";
import treeTable from "@/components/treetable/index.vue";

export default {
  components: { treeTable },
  data() {
    return {
      table_datas: [],
      table_loading: true,
      page: {
        total: 0,
        offset: 1,
        limit: 10
      },
      shrinkBox: false,
      search: {
        user_name: ""
      },
      showDialog: false,
      title: "",
      dialog_form: {
        parent_id: "",
        title: "",
        order: "",
        type: "",
        url: "",
        permission: "",
        state: ""
      }
    };
  },
  created() {
    this.listDatas();
  },
  methods: {
    listDatas() {
      this.table_loading = true;
      listMenuByPage(this.search).then(result => {
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
    togggeSearchBox() {
      this.shrinkBox = !this.shrinkBox;
    },
    updateMenu(data) {
      this.showDialog = true;
      this.title = "修改菜单信息";
      this.dialog_form = data;
    },
    listParentMenu() {
      return [];
    },
    submitData() {
      this.showDialog = false;
      this.title = "";
    },
    deleteMenu(id) {
      this.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$message({
            type: "success",
            message: "删除成功!"
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
