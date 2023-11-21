import { defineStore } from 'pinia'
import {AuthHttp} from "../services/AuthHttp";
import { useStorage } from '@vueuse/core'

export const useAuthStore = defineStore('auth-store', {
    state: () => ({
        users: [],
        user: useStorage('user', null),
        userRole: useStorage('user-role', null),
        isLoggedIn: useStorage('is-logged-in', false),
        token: useStorage('token', null),
        userFullName: useStorage('userFullName', null)
    }),
    getters: {
        isAuthenticated: (state) => !!state?.user,
        isAdmin: (state) => state?.userRole === 'ADMIN',
      },
    actions: {
      async register(payload) {
        const authHttp = new AuthHttp();
        const promise = await authHttp.register(payload);
        return promise;
      },

      async login(payload) {
        const authHttp = new AuthHttp();

        const promise = await authHttp.login(payload);
        if(promise.status === 200) {
            this.token = promise.body.token;
            this.userRole = promise.body.role
            this.isLoggedIn = true;
            console.log(promise.body);
            this.userFullName= `${promise.body.firstName} ${promise.body.lastName}`;
        }
        return promise;
    },  

    async logout() {
        this.token = null;
        this.userRole = null;
        this.isLoggedIn = false;
        this.user = null;
        this.userFullName = null;
        // Ajoutez appele d'API de déconnexion
    },
}, 
})