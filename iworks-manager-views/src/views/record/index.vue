<template>
  <div class="app-container">
    <el-table :data="table_datas" v-loading.body="table_loading" border fit highlight-current-row style="width: 100%">
      <el-table-column type="selection" width="55"></el-table-column>

      <el-table-column align="center" label="标题" show-overflow-tooltip>
        <template slot-scope="scope">
          <router-link class="link-type" :to="'/record/update/'+scope.row.id">
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
          <router-link :to="'/record/update/1'">
            <el-button type="primary" size="small" icon="el-icon-edit">Edit</el-button>
          </router-link>
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
import { listByPage } from "@/api/record";

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
      search: {}
    };
  },
  created() {
    this.listDatas();
  },
  methods: {
    listDatas() {
      this.table_loading = true;
      listByPage(this.search).then(result => {
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
