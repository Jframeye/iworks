<template>
  <el-row class="row">
    <transition name="component-fade" mode="out-in">
      <component :is="$store.state.type"></component>
    </transition>
  </el-row>
</template>

<script>
export default {
  name: 'demo',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .component-fade-enter-active, .component-fade-leave-active {
    transition: opacity .3s ease;
  }
  .component-fade-enter, .component-fade-leave-to {
    opacity: 0;
  }
</style>
