<template >
  <el-container style="align-items: center;
                      justify-content: center;
                      ">
    <el-card style="width: 80vw; height: 100vh;">
      <template #header><slot name="head"></slot></template>
      <slot name="body"></slot>
    </el-card>
  </el-container>
</template>
<style>
.el-card{
  text-align: center;
}


</style>