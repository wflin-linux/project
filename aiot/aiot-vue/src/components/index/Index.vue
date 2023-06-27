<template>
  <div class="gloalDiv">
    <el-container>
      <el-header style="padding: 0">
        <Navbar2></Navbar2>
      </el-header>
      <el-container>
        <el-aside width="400px" style="margin-top: 20px">
          <div style="height: 200px; background-color: burlywood">
            <Avatar></Avatar>
          </div>
          <Date></Date>
        </el-aside>

        <el-main style="overflow: hidden">
          <div style="height: 2%"></div>

          <Homeintr></Homeintr>
          <Homepro></Homepro>
          <Homepropaganda></Homepropaganda>
          <Homenews></Homenews>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>


<script>
import Navbar from "../pubilc/Navbar.vue";
import Avatar from "../pubilc/Avatar.vue";
import Date from "../pubilc/Date.vue";
import Homeintr from "../home/home.introduce.vue";
import Homepro from "../home/home.products.vue";
import Homepropaganda from "../home/home.propaganda.vue";
import Homenews from "../home/home.other.vue";
import Navbar2 from "../pubilc/Navbar2.vue";
export default {
  data() {
    return {};
  },
  components: {
    Date,
    Avatar,
    Navbar,
    Homeintr,
    Homepro,
    Homepropaganda,
    Homenews,
    Navbar2,
  },
  methods: {},
};
</script>

<style lang="less" scoped>
</style>
