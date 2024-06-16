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
			// console.log('Token from axios: ', token);
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
			const authStore = useAuthStore();
			authStore.clearLocalStorage();
		}
		if (response?.status === 400) {
			const fieldErrors = response.data.fieldErrors || {};
			const globalErrors = response.data.globalErrors || [];

			globalStore.setFieldErrors(fieldErrors);
			globalStore.setGlobalErrors(globalErrors);
			setTimeout(() => {
				globalStore.clearGlobalErrors();
				globalStore.clearFieldErrors();
			}, 8000);
		}
		if (response?.status === 500) {
			store.setError(response?.data);
			setTimeout(() => {
				globalStore.clearError();
			}, 5000);
		}
		return Promise.reject(error);
	}
);

export { http };
