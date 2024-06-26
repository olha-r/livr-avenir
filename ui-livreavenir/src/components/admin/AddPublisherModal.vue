<script setup>
import { reactive, computed } from 'vue';
import { useI18n } from 'vue-i18n';
import { useVuelidate } from '@vuelidate/core';
import { required, minLength, maxLength, helpers } from '@vuelidate/validators';
import { usePublisherStore } from '@/stores/publisher-store';
import { usePageStore } from '@/stores/page-store';
import ValidationMessage from '@/components/commons/ValidationMessage.vue';

const props = defineProps({
	show: {
		type: Boolean,
		required: true
	}
});

const emit = defineEmits(['onAdd', 'onClose']);
const publisherInputs = reactive({
	name: ''
});
const publisherStore = usePublisherStore();
const pageStore = usePageStore();
const { t } = useI18n();

const requiredMessage = `${t('admin.validationMessages.required')}`;
const minLengthMessage = `${t(
	'client.validationMessages.minLengthDescription'
)}`;
const maxLengthMessage = `${t(
	'client.validationMessages.maxLengthDescription'
)}`;

const createValidationRules = (field) => ({
	required: helpers.withMessage(requiredMessage, required),
	minLength: helpers.withMessage(minLengthMessage, minLength(1)),
	maxLength: helpers.withMessage(maxLengthMessage, maxLength(150))
});

const publisherRules = computed(() => ({
	name: createValidationRules('name')
}));

const publisherValidation = useVuelidate(publisherRules, publisherInputs);

const showAlert = (type, message, duration = 5000) => {
	pageStore.alert.type = type;
	pageStore.alert.message = message;
	pageStore.alert.show = true;
	setTimeout(() => {
		pageStore.alert.show = false;
	}, duration);
};

const addPublisher = async () => {
	await publisherValidation.value.$validate();
	if (!publisherValidation.value.$error) {
		const resp = await publisherStore.add_new_publisher(publisherInputs);
		if (resp.status === 204) {
			emit('onAdd', true);
			showAlert('success', t('admin.addPublisherForm.successMessage'));
			closeModal();
		} else {
			showAlert('error', t('client.addPublisherForm.errorMessage'), 3000);
		}
	}
};

const closeModal = () => {
	publisherInputs.name = '';
	publisherValidation.value.$reset();
	emit('onClose');
};
</script>

<template>
	<div v-if="show" class="modal author-modal" tabindex="-1">
		<div class="modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">
						{{ t('admin.addPublisherForm.titleCreate') }}
					</h5>
					<button type="button" class="btn-close" @click="closeModal"></button>
				</div>
				<form novalidate @submit.prevent="addPublisher">
					<div class="modal-body">
						<div class="mb-3">
							<label for="name" class="form-label required">{{
								t('admin.addPublisherForm.name')
							}}</label>
							<input
								v-model="publisherInputs.name"
								name="name"
								id="name"
								type="text"
								class="form-control"
							/>
							<ValidationMessage :model="publisherValidation.name" />
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">
							{{ t('admin.addPublisherForm.buttonCreate') }}
						</button>
						<button type="button" class="btn btn-secondary" @click="closeModal">
							{{ t('admin.dashboard.bookInfo.closeModal') }}
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</template>
