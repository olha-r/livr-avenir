<script setup>
import { storeToRefs } from 'pinia';
import { useI18n } from 'vue-i18n';
import { useAuthStore } from '@/stores/auth-store';

const { t } = useI18n();
const authStore = useAuthStore();
const { isLoggedIn, userFullName } = storeToRefs(authStore);
const logout = () => {
	authStore.logout();
};
</script>

<template>
	<header>
		<nav class="navbar navbar-expand-lg bg-light navigation border-bottom">
			<div
				class="container-fluid d-flex align-items-center justify-content-between"
			>
				<div class="d-flex align-items-center">
					<RouterLink
						class="navbar-brand d-flex align-items-center"
						:to="{ name: 'admin-dashboard' }"
					>
						<img src="/images/logo.png" alt="logo" class="logo me-2" />
					</RouterLink>
					<ul class="navbar-nav d-flex flex-row ms-3">
						<li class="nav-item">
							<RouterLink class="nav-link" :to="{ name: 'admin-dashboard' }">
								<i class="bi bi-book fs-5"></i>
							</RouterLink>
						</li>
						<li class="nav-item">
							<RouterLink class="nav-link" :to="{ name: 'manage-users' }">
								<i class="bi bi-people fs-5"></i>
							</RouterLink>
						</li>
					</ul>
				</div>
				<div class="navbar-text me-5 navbar-hello" v-if="isLoggedIn">
					{{ t('navbarAdmin.hello') }}, {{ userFullName }}!
				</div>

				<div class="navbar-text">
					<a href="#" @click="logout">
						<i class="bi bi-box-arrow-right fs-5 logout me-4"></i>
					</a>
				</div>
			</div>
		</nav>
	</header>
</template>
