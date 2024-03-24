import { defineStore } from 'pinia'
import {BookItemHttp} from "@/services/BookItemHttp";

export const useBookItemStore = defineStore('book-item-store', {
    state: () => ({
        items_by_book: [],
        items_by_user: []
    }),
    actions: {
        async add_new_book_item(payload, token){
            const bookItemHttp = new BookItemHttp();
            const promise = await bookItemHttp.add_new_item(payload, token);
            return promise; 
        },
        async get_items_by_book_id(book_id){
            const bookItemHttp = new BookItemHttp();
            const promise = await bookItemHttp.get_items_by_book_id(book_id);
            if(promise.status === 200) {
                console.log(promise);
                this.items_by_book = promise.body;
            }
            return promise; 
        },

        async get_items_by_user(){
            const bookItemHttp = new BookItemHttp();
            const promise = await bookItemHttp.get_items_by_user();
            if(promise.status === 200) {
                console.log(promise);
                this.items_by_user = promise.body;
            }
            return promise; 
        },
        async delete_item(item_id, token){
            const bookItemHttp = new BookItemHttp();
            const promise = await bookItemHttp.delete_item(item_id, token);
            console.log("PROMISE", promise);
            // if(promise.status== 204){
            //     this.get_book_list_admin();
            // }
            return promise; 
        },
        async update_item(item_id, payload, token){
            const bookItemHttp = new BookItemHttp();
            const promise = await bookItemHttp.update_item(item_id, payload, token);
            console.log("PROMISE", promise);
            return promise; 
        }
    }, 
})