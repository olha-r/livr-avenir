<script setup>
import { reactive, onMounted, computed } from 'vue';
import { storeToRefs } from 'pinia';
import { usePageStore } from '@/stores/page-store';
import { useBookItemStore } from '@/stores/book-item-store';
import { useConditionStore } from '@/stores/condition-store';
import LabelValues from '../../components/commons/LabelValues.vue';
import ValidationMessage from '@/components/commons/ValidationMessage.vue';
import { useVuelidate } from '@vuelidate/core';
import { useI18n } from 'vue-i18n';
import {
	required,
	minLength,
	maxLength,
	numeric,
	helpers
} from '@vuelidate/validators';

const pageStore = usePageStore();
const conditionStore = useConditionStore();
const bookItemStore = useBookItemStore();

const { list_conditions } = storeToRefs(conditionStore);

const props = defineProps(['item', 'exitEditMode']);

const inputs = reactive({
	description: null,
	conditionId: null,
	pointsPrice: null
});

const { t } = useI18n();
const requiredMessage = `${t('admin.validationMessages.required')}`;

const rules = computed(() => ({
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
}));

const v$ = useVuelidate(rules, inputs);

onMounted(async () => {
	Object.assign(inputs, {
		description: props.item.description || null,
		conditionId: props.item.condition.id || null,
		pointsPrice: props.item.pointsPrice || null
	});
	await conditionStore.get_list_conditions();
});

const pointsPriceOptions = [1, 2, 3, 4, 5].map((id) => ({ id, name: id }));

const showAlert = (type, message) => {
	pageStore.alert.type = type;
	pageStore.alert.message = message;
	pageStore.alert.show = true;
	setTimeout(() => {
		pageStore.alert.show = false;
	}, 5000);
};

const update_item = async () => {
	await v$.value.$validate();
	if (!v$.value.$error) {
		const resp = await bookItemStore.update_item(props.item.id, inputs);
		if (resp.status === 204) {
			props.exitEditMode();
			showAlert('success', `${t('client.updateItem.successMessage')}`);
			await bookItemStore.get_items_by_user();
		} else {
			showAlert('error', `${t('client.updateItem.errorMessage')}`);
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
			<label for="description" class="form-label required">{{
				t('client.bookDetailPage.bookItemDetail.description')
			}}</label>
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
			<label for="conditionId" class="form-label required">{{
				t('client.newItemForm.condition.label')
			}}</label>
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
			<label for="pointsPrice" class="form-label required">{{
				t('client.newItemForm.pointsPrice.label')
			}}</label>
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
