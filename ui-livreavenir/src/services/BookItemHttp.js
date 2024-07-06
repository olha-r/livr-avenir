import { http } from './axios';

export class BookItemHttp {
	async add_new_item(payload) {
		const url = `/book-items`;
		const response = await http.post(url, payload);
		return response;
	}

	async get_items_by_book_id(book_id) {
        const url = `/book-items/${book_id}`;
		const response = await http.get(url);
		return response;
	}
    
    async get_items_by_user() {
        const url = `/book-items/user`;
        const response = await http.get(url);
        return response;
    }

	async delete_item(item_id) {
		const url = `/book-items/${item_id}`;
		const response = await http.delete(url);
		return response;
	}
	async update_item(item_id, payload) {
		const url = `/book-items/${item_id}`;
		const response = await http.patch(url, payload);
		return response;
	}
}
