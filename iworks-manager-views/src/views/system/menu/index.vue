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
    <el-row>
      <el-col :span="10">
        <el-tree ref="tree" :data="table_datas" node-key="id" :expand-on-click-node="false" style="width: 80%;">
          <span slot-scope="{ node, data }" style="width: 100%; line-height: 30px;">
            {{ data.title }}
            <span style="float: right;">
              <el-button type="text" size="mini" icon="el-icon-view" @click="showNode(data, true)">查看</el-button>
              <el-button type="text" size="mini" icon="el-icon-plus" @click="showNode(data, false)">新增</el-button>
              <el-button type="text" size="mini" icon="el-icon-delete" @click="deleteNode(node, data)">删除</el-button>
              <el-button-group>
              </el-button-group>
            </span>
          </span>
        </el-tree>
      </el-col>
      <el-col :span="14" v-show="show_menu">
        <el-form :model="menu_form" size="mini">
          <el-input v-model="menu_form.id" v-show="false"></el-input>
          <el-input v-model="menu_form.parent_id" v-show="false"></el-input>
          <el-form-item label="上级节点">
            <span v-if="menu_form.parent_id">{{menu_form.parent_title}}</span>
            <span v-else>根目录</span>
          </el-form-item>
          <el-form-item label="节点类型">
            <span v-if="menu_form.type === 1">目录</span>
            <span v-if="menu_form.type === 2">菜单</span>
            <span v-if="menu_form.type === 3">按钮</span>
          </el-form-item>
          <el-form-item label="节点名称">
            <el-input v-model="menu_form.title" autoComplete="off" :readonly="!edit_menu"></el-input>
          </el-form-item>
          <el-form-item label="请求地址">
            <el-input v-model="menu_form.url" autoComplete="off" :readonly="!edit_menu"></el-input>
          </el-form-item>
          <el-form-item label="权限标识">
            <el-input v-model="menu_form.permission" autoComplete="off" :readonly="!edit_menu"></el-input>
          </el-form-item>
          <el-form-item label="节点状态">
            <el-radio v-model="menu_form.state" label="1" :disabled="!edit_menu">显示</el-radio>
            <el-radio v-model="menu_form.state" label="2" :disabled="!edit_menu">隐藏</el-radio>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="edit_menu = true" v-if="!edit_menu">编辑</el-button>
            <el-button type="primary" @click="submitData" v-if="edit_menu">保存</el-button>
            <el-button @click="cancelEdit" v-if="edit_menu">取消</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { listMenuByPage, updateMenu, deleteMenu } from "@/api/system/menu";
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
      show_menu: false, // 显示菜单内容
      edit_menu: false, // 更新菜单内容
      menu_form: {
        id: "",
        parent_id: "",
        parent_title: "",
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
    togggeSearchBox() {
      this.shrinkBox = !this.shrinkBox;
    },
    showNode(data, exist) {
      if (this.edit_menu) {
        // 说明表单已经打开
        this.$confirm("此操作将导致正在编辑的数据丢失, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.logicalProcessing(data, exist);
        });
      } else {
        this.logicalProcessing(data, exist);
      }
    },
    logicalProcessing(data, exist) {
      // 表单逻辑处理
      this.show_menu = true;
      if (exist) {
        this.edit_menu = false;
        this.menu_form = data;
      } else {
        if (data.type === 3) {
          this.$message({
            type: "warning",
            message: "按钮级别不能增加子目录！"
          });
          return;
        }
        this.$refs.tree.store.nodesMap[data.id].expanded = true; // 展开当前节点
        this.edit_menu = true;
        this.menu_form = {
          parent_id: data.id,
          parent_title: data.title,
          type: data.type + 1
        };
      }
    },
    cancelEdit() {
      this.edit_menu = false;
      if (!this.menu_form.id) {
        this.show_menu = false;
      }
    },
    submitData() {
      updateMenu(this.menu_form).then(result => {
        this.listDatas();
        this.edit_menu = false;
        this.$message({
          type: "success",
          message: "更新成功！"
        });
      });
    },
    deleteNode(node, data) {
      if (data.children && data.children.length > 0) {
        this.$message({
          type: "warning",
          message: "请先删除子节点！"
        });
        return;
      }
      this.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        deleteMenu(data.id).then(result => {
          debugger
          const parent = node.parent;
          const children = parent.data.children || parent.data;
          const index = children.findIndex(d => d.id === data.id);
          children.splice(index, 1);
          this.$message({
            type: "success",
            message: "删除成功！"
          });
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
