<script setup>
import { computed } from 'vue';
const props = defineProps({
	item: {
		type: Object,
		required: true
	},
	index: {
		type: Number,
		required: true
	}
});
const emits = defineEmits(['remove']);
const baseUrl = import.meta.env.VITE_IMG_BASE_URL;
const imageUrl = computed(() =>
	props.item.book.coverImageUrl
		? baseUrl + props.item.book.coverImageUrl
		: baseUrl + 'default-image.jpg'
);
const removeItem = () => {
	emits('remove', props.index);
};
</script>
<template>
	<div class="cart-item d-flex flex-column p-2">
		<div class="d-flex align-items-center mb-2">
			<div class="book-image-cart">
				<img :src="imageUrl" alt="Cover Image" class="img-fluid" />
			</div>
			<div class="item-details ms-3 flex-grow-1">
				<p class="mb-1">{{ item.book.title }}</p>
			</div>
			<div class="points-price ms-auto text-center">
				<p class="mb-0 points-number">
					{{ item.item.pointsPrice }}
				</p>
				<p class="mb-0 points-label">points</p>
			</div>
		</div>
		<p class="mb-1 seller-info">
			Proposé par: {{ item.item.seller.firstName }}
			{{ item.item.seller.lastName }}
		</p>
		<div class="text-end mt-auto">
			<button @click="removeItem" class="btn btn-outline-danger btn-sm mt-2">
				Remove
			</button>
		</div>
	</div>
</template>
