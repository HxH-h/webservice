<template >
  <el-container style="align-items: center;
                      justify-content: center;
                      ">
    <el-card style="width: 70vw;">
      <template #header><slot ></slot></template>
      
    </el-card>
  </el-container>
</template>
<style>
.el-card{
  
  text-align: center;


}


</style>