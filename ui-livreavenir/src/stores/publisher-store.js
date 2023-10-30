import { defineStore } from 'pinia'
import {PublisherHttp} from "../services/PublisherHttp";

export const publisherStore = defineStore('publisher-store', {
    state: () => ({
        publisher_list: [],
    }),
    actions: {
        async get_publisher_list(){
            const publisherHttp = new PublisherHttp();
            const promise = await publisherHttp.get_publisher_list();
            if(promise.status === 200) {
                this.publisher_list = promise.body;
            }
            return promise; 
        },
    },
  })