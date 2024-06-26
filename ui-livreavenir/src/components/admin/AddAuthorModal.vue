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
const requiredMessage = `${t('admin.validationMessages.required')}`;
const authorRules = computed(() => {
	return {
		firstName: {
			required: helpers.withMessage(requiredMessage, required),
			minLength: helpers.withMessage(
				`${t('client.validationMessages.minLengthDescription')}`,

				minLength(1)
			),
			maxLength: helpers.withMessage(
				`${t('client.validationMessages.maxLengthDescription')}`,
				maxLength(150)
			)
		},
		lastName: {
			required: helpers.withMessage(requiredMessage, required),
			minLength: helpers.withMessage(
				`${t('client.validationMessages.minLengthDescription')}`,

				minLength(1)
			),
			maxLength: helpers.withMessage(
				`${t('client.validationMessages.maxLengthDescription')}`,
				maxLength(150)
			)
		}
	};
});

const authorValidation = useVuelidate(authorRules, authorInputs);
const addAuthor = async () => {
	await authorValidation.value.$validate();
	if (!authorValidation.value.$error) {
		const resp = await authorStore.add_new_author(authorInputs);
		if (resp.status === 204) {
			emit('onAdd', true);
			pageStore.alert.type = 'success';
			pageStore.alert.message = t('admin.addAuthorForm.successMessage');
			pageStore.alert.show = true;
			setTimeout(() => {
				pageStore.alert.show = false;
			}, 5000);
			closeModal();
		} else {
			pageStore.alert.type = 'error';
			pageStore.alert.message = t('client.addAuthorForm.errorMessage');
			pageStore.alert.show = true;
			setTimeout(() => {
				pageStore.alert.show = false;
			}, 3000);
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
