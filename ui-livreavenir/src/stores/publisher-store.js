import { defineStore } from 'pinia';
import { PublisherHttp } from '../services/PublisherHttp';

export const usePublisherStore = defineStore('publisher-store', {
	state: () => ({
		publisher_list: []
	}),
	actions: {
		async get_publisher_list() {
			const publisherHttp = new PublisherHttp();
			const promise = await publisherHttp.get_publisher_list();
			if (promise.status === 200) {
				this.publisher_list = promise.body;
			}
			return promise;
		},
		async add_new_publisher(payload) {
			const publisherHttp = new PublisherHttp();
			const promise = await publisherHttp.add_new_publisher(payload);
			return promise;
		}
	}
});
