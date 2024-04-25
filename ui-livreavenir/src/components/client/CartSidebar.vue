<script setup>
import { defineEmits } from 'vue';
import { storeToRefs } from 'pinia';
import { useCartStore } from '@/stores/cart-store';

const emits = defineEmits(['close']);
const cartStore = useCartStore();
const { cartItems } = storeToRefs(cartStore);
const removeItem = (index) => {
	cartStore.removeItem(index);
};
const checkout = () => {
	console.log('Commander le livre');
};
const closeCart = () => {
	emits('close');
};
</script>
<template>
	<div class="d-flex flex-row mb-3">
		<div class="p-2">
			<span @click="$emit('close')">
				<i class="bi bi-x-square fs-2 logout"></i>
			</span>
		</div>
		<h4 class="mt-2 ms-2 p-2">Panier</h4>
	</div>
	<span v-if="cartItems.length > 0">
		<div
			class="d-flex justify-content-between p-3 align-items-center"
			v-for="(item, index) in cartItems"
			:key="index"
		>
			<p>Livre: {{ item?.id }}</p>
			<p>price {{ item?.pointsPrice }} points</p>
			<button @click="removeItem(index)" class="btn btn-outline-danger">
				Remove
			</button>
		</div>
		<div class="text-center">
			<button @click="checkout" class="btn btn-primary m-5">Commander</button>
		</div>
	</span>
	<div class="text-center" v-else>
		<span> Rien à commander. Ajoute un livre ! </span>
	</div>
</template>
