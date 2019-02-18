<template>
  <div>
    <el-row>加密测试: 叶宏梁</el-row>
    <el-row>MD5: {{msg}} ---> {{_md5}}</el-row>
    <el-row>AES: {{msg}} ---> {{aes_e(msg)}}</el-row>
  </div>
</template>

<script>
  import * as crypto from '@/utils/crypto'

  export default {
    name: 'infos',
    data() {
      return {
        msg: 'Welcome to Your Vue.js App',
        _md5: '',
        _aes: ''
      }
    },
    created() {
      this._md5 = this.md5(this.msg)
    },
    methods: {
      md5: function (str) {
        return crypto.md5(str)
      },
      aes_e: function (str) {
        return crypto.aes_encrypt(str)
      },
      aes_d: function (str) {
        return crypto.aes_encrypt(str)
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
