<template>
  <section class="app-main">
    <vue-scroll :ops="ops">
      <transition name="fade-transform" mode="out-in">
        <keep-alive :include="cachedViews">
          <router-view :key="key"></router-view>
        </keep-alive>
      </transition>
    </vue-scroll>
  </section>
</template>

<script>
export default {
  name: "mainbox",
  computed: {
    cachedViews() {
      return this.$store.state.app.cachedViews;
    },
    key() {
      return this.$route.fullPath;
    }
  },
  data() {
    return {
      ops: {
        bar: {
          background: "#d8dce5",
          keepShow: true
        }
      }
    };
  }
};
</script>

<style scoped>
.app-main {
  /*84 = navbar + tags-view = 50 +34 */
  height: calc(100vh - 84px);
  position: relative;
  overflow-y: auto;
  overflow-x: hidden;
  width: 100%;
  background-color: #eceef3;
}
</style>

