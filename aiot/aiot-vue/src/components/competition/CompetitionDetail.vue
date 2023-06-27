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
          <h2 class="title">{{ title }}</h2>
          <mavon-editor
            class="md"
            :value="htmlContent"
            :subfield="prop.subfield"
            :defaultOpen="prop.defaultOpen"
            :toolbarsFlag="prop.toolbarsFlag"
            :editable="prop.editable"
            :scrollStyle="prop.scrollStyle"
          />
          <div v-if="flag > 0" class="bottom">
            <p class="fu">附件：</p>
            <el-row :gutter="20">
              <el-col :span="4" v-for="(o, index) in files" :key="index">
                <div class="grid-content">
                  <a class="file" :href="o.filePath" target="_blank">{{
                    o.realName
                  }}</a>
                </div>
              </el-col>
            </el-row>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Navbar from '../pubilc/Navbar.vue'
import Date from '../pubilc/Date.vue'
import Avatar from '../pubilc/Avatar.vue'
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
export default {
  data() {
    return {
      // 渲染解析的数据绑定
      title: '',
      htmlContent: '暂无内容',
      flag: 0,
      files: [],
    }
  },
  components: {
    Navbar,
    Date,
    Avatar,
    mavonEditor,
  },
  computed: {
    // 解析器配置
    prop() {
      let data = {
        subfield: false, // 单双栏模式
        defaultOpen: 'preview', // edit： 默认展示编辑区域 ， preview： 默认展示预览区域
        editable: false, // 是否允许编辑
        toolbarsFlag: false,
        scrollStyle: true,
      }
      return data
    },
  },
  mounted() {
    // 从后台获取数据
    let id = this.$route.query.id
    this.getCompetition(id)
    this.getFiles(id)
  },
  methods: {
    getCompetition(id) {
      this.$http
        .get('/competition/query?id=' + id, {
          headers: {
            token: localStorage.getItem('token'),
          },
        })
        .then((result) => {
          if (result.data.code === 200) {
            this.title = result.data.data.title
            this.htmlContent = result.data.data.content
          }
        })
        .catch((err) => {})
    },
    getFiles(id) {
      this.$http
        .get('/competition/queryFile?id=' + id, {
          headers: {
            token: localStorage.getItem('token'),
          },
        })
        .then((result) => {
          if (result.data.code === 200) {
            this.flag = result.data.data.flag
            if (this.flag > 0) {
              this.files = result.data.data.files
            }
          }
        })
        .catch((err) => {})
    },
  },
}
</script>

<style lang="less" scoped>
.title,
.bottom {
  background-color: rgb(251, 251, 251);
  box-shadow: rgb(0 0 0 / 10%) 0px 2px 12px 0px;
}

.title {
  height: 35px;
  line-height: 40px;
  padding-bottom: 10px;
  text-align: center;
}

.bottom {
  padding-top: 20px;
  font-size: 14px;
}

.fu {
  margin-bottom: 10px;
}

.el-col {
  margin-bottom: 10px;
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.file {
  color: #24292e;
  cursor: pointer;
}

.file:hover {
  color: #f60;
}
</style>
