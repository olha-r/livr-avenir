<script setup>
import { storeToRefs } from 'pinia';
import { useCartStore } from '@/stores/cart-store';
import CartItem from '@/components/client/CartItem.vue';
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
	<div class="sidebar d-flex flex-column">
		<div class="d-flex flex-row mb-3 align-items-center">
			<div class="p-2">
				<span @click="closeCart">
					<i class="bi bi-x-square fs-2 logout"></i>
				</span>
			</div>
			<h4 class="mt-2 ms-2 p-2">Panier</h4>
		</div>
		<span v-if="cartItems.length > 0">
			<div class="cart-items-wrapper">
				<CartItem
					v-for="(item, index) in cartItems"
					:key="index"
					:item="item"
					:index="index"
					@remove="removeItem"
				/>
			</div>
			<div class="text-center">
				<button @click="checkout" class="btn btn-primary m-2">Commander</button>
			</div>
		</span>
		<div class="text-center" v-else>
			<span>Rien à commander. Ajoute un livre !</span>
		</div>
	</div>
</template>
