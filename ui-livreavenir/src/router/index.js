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
        { path: '', component: () => import('../pages/HomePage.vue'), name: "home" },
      ]
    },
    {
      path: '/books/:id/detail',
      component: () => import('../layouts/MainLayout.vue'),
      children: [
        { path: '', component: () => import('../pages/BookDetails.vue'), name: "book-details" },
      ],
    },
    {
      path: '/auth',
      component: () => import('../layouts/MainLayout.vue'),
      children: [
        { path: 'register', component: () => import('../pages/RegisterPage.vue'), name: "register" },
        { path: 'login',component: () => import('../pages/LoginPage.vue'), name: "login" },
        { path: 'profile',component: () => import('../pages/UserProfile.vue'), name: "profile" },
      ]
    },
    {
      path: '/admin',
      component: () => import('../layouts/AdminLayout.vue'),
      children: [
        { path: '', component: () => import('../pages/admin/AdminDashboard.vue'), name: "admin-dashboard" },
        { path: 'users', component: () => import('../pages/admin/ManageUsers.vue'), name: "manage-users" },
        { path: 'add-book', component: () => import('../pages/admin/AddBook.vue'), name: "add-book" },
        { path: 'book/:id/update', component: () => import('../pages/admin/UpdateBook.vue'), name: "update-book" },
      ],
      meta:{
        requiresAuth: true,
        permission: 'admin'
      }
    },
    {
      path: '/:catchAll(.*)*',
      component: () => import('../pages/ErrorNotFound.vue'),
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
        console.log("Vous n'avez pas droit à acceder aux ressources démandés.");
        // Ajouter page d'erreur
        return '/'
    }
})

export default router
