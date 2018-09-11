<template>
  <editor :is-edit='true'></editor>
</template>

<script>
import editor from "./editor/index.vue";

export default {
  name: "updateForm",
  components: { editor }
};
</script>