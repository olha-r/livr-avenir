import { defineStore } from 'pinia';
import { useSessionStorage } from '@vueuse/core';

export const useCartStore = defineStore('cart', {
	state: () => ({
		cartItems: useSessionStorage('cart', [])
	}),
	actions: {
		addItem(book_item) {
			const existingItem = this.cartItems.find(
				(i) => i.item.id === book_item.item.id
			);
			if (!existingItem) {
				this.cartItems.push({ ...book_item });
			}
		},
		removeItem(index) {
			this.cartItems.splice(index, 1);
		}
	}
});
