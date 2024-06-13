import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import Vuex from 'vuex'
import store from './store'
import 'element-plus/dist/index.css'

createApp(App).use(router).use(ElementPlus).use(Vuex).use(store).mount('#app')
