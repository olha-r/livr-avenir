import { createRouter, createWebHistory } from 'vue-router';
import {AuthStore} from '../stores/auth-store'
import { storeToRefs } from 'pinia';


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: () => import('../layouts/MainLayout.vue'),
      children: [
        { path: '', component: () => import('../views/HomePage.vue'), name: "home" },
      ]
    },
    {
      path: '/books/:id/detail',
      component: () => import('../layouts/MainLayout.vue'),
      children: [
        { path: '', component: () => import('../views/BookDetails.vue'), name: "book-details" },
      ],
    },
    {
      path: '/auth',
      component: () => import('../layouts/MainLayout.vue'),
      children: [
        { path: 'register', component: () => import('../views/auth/RegisterPage.vue'), name: "register" },
        { path: 'login',component: () => import('../views/auth/LoginPage.vue'), name: "login" },
        { path: 'profile',component: () => import('../views/UserProfile.vue'), name: "profile" },
      ]
    },
    {
      path: '/admin',
      component: () => import('../layouts/AdminLayout.vue'),
      children: [
        { path: '', component: () => import('../views/admin/AdminDashboard.vue'), name: "admin-dashboard" },
        { path: 'users', component: () => import('../views/admin/ManageUsers.vue'), name: "manage-users" },
        { path: 'add-book', component: () => import('../views/admin/AddBook.vue'), name: "add-book" },
        { path: 'book/:id/update', component: () => import('../views/admin/UpdateBook.vue'), name: "update-book" },
      ],
      meta:{
        requiresAuth: true,
        permission: 'admin'
      }
    },
    {
        path: '/error',
        component: () => import('../layouts/MainLayout.vue'),
        children: [
          { path: '403', component: () => import('../views/errors/ErrorForbidden.vue'), name: "forbidden" },
          { path: '401', component: () => import('../views/errors/ErrorUnauthorized.vue'), name: "unauthorized" },
        ]
      },
    {
      path: '/:catchAll(.*)*',
      component: () => import('../views/errors/ErrorNotFound.vue'),
    },
  ]
})

router.beforeEach(async (to, from) => {
    const authStore = AuthStore();
    const {isLoggedIn, userRole} = storeToRefs(authStore);
    console.log("Logged in", isLoggedIn.value);
    console.log("ROLE", userRole.value);
    if(to.meta.requiresAuth && !isLoggedIn.value){
        return '/auth/login'
    }
    if(to.meta.permission == 'admin' && userRole.value == "USER"){
        console.log("Vous n'avez pas droit à accéder aux ressources demandées.");
        return '/error/401'
    }
})

export default router
