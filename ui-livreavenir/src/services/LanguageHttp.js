import { http } from './axios';
export class LanguageHttp {
	async get_list_languages() {
		const url = `/languages`;
		const response = await http.get(url);
		return response;
	}
}
