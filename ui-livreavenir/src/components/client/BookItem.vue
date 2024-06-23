<script setup>
import { storeToRefs } from 'pinia';
import { useI18n } from 'vue-i18n';
import { useCartStore } from '@/stores/cart-store';
import { useAuthStore } from '@/stores/auth-store';

const props = defineProps(['item', 'book']);
const { d, t } = useI18n();

const addToCart = (item, book) => {
	const cartStore = useCartStore();
	const bookItem = {
		item: item,
		book: book
	};
	cartStore.addItem(bookItem);
	cartStore.cartItems.find((i) => i.item.id === bookItem.item.id);
};

const authStore = useAuthStore();
const { userFullName } = storeToRefs(authStore);
const owner = `${props.item?.seller?.firstName} ${props.item?.seller?.lastName}`;

const getConditionBadgeClass = (condition) => {
	switch (condition) {
		case 'Comme neuf':
			return 'badge bg-success badge-condition';
		case 'Très bon':
			return 'badge bg-primary badge-condition';
		case 'Bon':
			return 'badge bg-info badge-condition';
		case 'Satisfaisant':
			return 'badge bg-warning badge-condition';
		default:
			return 'badge bg-secondary badge-condition';
	}
};
</script>
<template>
	<div
		class="row book-owner pt-3 mt-5 d-flex align-items-center justify-content-center"
	>
		<div class="col-12 col-md-2 text-center">
			<span class="seller-name-label">{{
				t('client.bookDetailPage.bookItemDetail.date')
			}}</span>

			<div class="ms-md-2">
				<p class="seller-name">
					{{ item?.seller?.firstName }} {{ item?.seller?.lastName }}
				</p>
				<p class="seller-date">{{ d(item?.addedAt, 'short', 'fr-FR') }}</p>
			</div>
		</div>
		<div class="col-12 col-md-4">
			<div class="row">
				<div class="col">
					<span class="book-item-label text-center">{{
						t('client.bookDetailPage.bookItemDetail.condition')
					}}</span>
					<p class="book-item-value text-center">
						<span
							:class="getConditionBadgeClass(item?.condition?.name)"
							class="badge badge-condition"
							>{{ item?.condition?.name }}</span
						>
					</p>
				</div>
				<div class="col">
					<div class="points-container text-center">
						<span class="points">{{ item?.pointsPrice }}</span>
						<span class="points-label">{{
							t('client.bookDetailPage.bookItemDetail.pointsPrice')
						}}</span>
					</div>
				</div>
			</div>
		</div>

		<!-- <div class="col-12 col-md-2">
			<div class="points-container text-center">
				<span class="points">{{ item?.pointsPrice }}</span>
				<span class="points-label">{{
					t('client.bookDetailPage.bookItemDetail.pointsPrice')
				}}</span>
			</div>
		</div> -->
		<div class="col-12 col-md-4">
			<p class="book-item-description text-center">{{ item?.description }}</p>
		</div>
		<div
			class="col-12 col-md-2 d-flex flex-column justify-content-center"
			v-if="userFullName !== owner"
		>
			<button @click="addToCart(item, book)" class="btn">
				<i class="bi bi-handbag"></i>
			</button>
		</div>
		<div
			class="col-12 col-md-2 text-center owner-message d-flex align-items-center justify-content-center"
			v-else
		>
			<p class="m-0">{{ t('client.bookDetailPage.bookItemDetail.owner') }}</p>
		</div>
	</div>
</template>
