<script setup>
import { storeToRefs } from 'pinia';
import { computed } from 'vue';
import { useI18n } from 'vue-i18n';
import { useGlobalStore } from '@/stores/global-errors-store';

const store = useGlobalStore();
const { error, globalErrors, fieldErrors } = storeToRefs(store);
const { t } = useI18n();
const getConvertedErrors = () => {
	const errors = store.convertErrorsToMessages(fieldErrors.value);
	return errors;
};
const convertedErrors = computed(getConvertedErrors);
</script>

<template>
	<div class="justify-content-center align-items-center">
		<div v-if="error" class="text-center alert alert-danger container mt-3 p-3">
			{{ error }}
		</div>
		<div
			v-if="globalErrors.length"
			class="text-center alert alert-danger container mt-3 p-3"
		>
			<div v-for="(error, index) in globalErrors" :key="index">{{ error }}</div>
		</div>
		<div
			v-for="(errors, field) in convertedErrors"
			:key="field"
			class="alert alert-danger container mt-3 p-3"
		>
			<div v-for="(error, index) in errors" :key="index">{{ error }}</div>
		</div>
	</div>
</template>
