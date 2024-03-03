import { defineStore } from 'pinia'
import {BookItemHttp} from "@/services/BookItemHttp";

export const useBookItemStore = defineStore('book-item-store', {
    state: () => ({
        
    }),
    actions: {
        async add_new_book_item(payload, token){
            const bookItemHttp = new BookItemHttp();
            const promise = await bookItemHttp.add_new_item(payload, token);
            return promise; 
        },
    }, 
})