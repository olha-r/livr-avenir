<script setup>
import AdminHeader from '../components/admin/AdminHeader.vue';
import NotificationComponent from '../components/commons/NotificationComponent.vue';
import ErrorComponent from '../components/commons/ErrorComponent.vue';
</script>

<template>
	<div>
		<AdminHeader />
		<ErrorComponent />
		<NotificationComponent />
		<RouterView />
	</div>
</template>
