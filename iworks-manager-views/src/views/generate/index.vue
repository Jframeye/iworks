<template>
  <div class="app-container">
    <el-form ref="form" :model="sizeForm" label-width="80px" size="mini">
      <el-form-item label="数据库">
        <el-select v-model="search.database" placeholder="请选择数据库">
          <el-option label="区域一" value="shanghai"></el-option>
          <el-option label="区域二" value="beijing"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="表名">
        <el-input v-model="search.table_name"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="table_datas" v-loading.body="table_loading" border fit highlight-current-row style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left">
            <div v-for="column in props.row.column" :key="column.column_name">
              <span>{{column.column_name}}</span>
              <span>{{column.data_type}}</span>
              <span>{{column.column}}</span>
              <span>{{column.comments}}</span>
            </div>
          </el-form>
        </template>
      </el-table-column>
      
      <el-table-column type="selection" width="55"></el-table-column>

      <el-table-column align="center" label="数据库" show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{ scope.row.database }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="表名称" show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{ scope.row.table_name }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="表说明" show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{ scope.row.table_comment }}</span>
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
            <el-button type="primary" size="small" icon="el-icon-edit"></el-button>
            <el-button type="danger" size="small" icon="el-icon-delete"></el-button>
            <el-button type="info" size="small" icon="el-icon-download"></el-button>
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
import { listDatabse, listByPage } from "@/api/generate";

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
        database: '',
        table_name: ''
      }
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
