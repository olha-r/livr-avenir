import { defineStore } from 'pinia'
import { AddBookFormHttp } from '../services/AddBookFromHttp';

export const AddBookFormStore = defineStore('add-book-form-store', {
    state: () => ({
        list_formats: [],
        list_languages: [],
        list_conditions: [],
        list_categories: [],
    }),
    actions: {

        async get_list_formats(){
            const addBookFormHttp = new AddBookFormHttp();
            const promise = await addBookFormHttp.get_list_formats();
            if(promise.status === 200) {
                this.list_formats = promise.body;
            }
            return promise; 
        },
        async get_list_languages(){
            const addBookFormHttp = new AddBookFormHttp();
            const promise = await addBookFormHttp.get_list_languages();
            if(promise.status === 200) {
                this.list_languages = promise.body;
            }
            return promise; 
        },
        async get_list_conditions(){
            const addBookFormHttp = new AddBookFormHttp();
            const promise = await addBookFormHttp.get_list_conditions();
            if(promise.status === 200) {
                this.list_conditions = promise.body;
            }
            return promise; 
        },
        async get_list_categories(){
            const addBookFormHttp = new AddBookFormHttp();
            const promise = await addBookFormHttp.get_list_categories();
            if(promise.status === 200) {
                this.list_categories = promise.body;
            }
            return promise; 
        },

    },
  })