<script setup>
import { reactive, computed } from 'vue';
import { useI18n } from 'vue-i18n';
import { useVuelidate } from '@vuelidate/core';
import { required, minLength, maxLength, helpers } from '@vuelidate/validators';
import { useAuthorStore } from '@/stores/author-store';
import { usePageStore } from '@/stores/page-store';
import ValidationMessage from '@/components/commons/ValidationMessage.vue';

const props = defineProps({
	show: {
		type: Boolean,
		required: true
	}
});

const emit = defineEmits(['onAdd', 'onClose']);
const authorInputs = reactive({
	firstName: '',
	lastName: ''
});

const authorStore = useAuthorStore();
const pageStore = usePageStore();
const { t } = useI18n();

const requiredMessage = t('admin.validationMessages.required');
const minLengthMessage = t('client.validationMessages.minLengthDescription');
const maxLengthMessage = t('client.validationMessages.maxLengthDescription');

const createValidationRules = (field) => ({
	required: helpers.withMessage(requiredMessage, required),
	minLength: helpers.withMessage(minLengthMessage, minLength(1)),
	maxLength: helpers.withMessage(maxLengthMessage, maxLength(150))
});

const authorRules = computed(() => ({
	firstName: createValidationRules('firstName'),
	lastName: createValidationRules('lastName')
}));

const authorValidation = useVuelidate(authorRules, authorInputs);

const showAlert = (type, message) => {
	pageStore.alert.type = type;
	pageStore.alert.message = message;
	pageStore.alert.show = true;
	setTimeout(() => {
		pageStore.alert.show = false;
	}, 5000);
};

const addAuthor = async () => {
	await authorValidation.value.$validate();
	if (!authorValidation.value.$error) {
		const resp = await authorStore.add_new_author(authorInputs);
		if (resp.status === 204) {
			emit('onAdd', true);
			showAlert('success', t('admin.addAuthorForm.successMessage'));
			closeModal();
		} else {
			showAlert('error', t('client.addAuthorForm.errorMessage'));
		}
	}
};

const closeModal = () => {
	authorInputs.firstName = '';
	authorInputs.lastName = '';
	authorValidation.value.$reset();
	emit('onClose');
};
</script>

<template>
	<div v-if="show" class="modal author-modal" tabindex="-1">
		<div class="modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">
						{{ t('admin.addAuthorForm.titleCreate') }}
					</h5>
					<button type="button" class="btn-close" @click="closeModal"></button>
				</div>
				<form novalidate @submit.prevent="addAuthor">
					<div class="modal-body">
						<div class="mb-3">
							<label for="firstName" class="form-label required">{{
								t('admin.addAuthorForm.firstName')
							}}</label>
							<input
								v-model="authorInputs.firstName"
								name="firstName"
								id="firstName"
								type="text"
								class="form-control"
							/>
							<ValidationMessage :model="authorValidation.firstName" />
						</div>
						<div class="mb-3">
							<label for="lastName" class="form-label required">{{
								t('admin.addAuthorForm.lastName')
							}}</label>
							<input
								v-model="authorInputs.lastName"
								name="lastName"
								id="lastName"
								type="text"
								class="form-control"
							/>
							<ValidationMessage :model="authorValidation.lastName" />
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">
							{{ t('admin.addAuthorForm.buttonCreate') }}
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
