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
    <el-table :data="table_datas" v-loading.body="table_loading" border fit highlight-current-row style="width: 100%">
      <el-table-column type="selection" width="55" align="center"></el-table-column>

      <el-table-column align="center" label="标题" show-overflow-tooltip>
        <template slot-scope="scope">
          <router-link class="link-type" :to="'/system/users/detail/' + scope.row.id">
            <span>{{ scope.row.title }}</span>
          </router-link>
        </template>
      </el-table-column>

      <el-table-column align="center" label="作者">
        <template slot-scope="scope">
          <span>{{scope.row.author}}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="状态">
        <template slot-scope="scope">
          <span>{{scope.row.state}}</span>
        </template>
      </el-table-column>

      <el-table-column label="创建时间">
        <template slot-scope="scope">
          <span>{{scope.row.create_time}}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button-group>
            <router-link :to="'/system/users/update/' + scope.row.id">
              <el-button type="primary" size="small" icon="el-icon-edit"></el-button>
            </router-link>
            <el-button type="danger" size="small" icon="el-icon-delete"></el-button>
          </el-button-group>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page.offset"
        :page-sizes="[10,20,30, 50]" :page-size="page.limit" layout="total, sizes, prev, pager, next, jumper" :total="page.total">
      </el-pagination>
    </div>

  </div>
</template>

<script>
import { listUserByPage } from "@/api/system/user";

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
      shrinkBox: false,
      search: {
        user_name: ''
      }
    };
  },
  created() {
    this.listDatas();
  },
  methods: {
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
    togggeSearchBox() {
      this.shrinkBox = !this.shrinkBox;
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
