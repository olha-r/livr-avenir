<script setup>
import { reactive, onMounted, computed } from 'vue';
import { storeToRefs } from 'pinia';
import { usePageStore } from '@/stores/page-store';
import { useAuthStore } from '@/stores/auth-store';
import { useBookItemStore } from '@/stores/book-item-store';
import LabelValues from '../../components/commons/LabelValues.vue';
import { useConditionStore } from '@/stores/condition-store';
import { useVuelidate } from '@vuelidate/core';
import { useI18n } from 'vue-i18n';
import {
	required,
	minLength,
	maxLength,
	numeric,
	helpers
} from '@vuelidate/validators';
import ValidationMessage from '@/components/commons/ValidationMessage.vue';
const pageStore = usePageStore();
const authStore = useAuthStore();
const { token } = storeToRefs(authStore);
const props = defineProps(['item', 'exitEditMode']);
const inputs = reactive({
	description: null,
	conditionId: null,
	pointsPrice: null
});
const { t } = useI18n();
const requiredMessage = `${t('admin.validationMessages.required')}`;
const rules = computed(() => {
	return {
		pointsPrice: {
			required: helpers.withMessage(requiredMessage, required),
			numeric: helpers.withMessage(
				`${t('admin.validationMessages.numeric')}`,
				numeric
			)
		},
		description: {
			required: helpers.withMessage(requiredMessage, required),
			minLength: helpers.withMessage(
				`${t('client.validationMessages.minLengthDescription')}`,

				minLength(10)
			),
			maxLength: helpers.withMessage(
				`${t('client.validationMessages.maxLengthDescription')}`,
				maxLength(150)
			)
		},
		conditionId: {
			required: helpers.withMessage(requiredMessage, required)
		}
	};
});
const v$ = useVuelidate(rules, inputs);
const conditionStore = useConditionStore();
const { list_conditions } = storeToRefs(conditionStore);
onMounted(async () => {
	inputs.description = props.item.description || null;
	inputs.conditionId = props.item.condition.id || null;
	inputs.pointsPrice = props.item.pointsPrice || null;
	await conditionStore.get_list_conditions();
});
const pointsPriceOptions = [
	{
		id: 1,
		name: 1
	},
	{
		id: 2,
		name: 2
	},
	{
		id: 3,
		name: 3
	},
	{
		id: 4,
		name: 4
	},
	{
		id: 5,
		name: 5
	}
];
const bookItemStore = useBookItemStore();
const update_item = async () => {
	await v$.value.$validate();

	if (!v$.value.$error) {
		const resp = await bookItemStore.update_item(props.item.id, inputs, token);

		if (resp.status === 204) {
			props.exitEditMode();
			pageStore.alert.type = 'success';
			pageStore.alert.message = `${t('client.updateItem.successMessage')}`;

			pageStore.alert.show = true;
			setTimeout(() => {
				pageStore.alert.show = false;
			}, 5000);
			await bookItemStore.get_items_by_user();
		} else {
			pageStore.alert.type = 'error';
			pageStore.alert.message = `${t('client.updateItem.errorMessage')}`;
			pageStore.alert.show = true;
			setTimeout(() => {
				pageStore.alert.show = false;
			}, 5000);
		}
	}
};
const cancelEdit = () => {
	props.exitEditMode();
};
</script>

<template>
	<form novalidate @submit.prevent="update_item">
		<div class="mb-3">
			<label for="description" class="form-label required">
				{{ t('client.bookDetailPage.bookItemDetail.description') }}
			</label>
			<textarea
				v-model.trim="inputs.description"
				name="description"
				id="description"
				class="form-control form-control-sm"
				rows="3"
			></textarea>
			<ValidationMessage :model="v$.description" />
		</div>

		<div class="mb-3">
			<label for="conditionId" class="form-label required"
				>{{ t('client.newItemForm.condition.label') }}
			</label>
			<select
				v-model.number="inputs.conditionId"
				id="conditionId"
				class="form-select form-select-sm"
			>
				<option selected disabled value="0">
					{{ t('client.newItemForm.condition.option') }}
				</option>
				<LabelValues :items="list_conditions" />
			</select>
			<ValidationMessage :model="v$.conditionId" />
		</div>

		<div class="mb-3">
			<label for="pointsPrice" class="form-label required">
				{{ t('client.newItemForm.pointsPrice.label') }}</label
			>
			<select
				v-model.number="inputs.pointsPrice"
				id="pointsPrice"
				class="form-select form-select-sm"
			>
				<option selected disabled value="0">
					{{ t('client.newItemForm.pointsPrice.option') }}
				</option>
				<LabelValues :items="pointsPriceOptions" />
			</select>
			<ValidationMessage :model="v$.pointsPrice" />
		</div>

		<div class="text-center">
			<button
				type="button"
				class="btn btn-secondary btn-sm me-2"
				@click="cancelEdit"
			>
				{{ t('client.bookDetailPage.bookItemDetail.btnCancel') }}
			</button>
			<button type="submit" class="btn btn-sm btn-primary me-2">
				{{ t('client.bookDetailPage.bookItemDetail.btnModify') }}
			</button>
		</div>
	</form>
</template>
