<template>
  <div class="demo-type">
      <el-avatar :size="180" src="https://empty" @error="errorHandler" class="avatar">
        <img src="../../assets/img/AIOT_FV.png" style="padding-left: 12px;"/>
      </el-avatar>
    
  </div>
</template>
<script>
  export default {
    methods: {
      errorHandler() {
        return true
      }
    }
  }
</script>
<style scoped>
  .avatar{
    margin-top: 10px;
    margin-left: 10px;
  }
  img{
    margin: 0;
    padding: 0;
    width: 180px;
    height: 180px;
  }
</style>