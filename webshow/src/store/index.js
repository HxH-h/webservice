import Vuex from 'vuex'
import axios from 'axios';
import route from '../router/index'
const store = new Vuex.Store({
  state: {
    id: '',
    username: ' ',
    password: ' ',
    token: ' ',
    isLogin: false
  },

  getters: {

  },
  mutations: {
    updateInfo(state, user) {
      state.id = user.id
      state.username = user.username
      state.password = user.password
      state.isLogin = true
    },
    logout(state) {
      state.id=""
      state.username = ""
      state.password = ""
      state.isLogin = false
    },
    setToken(state, token) {
      state.token = token
    }
  },
  actions: {
    getInfo(context) {
      axios({
        method: "get",
        url: "http://localhost:8080/user/info/",
        headers: {
          Authorization: "Bearer " + context.state.token
        }
      }).then(function (resp) {
        var user = {
          username: '',
          password: '',
          id: ''
        }
        user.username = resp.data.username
        user.password = resp.data.password
        user.id = resp.data.id
        store.commit("updateInfo", user)
        route.push({ name: 'GameList' })
      })
    },
    getToken(context, Data) {
      axios({
        method: "post",
        url: "http://localhost:8080/user/token",
        data: JSON.stringify(Data),
        headers: {
          "Content-Type": "application/json"
        }
      }).then(function (resp) {
        store.commit("setToken", resp.data.token)
        localStorage.setItem("jwt_token",context.state.token)
        store.dispatch("getInfo")
      }).catch((error) => {
        console.log('error1')
      })
    },
    removeToken(context){
      localStorage.removeItem("jwt_token")
      context.commit("logout")
    }
  },
  modules: {

  }
})
export default store
