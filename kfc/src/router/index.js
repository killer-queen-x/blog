import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: ()=>import('../views/Login.vue')
  },
  {
    path: '/detail',
    name: 'Detail',
    component: ()=>import('../views/Detail.vue')
  },
  {
    path: '/edit',
    name: 'Edit',
    component: ()=>import('../views/Edit.vue')
  },
  {
    path: '/mypost',
    name: 'MyPost',
    component: ()=>import('../views/MyPost.vue')
  },
  {
    path: '/livePost',
    name: 'LivePost',
    component: ()=>import('../views/LivePost.vue')
  },
  {
    path: '/personal',
    name: 'Personal',
    component: ()=>import('../views/Personal.vue')
  },
  {
    path: '/publish',
    name: 'Publish',
    component: ()=>import('../views/Publish.vue')
  },
  {
    path: '/notice',
    name: 'Notice',
    component: ()=>import('../views/Notice.vue')
  },
  {
    path: '/chat',
    name: 'Chat',
    component: ()=>import('../views/Chat.vue')
  },
  
  {
    path:'/main',
    name:'Main',
    component:()=>import('../views/Main.vue'),

    children:[
      {
        path:'/home',
        name:'Home',
        component:()=>import('../views/Home.vue')
      },
      {
        path:'/message',
        name:'Message',
        component:()=>import('../views/Message.vue')
      },
      {
        path:'/my',
        name:'My',
        component:()=>import('../views/My.vue')
      },
      
    ]
    
  },
  // 重定向
  {
    path:'/',
    redirect:'Home'
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
