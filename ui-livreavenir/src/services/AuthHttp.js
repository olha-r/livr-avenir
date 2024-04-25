import { http } from './axios';

export class AuthHttp {
	async register(payload) {
		const url = `/auth/sign-up`;
		const response = await http.post(url, payload);
		return response;
	}

	async login(payload) {
		const url = `/auth/sign-in`;
		const response = await http.post(url, payload);
		return response;
	}
	async verifyUserRegistration(token) {
		const url = `/auth/verify?code=${token}`;
		const response = await http.get(url);
		return response;
	}
}
