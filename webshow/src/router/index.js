import { createRouter, createWebHistory } from 'vue-router'
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
    component: GameList
  },
  {
    path:'/GamePK',
    name: 'GamePK',
    component: GamePK
  },
  {
    path:'/GamerInfo',
    name: 'GamerInfo',
    component: GamerInfo
  },
  {
    path:'/RankList',
    name: 'RankList',
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

export default router
