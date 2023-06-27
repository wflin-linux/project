<template>
  <el-menu
    :default-active="activeIndex"
    mode="horizontal"
    @select="handleSelect"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
    class="el-menu"
  >
    <el-menu-item index="1">
      <router-link to="/index">首页</router-link>
    </el-menu-item>
    <el-menu-item index="2">
      <router-link to="/announcement">公告</router-link>
    </el-menu-item>
    <el-menu-item index="3">
      <router-link to="/competition">竞赛</router-link>
    </el-menu-item>
    <el-menu-item index="4">
      <router-link to="/login">登陆/注册</router-link>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  data() {
    return {
      activeIndex: "1",
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      this.activeIndex = key
      console.log(key, keyPath);
    },
  },
};
</script>


<style>
  .el-menu{
    margin: 0;
    padding: 0;
  }
</style>