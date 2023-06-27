<template>
  <div class="gloalDiv">
    <el-container>
      <el-header style="padding: 0">
        <Navbar></Navbar>
      </el-header>
      <el-container>
        <el-aside width="400px" style="margin-top: 20px">
          <div style="height: 200px; background-color: burlywood">
            <Avatar></Avatar>
          </div>
          <Date></Date>
        </el-aside>
        <el-main style="padding-right: 0; margin-right: 0">
          <Data></Data>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>


<script>
import Navbar from "../pubilc/Navbar.vue";
import Date from "../pubilc/Date.vue";
import Avatar from "../pubilc/Avatar.vue";
import Title from "./Title.vue";
import Data from "./Data.vue";
export default {
  data() {
    return {};
  },
  components: {
    Navbar,
    Title,
    Data,
    Date,
    Avatar,
  },
  methods: {},
};
</script>
<style >
</style>