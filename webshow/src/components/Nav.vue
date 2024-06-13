<template>
    <el-menu
      :default-active="activeIndex"
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
     
      <el-menu-item index="0" style="margin-left: 20vw "><router-link to="/GamePK" >对战模式</router-link></el-menu-item>
      <el-menu-item index="1" style="margin-left: 3svw"><router-link to="/GameList">对战列表</router-link></el-menu-item>
      <el-menu-item index="2" style="margin-left: 3svw"><router-link to="/RankList">排行榜</router-link></el-menu-item>
      
      <div class="flex-grow" />
      <el-sub-menu index="3">
        <template #title>{{ username }}</template>
        <el-menu-item index="2-1"><router-link to="/GamerInfo">个人信息</router-link></el-menu-item>
        <el-menu-item index="2-2">item two</el-menu-item>
        <el-menu-item index="2-3">item three</el-menu-item>
      </el-sub-menu>
    </el-menu>
  </template>
  <script setup>
  import {ref} from 'vue'
  const activeIndex = ref('1')
  
  const username=ref('hxh');

  </script>
  <style scoped>
  .flex-grow{
    flex-grow: 1;
  }
  .el-menu-demo > .el-menu-item {
  border-bottom: none;
  text-decoration: none;
}

.router-link-active, .a{
  border-bottom: none;
  text-decoration: none;
}
  </style>
  