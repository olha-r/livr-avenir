<script setup>
import { onMounted } from 'vue';
import { storeToRefs } from 'pinia';
import { useI18n } from 'vue-i18n';
import { useUserStore } from '@/stores/user-store';

const { t, d } = useI18n();
const userStore = useUserStore();
const { user_list } = storeToRefs(userStore);
onMounted(async () => {
	await userStore.get_all_users();
});
</script>
<template>
	<div class="container-xl my-2 home-page">
		<h3 class="text-center my-4">Gestion des utilisateurs</h3>
		<div class="table-responsive">
			<table class="table table-striped table-hover align-middle">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">{{ t('admin.manageUsers.firstName') }}</th>
						<th scope="col">{{ t('admin.manageUsers.lastName') }}</th>
						<th scope="col">{{ t('admin.manageUsers.email') }}</th>
						<th scope="col">{{ t('admin.manageUsers.pointsNumber') }}</th>
						<th scope="col">{{ t('admin.manageUsers.registrationDate') }}</th>
						<th scope="col" class="text-center">
							{{ t('admin.manageUsers.isEnabled') }}
						</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(user, index) in user_list" :key="index">
						<td scope="row">{{ index + 1 }}</td>
						<td scope="row">{{ user.firstName }}</td>
						<td scope="row">{{ user.lastName }}</td>
						<td scope="row">{{ user.email }}</td>
						<td scope="row">{{ user.pointsNumber }}</td>
						<td scope="row">
							{{ d(user?.registrationDate, 'short', 'fr-FR') }}
						</td>
						<td scope="row" class="text-center">
							<i
								:class="
									user.isEnabled
										? 'bi bi-check-circle fs-4 text-success'
										: 'bi bi-x-circle fs-4 text-secondary'
								"
							></i>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</template>

<style scoped>
.table-responsive {
	overflow-x: auto;
}
</style>
