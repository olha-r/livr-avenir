import { defineStore } from 'pinia';
import { ConditionHttp } from '@/services/ConditionHttp';

export const useConditionStore = defineStore('condition-store', {
	state: () => ({
		list_conditions: []
	}),
	actions: {
		async get_list_conditions() {
			const conditionHttp = new ConditionHttp();
			const promise = await conditionHttp.get_list_conditions();
			if (promise.status === 200) {
				this.list_conditions = promise.body;
			}
			return promise;
		}
	}
});
