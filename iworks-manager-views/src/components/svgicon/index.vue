<template>
  <icon :name="svgname" :scale="scale"></icon>
</template>

<script>
import Icon from "vue-svg-icon/Icon.vue";
export default {
  name: "svg-icon",
  components: {
    Icon
  },
  props: {
    svgname: {
      type: String
    },
    scale: {
      type: Number,
      default: 1.6
    }
  }
};
</script>

<style scoped>

</style>
