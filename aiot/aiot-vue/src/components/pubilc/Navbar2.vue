<template>
  <!-- <el-tabs
      v-model="activeName"
      @tab-click="handleClick"
      v-for="(item, index) in navList"
      :key="index"
    >
      <el-tab-pane :label="item.label" :name="item.name">{{
        item.label
      }}</el-tab-pane>
       -->
  <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="首页" name="first">用户管理</el-tab-pane>
    <el-tab-pane label="公告" name="second">配置管理</el-tab-pane>
    <el-tab-pane label="竞赛" name="third">角色管理</el-tab-pane>
  </el-tabs>
</template>
  <script>
export default {
  data() {
    return {
      navList: [
        { label: "首页", name: "first" },
        { label: "公告", name: "second" },
        { label: "竞赛", name: "third" },
      ],
      activeName: "second",
    };
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
  },
};
</script>