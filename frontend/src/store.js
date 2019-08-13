import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    Login: false,
    Account: false,
    FindPassword: false,
    user: null,
    seq: "",
    AdminNav: false,
    page: "",
    category: "",
    text: "",
    scheduleTitle: "1",
    scheduleStart: "2019-08-13",
    scheduleDescription: "1"
  },
  mutations: {},
  actions: {}
});
