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
          <el-row :gutter="20">
            <el-col :span="8" v-for="(o, index) in categoryList" :key="index">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span class="header">{{ o.name }}</span>
                </div>
                <div
                  v-for="(c, index) in o.competitions"
                  :key="index"
                  class="text item"
                >
                  <div @click="handleClick(c.id)">
                    <a class="title" src="javascript:;">{{ c.title }}</a>
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Navbar from '../pubilc/Navbar.vue'
import Date from '../pubilc/Date.vue'
import Avatar from '../pubilc/Avatar.vue'
export default {
  data() {
    return {
      categoryList: [],
    }
  },
  components: {
    Navbar,
    Date,
    Avatar,
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.$http
        .get('/category/fonts/list', {
          headers: {
            token: localStorage.getItem('token'),
          },
        })
        .then((result) => {
          if (result.data.code === 200) {
            this.categoryList = result.data.data
          }
        })
        .catch((err) => {})
    },
    handleClick(id) {
      this.$router.push({
        path: '/detail',
        query: {
          id: id,
        },
      })
    },
  },
}
</script>

<style lang="less" scoped>
.el-col {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
  border-radius: 4px;
}

.box-card {
  min-height: 240px;
}

.box-card:hover {
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%) !important;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.header {
  font-size: 14px;
}

.title {
  cursor: pointer;
}

.title:hover {
  color: #f60;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: '';
}

.clearfix:after {
  clear: both;
}
</style>
