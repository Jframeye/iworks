<template>
  <div :class="current" class="dot"></div>
</template>

<script>
export default {
  name: 'dot',
  props:["type"],
  data () {
    return {
      'current': this.type ? this.type : 'success'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .dot { width: 8px; height: 8px; border-radius: 50%; margin: 10px; display: inline-flex; justify-content: center; align-items: center; }
  .success { background-color: #67c23a; }
  .warning { background-color: #e6a23c; }
  .danger { background-color: #f56c6c; }
  @keyframes scale {
    0% {
      transform: scale(1);
    }
    50%,
    75% {
      transform: scale(2.5);
    }
    78%, 100% {
      opacity: 0;
    }
  }
  .dot:before { content: ''; width: 8px; height: 8px; border-radius: 50%; opacity: 0.7; animation: scale 2s infinite cubic-bezier(0, 0, 0.49, 1.02); animation-delay: 200ms; transition: 0.5s all ease; transform: scale(1); }
  .success:before { background-color: #67c23a; }
  .warning:before { background-color: #e6a23c; }
  .danger:before { background-color: #f56c6c; }
</style>
