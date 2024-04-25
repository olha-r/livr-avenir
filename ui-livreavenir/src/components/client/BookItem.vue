<script setup>
import { defineProps } from 'vue';
import { storeToRefs } from 'pinia';
import { useI18n } from 'vue-i18n';
import { useCartStore } from '@/stores/cart-store';
import { useAuthStore } from '@/stores/auth-store';
const props = defineProps(['item']);
const { d } = useI18n();
const { t } = useI18n();
const addToCart = (item) => {
	useCartStore().addItem(item);
};
const authStore = useAuthStore();
const { isLoggedIn, userFullName } = storeToRefs(authStore);
const owner = `${props?.item?.seller?.firstName} ${props?.item?.seller?.lastName}`;
</script>
<template>
	<div class="row book-owner pt-3 mt-5 d-flex align-items-center">
		<div class="col-12 col-md-2">
			<p class="text-center">
				{{ item?.seller?.firstName }} {{ item?.seller?.lastName }}
			</p>
		</div>
		<div class="col-12 col-md-2">
			<p class="text-center">
				{{ t('client.bookDetailPage.bookItemDetail.condition') }}
				{{ item?.condition?.name }}
			</p>
			<p class="text-center">
				{{ t('client.bookDetailPage.bookItemDetail.date') }}
			</p>
			<p class="text-center"></p>
			<p class="text-center">{{ d(item?.addedAt, 'short', 'fr-FR') }}</p>
		</div>
		<div class="col-12 col-md-1">
			<p class="text-center">
				{{ t('client.bookDetailPage.bookItemDetail.pointsPrice') }}
			</p>
			<p class="text-center">{{ item?.pointsPrice }}</p>
		</div>
		<div class="col-12 col-md-5">
			<p class="text-center">{{ item?.description }}</p>
		</div>
		<div
			class="col-12 col-md-2 d-flex flex-column justify-content-center"
			v-if="userFullName != owner"
		>
			<p class="text-center m-0">
				{{ t('client.bookDetailPage.bookItemDetail.addToCartBtn') }}
			</p>
			<button @click="addToCart(item)" class="btn">
				<i class="bi bi-handbag"></i>
			</button>
		</div>
		<div class="col-12 col-md-2" v-else>
			<p class="text-center">C'est votre livre</p>
		</div>
	</div>
</template>
