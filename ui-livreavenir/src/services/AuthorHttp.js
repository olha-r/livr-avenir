import { http } from './axios';

export class AuthorHttp {
	async add_new_author(payload) {
		const url = `/authors`;
		const response = await http.post(url, payload);
		return response;
	}
	async get_author_list() {
		const url = `/authors`;
		const response = await http.get(url);
		return response;
	}
}
