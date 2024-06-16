<script setup>
import { ref, onMounted } from 'vue';
import { storeToRefs } from 'pinia';
import { useI18n } from 'vue-i18n';
import { useRouter, useRoute } from 'vue-router';
import { useAuthStore } from '@/stores/auth-store';
import { usePageStore } from '@/stores/page-store';

const { t } = useI18n();
const pageStore = usePageStore();
const authStore = useAuthStore();
const { isEnabled } = storeToRefs(authStore);
const router = useRouter();
const route = useRoute();
const activationToken = ref(route.params.token);
onMounted(async () => {
	console.log(activationToken.value);
	await authStore.verifyUserRegistration(activationToken.value);
	if (isEnabled) {
		pageStore.alert.type = 'success';
		pageStore.alert.message = `${t('activationAccountPage.activationSuccess')}`;
		pageStore.alert.show = true;
		router.push({ name: 'login' });
		setTimeout(() => {
			pageStore.alert.show = false;
		}, 5000);
	} else {
		pageStore.alert.type = 'error';
		pageStore.alert.message = `${t('activationAccountPage.activationFailed')}`;
		pageStore.alert.show = true;
	}
});
</script>

<template>
	<main class="container-xl my-5">
		<div class="row justify-content-center align-items-center">
			<div class="d-flex justify-content-center mt-4">
				<h3>
					<span v-if="isEnabled">
						{{ t('activationAccountPage.activationSuccess') }}</span
					>
					<span v-else>{{ t('activationAccountPage.activationFailed') }}</span>
				</h3>
			</div>
		</div>
	</main>
</template>
