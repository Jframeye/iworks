<template>
  <div class="logo">
    <transition name="fade">
      <span v-if="collapsed" class="logo-png" key="0">
        <img src="/static/images/logo/min-logo.png" width="40" />
      </span>
      <span v-if="!collapsed" class="logo-png" key="0">
        <img src="/static/images/logo/max-logo.png" width="110" />
      </span>
    </transition>
  </div>
</template>

<script>
export default {
  name: "logo",
  data() {
    return {};
  },
  props: {
    collapsed: {
      type: Boolean
    }
  },
  computed: {},
  created() {},
  computed: {},
  methods: {}
};
</script>

<style scoped="scoped" lang="scss">
.fade-leave-active {
  transition: opacity 0.2s;
}
.fade-enter-active {
  transition: opacity 2.5s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}
.logo {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  // width: 230px;
  height: 64px;
  line-height: 25px;
  background: #002140;
  color: #fdfdfd;
  text-align: center;
  font-size: 20px;
  font-weight: 600;
  overflow: hidden;
  box-sizing: border-box;
}
.logo-png {
  color: #409eff;
  font-size: 28px;
}
</style>