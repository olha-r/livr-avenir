import { http } from './axios';
export class PublisherHttp {
	async get_publisher_list() {
		const url = `/publishers`;
		const response = await http.get(url);
		return response;
	}
	async add_new_publisher(payload) {
		const url = `/publishers`;
		const response = await http.post(url, payload);
		return response;
	}
}
