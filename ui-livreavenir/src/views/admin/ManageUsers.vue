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
				<caption>
					Manage users
				</caption>
				<thead>
					<tr>
						<th id="header-index">#</th>
						<th id="header-first-name">
							{{ t('admin.manageUsers.firstName') }}
						</th>
						<th id="header-last-name">{{ t('admin.manageUsers.lastName') }}</th>
						<th id="header-email">{{ t('admin.manageUsers.email') }}</th>
						<th id="header-points-number">
							{{ t('admin.manageUsers.pointsNumber') }}
						</th>
						<th id="header-registration-date">
							{{ t('admin.manageUsers.registrationDate') }}
						</th>
						<th id="header-is-enabled" class="text-center">
							{{ t('admin.manageUsers.isEnabled') }}
						</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(user, index) in user_list" :key="index">
						<td headers="header-index">{{ index + 1 }}</td>
						<td headers="header-first-name">{{ user.firstName }}</td>
						<td headers="header-last-name">{{ user.lastName }}</td>
						<td headers="header-email">{{ user.email }}</td>
						<td headers="header-points-number">{{ user.pointsNumber }}</td>
						<td headers="header-registration-date">
							{{ d(user?.registrationDate, 'short', 'fr-FR') }}
						</td>
						<td headers="header-is-enabled" class="text-center">
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
