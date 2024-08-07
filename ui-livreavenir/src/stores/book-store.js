import { defineStore } from 'pinia';
import { BookHttp } from '../services/BookHttp';

export const useBookStore = defineStore('book-store', {
	state: () => ({
		lastAddedBooks: [],
		bookListForAdmin: [],
		book_details: {},
		book_for_update: {}
	}),
	actions: {
		async add_new_book(payload) {
			const bookHttp = new BookHttp();
			const promise = await bookHttp.add_new_book(payload);
			return promise;
		},
		async get_last_added_books() {
			const bookHttp = new BookHttp();
			const promise = await bookHttp.get_last_added_books();
			if (promise.status === 200) {
				this.lastAddedBooks = promise.body;
			}
			return promise;
		},
		async get_book_list_admin() {
			const bookHttp = new BookHttp();
			const promise = await bookHttp.get_book_list_admin();
			if (promise.status === 200) {
				this.bookListForAdmin = promise.body;
			}
			return promise;
		},
		async get_book_details(book_id) {
			const bookHttp = new BookHttp();
			const promise = await bookHttp.get_book_details(book_id);
			if (promise.status === 200) {
				this.book_details = promise.body;
			}
			return promise;
		},
		async get_book_for_update(book_id) {
			const bookHttp = new BookHttp();
			const promise = await bookHttp.get_book_for_update(book_id);
			if (promise.status === 200) {
				this.book_for_update = promise.body;
			}
			return promise;
		},
		async delete_book(book_id) {
			const bookHttp = new BookHttp();
			const promise = await bookHttp.delete_book(book_id);
			if (promise.status == 204) {
				this.get_book_list_admin();
			}
			return promise;
		},
		async update_book(book_id, payload) {
			const bookHttp = new BookHttp();
			const promise = await bookHttp.update_book(book_id, payload);
			if (promise.status == 204) {
				this.get_book_list_admin();
			}
			return promise;
		}
	}
});
