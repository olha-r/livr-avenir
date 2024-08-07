import { http } from './axios';

export class UserHttp {
	async get_user_details() {
		const url = `/users/profile`;
		const response = await http.get(url);
		return response;
	}
	async get_all_users() {
		const url = `/users`;
		const response = await http.get(url);
		return response;
	}
}
