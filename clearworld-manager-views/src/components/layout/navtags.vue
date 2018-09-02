<template>
  <div class="tags-container">
    <!-- tag盒子 -->
    <div class="tags-box" ref="navtagbox">
      <div class="tags-list" ref="navtagbar" @mousewheel="hadelMousewheel">
        <div ref="navtagdiv" class="tag-item" :class="{'is-active':current == item.path}" :name="item.path" 
             v-for="(item, index) in navtags" :key="index" 
             @contextmenu.prevent="openMenu(item, $event)"
             @click="open(item)">
          <span class="el-icon-location-outline"></span>
          <span class="tag-text">{{item.title}}</span>
          <i class="el-icon-close" @click.stop="close(item)" v-if="item.close"></i>
        </div>
      </div>
      <el-dropdown class="tags-menu pull-right">
        <el-button type="primary" size="mini"> 更多 <i class="el-icon-arrow-down el-icon--right"></i>
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="closeOthers">关闭其他</el-dropdown-item>
          <el-dropdown-item @click.native="closeAll">关闭全部</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>
<script>
import { resolvePath } from "@/common/utils";

export default {
  name: "navtags",
  data() {
    return {
      visible: false,
      tagBodyLeft: 0,
      lock: false,
      startX: 0,
      startY: 0,
      endX: 0,
      endY: 0,
      top: 0,
      left: 0,
      selectedTag: {}
    };
  },
  created() {},
  mounted() {},
  computed: {
    navtags() {
      return this.$store.getters.navtags;
    },
    activitytag() {
      return this.$store.getters.activitytag;
    },
    current: function() {
      return resolvePath(this.$route);
    }
  },
  watch: {
    $route() {
      this.addNavtag()
      this.moveToCurrent()
    }
  },
  methods: {
    addNavtag() {
      this.$store.commit("ADD_NAVTAG", this.$route);
    },
    moveToCurrent() {
      this.navtagdiv = this.$refs.navtagdiv;
      setTimeout(() => {
        this.navtagdiv.forEach((item, index) => {
          if (this.activitytag.path === item.attributes.name.value) {
            let navtag = this.navtagdiv[index];
            this.moveToView(navtag);
          }
        });
      }, 1);
    },
    moveToView(navtag) {
      // 
    },
    open(item) {
      this.$router.push({
        path: item.path
      });
    },
    openMenu(tag, e) {
      if (this.navtags.length == 1) {
        return;
      }
      this.visible = true;
      this.selectedTag = tag;
      this.left = e.clientX;
      this.top = e.clientY;
    },
    close(item) {
      let index = this.getIndex(item);
      let navtag;
      this.$store.commit("DELETE_NAVTAG", item);
      if (item.path == this.activitytag.path) {
        navtag = this.navtags[index == 0 ? index : index - 1];
        this.open(navtag);
      }
    },
    closeOthers() {
      this.$store.commit("DELETE_OTHER_NAVTAG");
    },
    closeAll() {
      this.$store.commit("DELETE_ALL_NAVTAG");
      this.$router.push({
        path: 'index'
      });
    },
    getIndex(navtag) {
      for (var index in this.navtags) {
        if (this.navtags[index].path == navtag.path) {
          return index;
        }
      }
      return -1;
    },
    hadelMousewheel(e) {
      const step = 0.8 * 90; //一个tag长度
      const boundarystart = 0,
        boundaryend =
          this.$refs.navtagbar.offsetWidth -
          this.$refs.navtagbox.offsetWidth +
          100;
      // Y>0向左滑动
      if (e.deltaY > 0 && this.tagBodyLeft >= -boundaryend) {
        this.tagBodyLeft = this.tagBodyLeft - step;
        // Y<0向右滑动
      } else if (e.deltaY < 0 && this.tagBodyLeft < boundarystart) {
        this.tagBodyLeft = this.tagBodyLeft + step;
      }
    }
  }
};
</script>
<style lang="scss" scoped>
</style>


