import Vue from 'vue'
import Router from 'vue-router'
import LoginForm from '@/components/login-form/login-form'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'loginForm',
      component: LoginForm

    }
  ]
})
