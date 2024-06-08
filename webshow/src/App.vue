<template>
  <div id="APP">
    <Nav />
    <router-view> </router-view>
  </div>
</template>
<script>
import Nav from './components/Nav.vue'
export default {
  name: 'App',
  components: {
    Nav
  }
}
</script>
<style>
body{
  margin: 0px;
}
</style>
