<script setup>
import { storeToRefs } from 'pinia';
import { useI18n } from 'vue-i18n';
import HeaderComponent from '../components/commons/HeaderComponent.vue';
import NotificationComponent from '../components/commons/NotificationComponent.vue';
import ErrorComponent from '@/components/commons/ErrorComponent.vue';
const { t } = useI18n();
</script>

<template>
	<div>
		<HeaderComponent />
		<ErrorComponent />
		<NotificationComponent />
		<RouterView />
		<footer class="text-center text-lg-start text-muted">
			<div class="text-center p-4">
				&copy;
				{{ t('mainLayout.copyright') }}
			</div>
		</footer>
	</div>
</template>
