import { defineStore } from 'pinia';
import { useSessionStorage } from '@vueuse/core';

export const useCartStore = defineStore('cart', {
	state: () => ({
		cartItems: useSessionStorage('cart', [])
	}),
	actions: {
		addItem(item) {
			const existingItem = this.cartItems.find((i) => i.id === item.id);
			if (!existingItem) {
				this.cartItems.push({ ...item });
			}
		},
		removeItem(index) {
			this.cartItems.splice(index, 1);
		}
	}
});
