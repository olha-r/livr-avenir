import { createRouter, createWebHistory } from 'vue-router';
import { useAuthStore } from '../stores/auth-store';
import { storeToRefs } from 'pinia';

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: '/',
			component: () => import('../layouts/MainLayout.vue'),
			children: [
				{
					path: '',
					component: () => import('../views/common/HomePage.vue'),
					name: 'home'
				}
			]
		},
		{
			path: '/admin',
			component: () => import('../layouts/AdminLayout.vue'),
			children: [
				{
					path: '',
					component: () => import('../views/admin/AdminDashboard.vue'),
					name: 'admin-dashboard'
				},
				{
					path: 'ajouter-livre',
					component: () => import('../views/admin/AddBook.vue'),
					name: 'add-book'
				},
				{
					path: 'livre/:id/mettre-a-jour',
					component: () => import('../views/admin/UpdateBook.vue'),
					name: 'update-book'
				}
			],
			meta: {
				requiresAuth: true,
				permission: 'admin'
			}
		},
		{
			path: '/livre/:id/detail',
			component: () => import('../layouts/MainLayout.vue'),
			children: [
				{
					path: '',
					component: () => import('../views/common/BookDetails.vue'),
					name: 'book-details'
				}
			]
		},
		{
			path: '/auth',
			component: () => import('../layouts/MainLayout.vue'),
			children: [
				{
					path: 'inscription',
					component: () => import('../views/auth/RegisterPage.vue'),
					name: 'register'
				},
				{
					path: 'connexion',
					component: () => import('../views/auth/LoginPage.vue'),
					name: 'login'
				},
				{
					path: 'activation-account/code/:token',
					component: () => import('../views/auth/ActivateAccount.vue'),
					name: 'activate-account'
				},
				{
					path: 'profil',
					component: () => import('../views/client/UserProfile.vue'),
					name: 'profile',
					meta: { requiresAuth: true }
				}
			]
		},
		{
			path: '/erreur',
			component: () => import('../layouts/MainLayout.vue'),
			children: [
				{
					path: '403',
					component: () => import('../views/errors/ErrorForbidden.vue'),
					name: 'forbidden'
				},
				{
					path: '401',
					component: () => import('../views/errors/ErrorUnauthorized.vue'),
					name: 'unauthorized'
				}
			]
		},
		{
			path: '/:catchAll(.*)*',
			component: () => import('../views/errors/ErrorNotFound.vue'),
			name: 'not-found'
		}
	]
});

router.beforeEach(async (to, from) => {
	const authStore = useAuthStore();
	const { isLoggedIn, userRole } = storeToRefs(authStore);
	console.log('Logged in: ', isLoggedIn.value, ' ROLE: ', userRole.value);
	if (to.meta.requiresAuth && !isLoggedIn.value) {
		return '/auth/connexion';
	}
	if (to.meta.permission == 'admin' && userRole.value == 'USER') {
		return '/erreur/401';
	}
});

export default router;
