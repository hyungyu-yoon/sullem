import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import PostPage from './views/PostPage.vue'
import PostWriter from './views/PostWriter.vue'
import Intro from './views/Intro.vue'
import MapTest from './views/MapTest.vue'
import CreateSchedulePage from './views/CreateSchedulePage.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'intro',
      component: Intro
    },
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    },
    {
      path: '/postPage',
      name: 'postPage',
      component: PostPage
    },
    {
      path: '/postWriter',
      name: 'postWriter',
      component: PostWriter
    },
    {
      path: '/test',
      name: 'test',
      component: MapTest
    },
    {
      path: '/createschedule',
      name: 'createschedule',
      component: CreateSchedulePage
    }
  ]
})
