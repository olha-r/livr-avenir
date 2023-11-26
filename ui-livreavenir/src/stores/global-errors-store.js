import { defineStore } from 'pinia';

export const useGlobalStore = defineStore('global', {
  state: () => ({
    error: null,
  }),

  actions: {
    setError(message) {
      this.error = message;
      console.log("Error: " + this.error);
    },

    clearError() {
      this.error = null;
    },
  },
});


