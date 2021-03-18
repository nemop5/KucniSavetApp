import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';
import router from '../router/index.js';
//import something.js from './something.js' which is the splitted part of state 

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    tasks: [],
    totalHours: 0
  },
  actions: {
    getTasks: ({commit}) => {
      axios.get('http://localhost:8080/api/tasks/', { params: { date: router.currentRoute.params.date}})
      .then(res => {
            console.log(res)
            const tasks = res.data
  
            commit('GET_TASKS', tasks);
          
      })
      .catch(error => {
          console.log(error)
      })
    },
    setTotalHours: ({ commit }) => {
      commit('SET_TOTAL_HOURS')
    }

  },
  mutations: {
    'GET_TASKS' (state, tasks) {
      state.tasks = tasks;
    },
    'SET_TOTAL_HOURS' (state) {
      let tasks = state.tasks
      let totalHours = 0;
    
      tasks.forEach((element) => { totalHours += element.hours; });
      state.totalHours = totalHours;
    },
  },
  getters: {
    tasks: state => {
      return state.tasks;
    },
    totalHours: state => {
      return state.totalHours;
    }
  },
  modules: {
  },
})
