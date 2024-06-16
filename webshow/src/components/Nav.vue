<template>
    <el-menu
      :default-active="route.currentRoute.name"
      class="el-menu-demo"
      mode="horizontal"
      :ellipsis="false"
      @select=""
    >
      <!-- <el-menu-item index="0">
        <img
          style="width: 100px"
          src="/images/element-plus-logo.svg"
          alt="Element logo"
        />
      </el-menu-item> -->
     
      <el-menu-item index="0" style="margin-left: 20vw " @click="jumpPage('GamePK')">对战模式</el-menu-item>
      <el-menu-item index="1" style="margin-left: 3svw" @click="jumpPage('GameList')">对战列表</el-menu-item>
      <el-menu-item index="2" style="margin-left: 3svw" @click="jumpPage('RankList')">排行榜</el-menu-item>
      
      <div class="flex-grow" />
      <el-sub-menu index="3" v-if="this.store.state.isLogin">
        <template #title>{{ this.store.state.username }}</template>
        <el-menu-item index="2-1" @click="jumpPage('GamerInfo')">个人信息</el-menu-item>
        <el-menu-item index="2-2" @click="logout">登出</el-menu-item>
        <el-menu-item index="2-3" >item three</el-menu-item>
      </el-sub-menu>
      <el-menu-item index="3" @click="jumpPage('login')" v-else>登录</el-menu-item>
    </el-menu>
  </template>
  <script>
  import {ref,onMounted, reactive} from 'vue'
  import { useStore } from "vuex"
  import route from '../router/index'
  export default{
      setup(){
        const activeIndex = ref('1')
        const store = useStore()
        function logout(){
          store.dispatch("removeToken")
        }
        function jumpPage(page){
          route.push({ name: page })
        }
        return{
            activeIndex,
            store,
            route,
            logout,
            jumpPage
        }
      }
  }
  
  
  </script>
  <style scoped>
  .flex-grow{
    flex-grow: 1;
  }
  .el-menu-demo > .el-menu-item {
  border-bottom: none;
  text-decoration: none;
}

  </style>
  