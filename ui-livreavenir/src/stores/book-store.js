import { defineStore } from 'pinia'
import {BookHttp} from "../services/BookHttp";

export const BookStore = defineStore('book-store', {
    state: () => ({
        lastAddedBooks: [],
        book_details: {}
    }),
    actions: {
        async add_new_book(payload, token){
            const bookHttp = new BookHttp();
            const promise = await bookHttp.add_new_book(payload, token);
            return promise; 
        },
        async get_last_added_books(){
            const bookHttp = new BookHttp();
            const promise = await bookHttp.get_last_added_books();
            if(promise.status === 200) {
                this.lastAddedBooks = promise.body;
            }
            return promise; 
        },
        async get_book_details(book_id){
            const bookHttp = new BookHttp();
            const promise = await bookHttp.get_book_details(book_id);
            if(promise.status === 200) {
                this.book_details = promise.body;
            }
            return promise; 
        },
        async delete_book(book_id, token){
            const bookHttp = new BookHttp();
            const promise = await bookHttp.delete_book(book_id, token);
            return promise; 
        }
    },
  })