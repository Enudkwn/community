import { createRouter, createWebHistory } from 'vue-router'

import IndexView from '@/views/index/index.vue'
import LogView from '@/views/log/index.vue'
import LayoutView from '@/views/layout/index.vue'
import LoginView from '@/views/login/index.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/user/change-password',
      name: 'changePassword',
      component: () => import('@/views/user/ChangePassword.vue')
    },
    {
      path: '/',
      name: '',
      component: LayoutView,
      redirect: '/index',
      children: [
        {
          path: '/user/:userId',
          component: () => import('@/views/user/UserProfile.vue'),
          meta: { requiresAuth: true }
        },
        {
          path: '/user/public/:userId',
          component: () => import('@/views/user/UserPublicProfile.vue'),
          meta: { requiresAuth: true }
        },
        {path: 'index', name: 'index', component: IndexView},
        // {path: 'post', name: 'post', component: () => import('@/views/post/index.vue')},
        {path: 'post/:postId', name: 'postDetail', component: () => import('@/views/post/PostDetail.vue')},
        {path: 'post/create', name: 'createPost', component: () => import('@/views/post/CreatePost.vue')},
    {path: 'post/edit/:postId', name: 'editPost', component: () => import('@/views/post/CreatePost.vue'), props: true},
        // {path: 'reply', name: 'reply', component: () => import('@/views/reply/index.vue')},
        {path: 'log', name: 'log', component: LogView}
      ]
    },
    {path: '/login', name: 'login', component: LoginView}
  ]
})

export default router
