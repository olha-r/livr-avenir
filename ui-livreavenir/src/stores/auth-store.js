import { defineStore } from 'pinia'
import {AuthHttp} from "../services/AuthHttp";
import { useStorage } from '@vueuse/core'

export const AuthStore = defineStore('auth-store', {
    state: () => ({
        users: [],
        user: useStorage('user', null),
        userRole: useStorage('user-role', null),
        isLoggedIn: useStorage('is-logged-in', false),
        token: useStorage('token', null)
    }),
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
        }
        return promise;
},

}, 
})