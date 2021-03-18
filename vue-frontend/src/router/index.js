import Vue from 'vue'
import VueRouter from 'vue-router'

import AppTemplate from '../components/AppTemplate.vue'

Vue.use(VueRouter)

const routes = [
  { path: '/', component: AppTemplate },
  { path: '', component: AppTemplate },
  { path: '/home', component: AppTemplate},
  { path: '/:date', component: AppTemplate}
]

export default new VueRouter({mode: 'history', routes})