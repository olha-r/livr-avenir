<script setup>
import { useI18n } from 'vue-i18n';
const { t } = useI18n();
</script>
<template>
	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-3 text-center">
				<h1 class="m-5">{{ t('errorPages.e401.label') }}</h1>
				<h2 class="m-5">
					{{ t('errorPages.e401.message') }}
				</h2>
				<router-link :to="{ name: 'home' }" class="btn btn-primary m-5">{{
					t('errorPages.redirectToHomepage')
				}}</router-link>
			</div>
		</div>
	</div>
</template>
