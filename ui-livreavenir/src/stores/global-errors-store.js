import { defineStore } from 'pinia';
import { useI18n } from 'vue-i18n';

export const useGlobalStore = defineStore('global', {
	state: () => ({
		error: null,
		fieldErrors: {},
		globalErrors: []
	}),

	actions: {
		setError(message) {
			this.error = message;
		},

		clearError() {
			this.error = null;
		},
		setFieldErrors(errors) {
			this.fieldErrors = errors;
		},

		clearFieldErrors() {
			this.fieldErrors = {};
		},

		setGlobalErrors(errors) {
			this.globalErrors = errors;
		},

		clearGlobalErrors() {
			this.globalErrors = [];
		},

		convertErrorsToMessages(errors) {
			const { t } = useI18n();
			const convertedErrors = {};

			for (const field in errors) {
				if (errors.hasOwnProperty(field)) {
					convertedErrors[field] = errors[field].map((errorType) =>
						t(`errors.fieldErrors.${field}.${errorType}`)
					);
				}
			}

			return convertedErrors;
		}
	}
});
