<template>
  <el-form class="form-container">
      <el-row :gutter="10" style="margin-left: 0">
          <el-col :span="18" class="left-side box-container" style="padding: 20px;">
            <el-form-item style="margin-bottom: 20px;" prop="title">
              <vinput name="name" v-model="form.title" required :maxlength="100">
                标题
              </vinput>
            </el-form-item>
            <div class="link">
              <span>链接：http://localhost:8080</span>
            </div>
            <el-form-item style="margin-bottom: 40px;" label-width="45px" label="摘要:">
              <el-input type="textarea" class="article-textarea" :rows="1" autosize placeholder="请输入内容" v-model="form.summary">
              </el-input>
              <span class="word-counter" v-show="summaryCount">{{summaryCount}}字</span>
            </el-form-item>

            <div class="">
              <tinymce :height=400 ref="editor" v-model="form.content" />
            </div>
          </el-col>
          <el-col :span="6">
            <div class="right-side box-container">
              <svg-icon svgname="guide"></svg-icon>
              <span slot="title">发布</span>
            </div>
            <div class="right-side box-container">
              <svg-icon svgname="guide"></svg-icon>
              <span slot="title">分类</span>
            </div>
            <div class="right-side box-container">
              <svg-icon svgname="guide"></svg-icon>
              <span slot="title">标签</span>
              <hr>
            </div>
          </el-col>
        </el-row>
    </el-form>
</template>

<script>
import Tinymce from "@/components/tinymce";
import Vinput from "@/components/vinput";
import Sticky from "@/components/sticky"; // 粘性header组件
import Dropdown from "@/components/dropdown";

export default {
  name: "editor",
  components: {
    Tinymce,
    Vinput,
    Sticky,
    Dropdown
  },
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      form: {
        title: "",
        summary: "",
        content: ""
      },
      loading: false
    };
  },
  computed: {
    summaryCount() {
      return this.form.summary.length;
    }
  },
  created() {},
  methods: {
    fetchData(id) {
      fetchArticle(id)
        .then(response => {
          this.postForm = response.data;
          // Just for test
          this.postForm.title += `   Article Id:${this.postForm.id}`;
          this.postForm.content_short += `   Article Id:${this.postForm.id}`;
        })
        .catch(err => {
          console.log(err);
        });
    },
    submitForm() {
      this.postForm.display_time = parseInt(this.display_time / 1000);
      console.log(this.postForm);
      this.$refs.postForm.validate(valid => {
        if (valid) {
          this.loading = true;
          this.$notify({
            title: "成功",
            message: "发布文章成功",
            type: "success",
            duration: 2000
          });
          this.postForm.status = "published";
          this.loading = false;
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    draftForm() {
      if (
        this.postForm.content.length === 0 ||
        this.postForm.title.length === 0
      ) {
        this.$message({
          message: "请填写必要的标题和内容",
          type: "warning"
        });
        return;
      }
      this.$message({
        message: "保存成功",
        type: "success",
        showClose: true,
        duration: 1000
      });
      this.postForm.status = "draft";
    },
    getRemoteUserList(query) {
      userSearch(query).then(response => {
        if (!response.data.items) return;
        this.userListOptions = response.data.items.map(v => v.name);
      });
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
@import "src/styles/mixin.scss";
.form-container {
  padding: 20px;
  .left-side {
    padding: 15px;
    border-radius: 10px 0 0 10px;
    .link {
      padding: 5px 0;
      margin-bottom: 20px;
      font-size: 14px;
      color: #606266;
    }
    .word-counter {
      width: 40px;
      position: absolute;
      right: -10px;
      top: 0px;
    }
    .editor-container {
      min-height: 500px;
      margin: 0 0 30px;
      .editor-upload-btn-container {
        text-align: right;
        margin-right: 10px;
        .editor-upload-btn {
          display: inline-block;
        }
      }
    }
  }
  .right-side {
    margin-left: 0;
    margin-bottom: 10px;
    border-radius: 0 10px 10px 0;
  }
  .box-container {
    padding: 10px;
    background-color: #fff;
  }
}
</style>
