import { createRouter, createWebHistory } from 'vue-router'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: () => import('../layouts/MainLayout.vue'),
      children: [
        { path: '', component: () => import('../pages/HomePage.vue'), name: "home" },
      ]
    },
    {
      path: '/add-new-book',
      component: () => import('../layouts/MainLayout.vue'),
      children: [
        { path: '', component: () => import('../pages/AddBookFirstPage.vue'), name: "add-book" },
        {  path: 'final', component: () => import('../pages/AddBookSecondPage.vue'), name: "add-book-final" },
      ]
    },
    {
      path: '/book',
      component: () => import('../layouts/MainLayout.vue'),
      children: [
        { path: '', component: () => import('../pages/BookDetails.vue'), name: "book-details" },
      ]
    },
    {
      path: '/auth',
      component: () => import('../layouts/MainLayout.vue'),
      children: [
        { path: 'register', component: () => import('../pages/RegisterPage.vue'), name: "register" },
        { path: '/login',component: () => import('../pages/LoginPage.vue'), name: "login" },
        { path: '/profile',component: () => import('../pages/UserProfile.vue'), name: "profile" },
      ]
    },
    {
      path: '/:catchAll(.*)*',
      component: () => import('../pages/ErrorNotFound.vue'),
    },
  ]
})

export default router
