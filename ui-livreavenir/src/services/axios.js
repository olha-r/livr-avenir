import axios from 'axios';
import { useGlobalStore } from '../stores/global-errors-store';
import { useAuthStore } from '../stores/auth-store';

const BASE_URL = import.meta.env.VITE_API_BASE_URL;
const ACCEPTED_STATUS = [200, 201, 202, 204];

const http = axios.create({
	baseURL: BASE_URL,
	validateStatus: (status) => {
		return ACCEPTED_STATUS.includes(status);
	}
});

http.interceptors.request.use(
	(config) => {
		const authStore = useAuthStore();
		const token = authStore.token;
		if (token) {
			config.headers.Authorization = `Bearer ${token}`;
			config.headers.Accept = 'application/json';
		}
		return config;
	},
	(error) => {
		return Promise.reject(error);
	}
);

http.interceptors.response.use(
	(response) => {
		const status = response.status;
		const data = response.data;
		const body = data != '' ? data : null;
		return { status: status, body: body };
	},
	(error) => {
		const globalStore = useGlobalStore();
		const response = error.response;
		if (response?.status === 401) {
			console.log(response.data);
			const errorMessage = response.data || 'Non autorisé';
			const authStore = useAuthStore();
			if (errorMessage === 'User email was not confirmed') {
				globalStore.setError(
					"L'email de l'utilisateur n'a pas été confirmé. Veuillez vérifier votre email."
				);
			} else if (errorMessage === 'Wrong credentials') {
				authStore.clearLocalStorage();
				globalStore.setError(
					'Email ou mot de passe incorrect. Veuillez réessayer.'
				);
			} else {
				globalStore.setError('Accès non autorisé. Veuillez réessayer.');
			}
			setTimeout(() => {
				globalStore.clearError();
			}, 5000);
		} else if (response?.status === 400) {
			const fieldErrors = response.data.fieldErrors || {};
			const globalErrors = response.data.globalErrors || [];

			globalStore.setFieldErrors(fieldErrors);
			globalStore.setGlobalErrors(globalErrors);
			setTimeout(() => {
				globalStore.clearGlobalErrors();
				globalStore.clearFieldErrors();
			}, 8000);
		} else if (response?.status === 500) {
			store.setError(
				'Erreur interne du serveur. Veuillez réessayer plus tard.'
			);
			setTimeout(() => {
				globalStore.clearError();
			}, 5000);
		} else {
			globalStore.setError(
				"Une erreur inattendue s'est produite. Veuillez réessayer."
			);
			setTimeout(() => {
				globalStore.clearError();
			}, 5000);
		}

		return Promise.reject(error);
	}
);

export { http };
