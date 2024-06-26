import { defineStore } from 'pinia';
import { UserHttp } from '../services/UserHttp';

export const useUserStore = defineStore('user-store', {
	state: () => ({
		user_profile: {},
		user_list: []
	}),
	actions: {
		async get_user_details() {
			const userHttp = new UserHttp();
			const promise = await userHttp.get_user_details();
			if (promise.status === 200) {
				this.user_profile = promise.body;
			}
			return promise;
		},
		async get_all_users() {
			const userHttp = new UserHttp();
			const promise = await userHttp.get_all_users();
			if (promise.status === 200) {
				this.user_list = promise.body;
			}
			return promise;
		}
	}
});
