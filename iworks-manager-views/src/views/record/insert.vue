<template>
  <editor :is-edit='true'></editor>
</template>

<script>
import editor from "./editor/index.vue";

export default {
  name: "insertForm",
  components: { editor }
};
</script>