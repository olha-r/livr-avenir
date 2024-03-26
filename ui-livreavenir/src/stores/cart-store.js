import { defineStore } from 'pinia';
import { useSessionStorage} from '@vueuse/core'

export const useCartStore = defineStore('cart', {
  state: () => ({
    items: useSessionStorage('cart', []),
  }),
  actions: {
    addItem(item) {
      const existingItem = this.items.find((i) => i.id === item.id);
      if (!existingItem) {
        this.items.push({ ...item});
      }
    },
    removeItem(index) {
      this.items.splice(index, 1);
    },
  },
});
