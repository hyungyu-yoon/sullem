import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Vuetify from 'vuetify'
import VueSession from 'vue-session'
import 'vuetify/dist/vuetify.min.css'

import axios from 'axios'
import vuetify from './plugins/vuetify'
import '@babel/polyfill'

Vue.config.productionTip = false
Vue.prototype.$http = axios

Vue.use(VueSession)
Vue.use(Vuetify, {
  iconfont: 'fa4',
  theme: {
    primary: '#3f51b5',
    secondary: '#b0bec5',
    accent: '#8c9eff',
    error: '#b71c1c'
  }
})

new Vue({
  // el: '#app',
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
