import { http } from './axios';

export class BookHttp {
	async add_new_book(payload) {
		const url = `/books`;
		const headers = {};
		headers['Content-Type'] = 'multipart/form-data';
		const response = await http.post(url, payload, { headers });
		return response;
	}
	async get_last_added_books() {
		const url = `/books`;
		const response = await http.get(url);
		return response;
	}
	async get_book_list_admin() {
		const url = `/books/admin`;
		const response = await http.get(url);
		return response;
	}
	async get_book_details(book_id) {
		const url = `/books/${book_id}/detail`;
		const response = await http.get(url);
		return response;
	}
	async get_book_for_update(book_id) {
		const url = `/books/${book_id}/for-update`;
		const response = await http.get(url);
		return response;
	}
	async delete_book(book_id) {
		const url = `/books/${book_id}`;
		const response = await http.delete(url);
		return response;
	}
	async update_book(book_id, payload) {
		const url = `/books/${book_id}`;

		const response = await http.put(url, payload);
		return response;
	}
}
