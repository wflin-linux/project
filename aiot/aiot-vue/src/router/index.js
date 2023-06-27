import Vue from 'vue'
import VueRouter from 'vue-router'


// 路由懒加载
const Index = () => import('../components/index/Index.vue')
const Login = () => import('../components/Login.vue')
const Announcement = () => import('../components/announcement/Announcement.vue')
const Competition = () => import('../components/competition/Competition.vue')
const CompetitionDetail = () =>
  import('../components/competition/CompetitionDetail.vue')

Vue.use(VueRouter)

const router = new VueRouter({
  routes:
    [
      { path: '/', redirect: '/index' },
      { path: '/login', component: Login },
      { path: '/announcement', component: Announcement },
      { path: '/competition', component: Competition },
      { path: '/detail', component: CompetitionDetail },
      { path: '/index', component: Index }
    ]
})

// ；路由导航守卫	
router.beforeEach((to, from, next) => {
  if (to.path == '/login' || to.path == '/' || to.path == '/register' || to.path == '/index')
    return next()
  const tokenStr = localStorage.getItem('token')
  console.log(tokenStr);
  if (tokenStr == null) {
    alert("无权限，需要登陆")
    return next('/login')
  }
  next()
})

export default router
