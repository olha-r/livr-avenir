import { defineStore } from 'pinia';
import { LanguageHttp } from '@/services/LanguageHttp';

export const useLanguageStore = defineStore('language-store', {
	state: () => ({
		list_languages: []
	}),
	actions: {
		async get_list_languages() {
			const languageHttp = new LanguageHttp();
			const promise = await languageHttp.get_list_languages();
			if (promise.status === 200) {
				this.list_languages = promise.body;
			}
			return promise;
		}
	}
});
