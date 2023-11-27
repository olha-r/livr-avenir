import { defineStore } from 'pinia'
import {CategoryHttp} from "@/services/CategoryHttp";

export const useCategoryStore = defineStore('category-store', {
    state: () => ({
        list_categories: []
    }),
    actions: {
        async get_list_categories(){
            const categoryHttp = new CategoryHttp();
            const promise = await categoryHttp.get_list_categories();
            if(promise.status === 200) {
                this.list_categories = promise.body;
            }
            return promise; 
        },
    }, 
})