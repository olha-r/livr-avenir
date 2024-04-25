import { http } from './axios';

export class AuthorHttp {
	async get_author_list() {
		const url = `/authors`;
		const response = await http.get(url);
		return response;
	}
}
