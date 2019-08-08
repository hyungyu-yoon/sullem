import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    Login: false,
    Account: false,
    user: null,
    AdminNav: false,
    page: '',
    category: '',
    text: ''
  },
  mutations: {

  },
  actions: {

  }
})
