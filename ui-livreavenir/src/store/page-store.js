import { defineStore } from 'pinia'

export const usePageStore = defineStore('page-store', {
    state: () => ({
        alert: {
            show: null,
            type: "success",
            message: "",
        }
    }),
})