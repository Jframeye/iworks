<template>
  <Layout class="main">
    <Sider hide-trigger collapsible :width="256" :collapsed-width="64" v-model="settings.collapsed">
      <side-menu accordion :active-name="$route.name" :collapsed="settings.collapsed" @on-select="turnToPage" :menu-list="menuList">
        <!-- 需要放在菜单上面的内容，如Logo，写在side-menu标签内部，如下 -->
        <div class="logo-con">
          <img v-show="!settings.collapsed" src="../../assets/images/logo/max-logo.png" key="max-logo" />
          <img v-show="settings.collapsed" src="../../assets/images/logo/min-logo.png" key="min-logo" />
        </div>
      </side-menu>
    </Sider>
    <Layout>
      <Header class="header-con">
        <header-bar :collapsed="settings.collapsed" @on-collapsed-change="handleCollapsedChange">
          <!-- 在此处添加其他按钮，比如消息 -->
        </header-bar>
      </Header>
      <Content>
        <Layout>
          <div class="tag-nav-wrapper">
            <tags-nav :value="$route" @input="handleClick" :list="tagNavList" @on-close="handleCloseTag"/>
          </div>
          <Content class="content-wrapper">
            <keep-alive :include="cacheList">
              <router-view/>
            </keep-alive>
          </Content>
        </Layout>
      </Content>
    </Layout>
  </Layout>
</template>
<script>
import { mapMutations, mapActions } from "vuex";
import SideMenu from "./components/side-menu";
import HeaderBar from './components/header-bar'
import TagsNav from './components/tags-nav'
// import Fullscreen from './components/fullscreen'
// import Language from './components/language'


export default {
  name: "layout",
  components: {
    SideMenu,
    HeaderBar,
    TagsNav
  },
  data() {
    return {
      settings: {
        collapsed: false,
      }
    };
  },
  computed: {
    menuList () {
      return this.$store.getters.menuList
    },
    tagNavList () {
      return this.$store.state.app.tagNavList
    },
    cacheList () {
      return this.tagNavList.length ? this.tagNavList.filter(item => !(item.meta && item.meta.notCache)).map(item => item.name) : []
    }
  },
  methods: {
    ...mapMutations([
      'setBreadCrumb',
      'setTagNavList',
      'addTag',
      'setLocal'
    ]),
    ...mapActions([
      'handleLogin'
    ]),
    turnToPage (name) {
      if (name.indexOf('isTurnByHref_') > -1) {
        window.open(name.split('_')[1])
        return
      }
      this.$router.push({
        name: name
      })
    },
    handleCollapsedChange (state) {
      this.collapsed = state
    },
    handleCloseTag (res, type, name) {
      const nextName = getNextName(this.tagNavList, name)
      this.setTagNavList(res)
      if (type === 'all') this.turnToPage('home')
      else if (this.$route.name === name) this.$router.push({ name: nextName })
    },
    handleClick (item) {
      this.turnToPage(item.name)
    }
  },
  watch: {
    '$route' (newRoute) {
      this.setBreadCrumb(newRoute.matched)
      this.setTagNavList(getNewTagList(this.tagNavList, newRoute))
    }
  },
  mounted() {
    /**
     * @description 初始化设置面包屑导航和标签导航
     */
    this.setTagNavList()
    this.addTag(this.$store.state.app.homeRoute)
    this.setBreadCrumb(this.$route.matched)
    // 设置初始语言
    // this.setLocal(this.$i18n.locale)
    // 文档提示
    /* this.$Notice.open({
      title: '想快速上手，去看文档吧',
      duration: 0,
      render: (h) => {
        return h('p', {
          style: {
            fontSize: '13px'
          }
        }, [
          '点击',
          h('a', {
            attrs: {
              href: 'https://lison16.github.io/iview-admin-doc/#/',
              target: '_blank'
            }
          }, 'iview-admin2.0文档'),
          '快速查看'
        ])
      }
    }) */
  }
};
</script>

<style lang="less" scoped>
@import "./layout.less";
</style>