import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

// 导入全局样式表
import './assets/css/global.css'

import './assets/content/bootstrap.scss';
import './assets/content/utils.scss';
import './assets/content/app.scss';
Vue.config.productionTip = false

Vue.use(ElementUI)

import axios from 'axios'
// axios.defaults.baseURL = 'http://127.0.0.1:80/'
axios.defaults.baseURL = 'http://121.5.171.98:80/'
Vue.prototype.$http = axios
axios.defaults.withCredentials = true

// axios.defaults.baseURL = 'http://127.0.0.1:80/'
// Vue.prototype.$http = axios
// Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

