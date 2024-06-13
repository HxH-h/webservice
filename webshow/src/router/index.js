import { createRouter, createWebHistory } from 'vue-router'
import { useStore } from "vuex"
import GameList from '../views/GameList/GameList.vue'
import GamePK from '../views/GamePK/GamePK.vue'
import GamerInfo from '../views/GamerInfo/GamerInfo.vue'
import RankList from '../views/RankList/RankList.vue'
import NotFound from '../views/NotFound.vue'
import LoginPage from '../views/Login/login.vue'

const routes = [
  {
    path:'/',
    redirect: '/GamePK'
  },
  {
    path:'/GameList',
    name: 'GameList',
    meta: { isAuth: true, title:'主页' },
    component: GameList
  },
  {
    path:'/GamePK',
    name: 'GamePK',
    meta: { isAuth: true, title:'主页' },
    component: GamePK
  },
  {
    path:'/GamerInfo',
    name: 'GamerInfo',
    meta: { isAuth: true, title:'主页' },
    component: GamerInfo
  },
  {
    path:'/RankList',
    name: 'RankList',
    meta: { isAuth: true, title:'主页' },
    component: RankList
  },
  {
    path: '/login',
    name:'login',
    component:LoginPage
  },
  {
    path: '/404',
    name: 'Notfound',
    component: NotFound
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/404'
  }
  
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  const store = useStore()
  //如果路由需要跳转
  if (to.meta.isAuth) {
      if (store.state.isLogin) {
          next()  //放行
      } else {
          alert('抱歉，您无权限查看！')
          next('/login')
      }
  } else {
      // 否则，放行
      next()
  }
})


export default router
