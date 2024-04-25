import { http } from './axios';
export class ConditionHttp {
	async get_list_conditions() {
		const url = `/conditions`;
		const response = await http.get(url);
		return response;
	}
}
