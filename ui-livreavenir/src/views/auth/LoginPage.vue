<script setup>
import { reactive, computed, ref } from 'vue';
import { storeToRefs } from 'pinia';
import { useI18n } from 'vue-i18n';
import { useRouter } from 'vue-router';
import useValidate from '@vuelidate/core';
import { helpers, required } from '@vuelidate/validators';
import { useAuthStore } from '@/stores/auth-store';
import { usePageStore } from '@/stores/page-store';
import ValidationMessage from '@/components/commons/ValidationMessage.vue';

const { t } = useI18n();
const user = reactive({
	email: null,
	password: null
});
const rules = computed(() => {
	return {
		email: {
			required: helpers.withMessage(
				`${t('admin.validationMessages.required')}`,
				required
			)
		},
		password: {
			required: helpers.withMessage(
				`${t('admin.validationMessages.required')}`,
				required
			)
		}
	};
});
const v$ = useValidate(rules, user);
const authStore = useAuthStore();
const { userRole } = storeToRefs(authStore);
const pageStore = usePageStore();
const router = useRouter();
const onSubmit = async () => {
	await v$.value.$validate();
	if (!v$.value.$error) {
		const resp = await authStore.login(user);
		if (resp.status === 200) {
			if (userRole.value == 'ADMIN') {
				router.push({ name: 'admin-dashboard' });
				pageStore.alert.type = 'success';
				pageStore.alert.message = `${t('signIn.success1')} ${user.email} ${t(
					'signIn.success2'
				)}`;
				pageStore.alert.show = true;
				setTimeout(() => {
					pageStore.alert.show = false;
				}, 7000);
			} else {
				router.push({ name: 'home' });
				pageStore.alert.type = 'success';
				pageStore.alert.message = `${t('signIn.success1')} ${user.email} ${t(
					'signIn.success2'
				)}`;
				pageStore.alert.show = true;
				setTimeout(() => {
					pageStore.alert.show = false;
				}, 6000);
			}
		}
		if (!resp) {
			pageStore.alert.type = 'error';
			pageStore.alert.message = `${t('signIn.error')}`;
			pageStore.alert.show = true;
			setTimeout(() => {
				pageStore.alert.show = false;
			}, 6000);
		}
	}
};
const showPassword = ref(false);
const togglePasswordVisibility = () => {
	showPassword.value = !showPassword.value;
};
</script>

<template>
	<main class="container-xl my-5">
		<div class="row justify-content-center align-items-center">
			<div class="d-flex justify-content-center mt-4">
				<h3>{{ t('signIn.title') }}</h3>
			</div>
			<form novalidate @submit.prevent="onSubmit" class="col-md-5">
				<div class="mb-3">
					<label for="email" class="form-label required">{{
						t('signIn.email')
					}}</label>
					<input
						v-model.trim="user.email"
						name="email"
						id="email"
						type="email"
						class="form-control"
						:class="{ 'is-invalid': v$.email.$error }"
						aria-describedby="emailHelp"
					/>
					<ValidationMessage :model="v$.email" />
				</div>

				<div class="mb-3 position-relative">
					<label for="password" class="form-label required">{{
						t('signIn.password')
					}}</label>
					<div class="input-group">
						<input
							v-model.trim="user.password"
							name="password"
							id="password"
							:type="showPassword ? 'text' : 'password'"
							class="form-control"
							:class="{ 'is-invalid': v$.password.$error }"
						/>
						<span
							class="input-group-text password-toggle"
							@click="togglePasswordVisibility"
						>
							<i :class="showPassword ? 'bi bi-eye-slash' : 'bi bi-eye'"></i>
						</span>
					</div>
					<ValidationMessage :model="v$.password" />
				</div>
				<button type="submit" class="btn btn-primary col-12 mb-3">
					{{ t('signIn.button') }}
				</button>
			</form>
		</div>
	</main>
</template>
