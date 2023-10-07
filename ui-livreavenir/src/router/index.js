import { createRouter, createWebHistory } from 'vue-router';


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: () => import('../layouts/MainLayout.vue'),
      children: [
        { path: '', component: () => import('../pages/HomePage.vue'), name: "home" },
      ],
    //   meta:{
    //     requiresAuth: false
    //   }

    },
    {
      path: '/add-new-book',
      component: () => import('../layouts/MainLayout.vue'),
      children: [
        { path: '', component: () => import('../pages/AddBook.vue'), name: "add-book" },
      ]
    },
    {
      path: '/books/:id/detail',
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
        { path: 'login',component: () => import('../pages/LoginPage.vue'), name: "login" },
        { path: 'profile',component: () => import('../pages/UserProfile.vue'), name: "profile" },
      ]
    },
    {
      path: '/:catchAll(.*)*',
      component: () => import('../pages/ErrorNotFound.vue'),
    },
  ]
})

// router.beforeEach(async (to, from) => {
//     if(to.meta.requiresAuth && !authService.isLoggedIn()){
//         const loggedIn = await authService.login();
//         if(!loggedIn) return '/login'
//     }
// })

export default router
