<template>
  <el-row>
    <div v-for="(item, index) in dataList" :key="index">
      <el-col style="padding: 0 25px; margin: 10px 0">
        <el-card shadow="hover" :body-style="{ padding: '20px' }">
          <div style="padding: 14px">
            <span>{{ item.announcementContent.content }}</span>
          </div>
          <div
            v-for="(imgItem, i) in item.announcementImageList"
            :key="i"
            class="divBox"
          >
            <img :src="baseURL + imgItem.imgPath" class="image" />
          </div>
        </el-card>
      </el-col>
    </div>
  </el-row>
</template>

<script>
export default {
  data() {
    return {
      page: 1,
      limit: 10,
      dataList: [],
      baseURL: this.$http.defaults.baseURL,
    };
  },
  created() {
    this.getData();
  },
  methods: {
    async getData() {
      const { data: res } = await this.$http({
        url: "/announce?page=" + this.page + "&limit=" + this.limit,
        method: "get", //get
        headers: {
          token: localStorage.getItem("token"),
          "Content-Type": "application/json;charset=utf-8",
        },
      });
      if (res.code == 0) {
        console.log(res.data);
        this.dataList = res.data;
      }
    },
  },
};
</script>

<style scoped>
.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 70%;
  display: inline-block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.divBox {
  text-align: center;
}

span {
  font-size: 15px;
  text-indent: 2em;
}

.clearfix:after {
  clear: both;
}
</style>
