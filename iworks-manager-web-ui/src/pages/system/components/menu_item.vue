<template>
  <el-row class="row list">
    <template v-for="(item, index) in menu_list">
      <el-row class="row level" v-if="level == 0 || (item.data && parent.expand)" @mouseenter.native="mouseEnter($event,item)" @mouseleave.native="mouseLeave($event,item)">
        <template v-if="level>0">
          <el-col :span="2" class="col level-h-dashed" v-for="n in level" :key="n">
            &nbsp;
          </el-col>
        </template>
        <el-col class="col level-icon" :span="4" @click.native="item.expand = !item.expand">
          <span :class="item.expand ? 'fa fa-folder-open' : 'fa fa-folder'"></span>
        </el-col>
        <el-col class="col level-title" :span="20-2*level" :style="{}" @click.native="item.expand=!item.expand">
          {{item.name}}
        </el-col>
        <div class="col tools" v-show="item.show_tools">
          <el-tooltip effect="light" content="新建子菜单">
            <i class="el-icon-plus" style="color:#909399;"></i>
          </el-tooltip>
          <el-tooltip effect="light" content="修改菜单组">
          <i class="el-icon-edit" style="color:#409EFF;"></i>
          </el-tooltip>
          <el-tooltip effect="light" content="删除菜单组" placement="right">
            <i class="el-icon-delete" style="color:#f56c6c;"></i>
          </el-tooltip>
        </div>
      </el-row>
      <el-collapse-transition>
        <list v-if="item.data && item.data.length>0" :level="level+1" :data="item.data" :parent="item"></list>
      </el-collapse-transition>
      <el-row class="row level" v-if="!item.data && parent.expand" @mouseenter.native="mouseEnter($event,item)" @mouseleave.native="mouseLeave($event,item)">
        <template v-if="level>0">
          <el-col :span="2" class="col level-h-dashed" v-for="n in level" :key="n">
            &nbsp;
          </el-col>
        </template>
        <el-col class="col level-text" :span="4" :style="{ color: item.type == 1 ? '#1ab394' : '#f8ac59'}">
          {{ item.type == 1 ? '菜单' : '按钮'}}
        </el-col>
        <el-col class="col level-title" :span="20-2*level">
          {{item.name}}
        </el-col>
        <div class="col tools" v-show="item.show_tools">
          <el-tooltip effect="light" :content="item.type == 1 ? '删除菜单' : '删除按钮'" placement="right">
            <i class="el-icon-delete" style="color:#f56c6c;"></i>
          </el-tooltip>
        </div>
      </el-row>
    </template>
  </el-row>
</template>

<script>
export default {
  name: 'list',
  props:{
    level: {
      type: Number,
      default: 0
    },
    data: Array,
    parent: Object
  },
  data () {
    return {
    }
  },
  computed: {
    menu_list:function () {
      if(this.level === 0) {
        return this.data
      } else {
        return this.data
      }
    }
  },
  methods: {
    mouseEnter: function (event, item) {
      item.show_tools = true
    },
    mouseLeave: function (event, item) {
      item.show_tools = false
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  .list { cursor: pointer;white-space: nowrap;background-color: white; }
  .list .level {height: 35px;line-height: 35px;white-space: nowrap; }
  .level-h-dashed { border-right: 1px lightgray dashed}
  .level-icon { text-align: center;white-space: nowrap; }
  .level-icon span { color:#c7c7c7;font-size: 13px; }
  .level-text { font-size: 13px; margin: 0; padding: 0; line-height: 35px; text-align: center; }
  .level-title { margin: 0; font-size: 14px; color: gray; white-space: nowrap; padding: 0; text-overflow: ellipsis;overflow: hidden; text-decoration: none }
  .tools { height: 35px;white-space: nowrap;text-align: center;position: absolute;top: 0px;right: 0px; }
  .tools i { font-size: 12px;padding: 3px;background-color: white;font-weight: 900; }
</style>
