import { defineStore } from 'pinia';

export const useGlobalStore = defineStore('global', {
  state: () => ({
    error: null,
  }),

  actions: {
    setError(message) {
      this.error = message;
    },

    clearError() {
      this.error = null;
    },
  },
});


