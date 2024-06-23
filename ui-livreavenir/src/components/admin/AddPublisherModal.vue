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
const publisherRules = computed(() => {
	return {
		name: {
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

const publisherValidation = useVuelidate(publisherRules, publisherInputs);
const addPublisher = async () => {
	await publisherValidation.value.$validate();
	if (!publisherValidation.value.$error) {
		const resp = await publisherStore.add_new_publisher(publisherInputs);
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
	publisherInputs.name = '';
	publisherValidation.value.$reset();
	emit('onClose');
};
</script>

<template>
	<div
		v-if="show"
		class="modal author-modal"
		tabindex="-1"
		style="display: block"
	>
		<div class="modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">
						{{ t('admin.addAuthorForm.titleCreate') }}
					</h5>
					<button type="button" class="btn-close" @click="closeModal"></button>
				</div>
				<form novalidate @submit.prevent="addPublisher">
					<div class="modal-body">
						<div class="mb-3">
							<label for="name" class="form-label required">{{
								t('admin.addAuthorForm.firstName')
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
