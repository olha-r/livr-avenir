import { defineStore } from 'pinia'
import {AuthorHttp} from "../services/AuthorHttp";

export const useAuthorStore = defineStore('author-store', {
    state: () => ({
        author_list: [],
    }),
    actions: {
        async get_author_list(){
            const authorHttp = new AuthorHttp();
            const promise = await authorHttp.get_author_list();
            if(promise.status === 200) {
                this.author_list = promise.body;
            }
            return promise; 
        },
    },
  })