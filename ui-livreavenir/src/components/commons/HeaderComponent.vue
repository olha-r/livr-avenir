<script setup>
import { storeToRefs } from "pinia";
import { ref } from "vue";
import { useI18n } from "vue-i18n";
import { useAuthStore } from "@/stores/auth-store";
import { useCartStore } from "@/stores/cart-store";
import CartSidebar from "@/components/client/CartSidebar.vue";

const { t } = useI18n();
const authStore = useAuthStore();
const cartStore = useCartStore();
const { isAdmin, isLoggedIn, userFullName } = storeToRefs(authStore);
const logout = () => {
    authStore.logout();
};
const showCartSidebar = ref(false);
const toggleCartSidebar = () => {
    showCartSidebar.value = !showCartSidebar.value;
};
const closeCartSidebar = () => {
    showCartSidebar.value = false;
};
</script>

<template>
    <header
        class="d-flex flex-wrap justify-content-around py-3 mb-4 border-bottom"
    >
        <RouterLink
            class="d-flex align-items-center col-md-3 mb-2 mb-md-0"
            :to="{ name: 'home' }"
            ><img
                src="../../assets/images/logo/logo.png"
                alt="logo"
                style="height: 35px"
        /></RouterLink>
        <ul class="nav nav-pills">
            <li class="nav-item">
                <RouterLink class="nav-link px-2" :to="{ name: 'home' }">{{
                    t("navbar.homePageItem")
                }}</RouterLink>
            </li>
            <li class="nav-item" v-if="isLoggedIn">
                <RouterLink class="nav-link px-2" :to="{ name: 'profile' }"
                    >User profile</RouterLink
                >
            </li>
            <li class="nav-item" v-if="isLoggedIn && isAdmin">
                <RouterLink
                    class="nav-link px-2"
                    :to="{ name: 'admin-dashboard' }"
                    >{{ t("navbar.adminItem") }}</RouterLink
                >
            </li>
            <li class="nav-item" v-if="!isLoggedIn">
                <RouterLink
                    class="btn btn-outline-primary"
                    :to="{ name: 'register' }"
                    >{{ t("navbar.signUp") }}</RouterLink
                >
            </li>
            <li class="nav-item" v-if="!isLoggedIn">
                <RouterLink
                    class="btn btn-outline-primary"
                    :to="{ name: 'login' }"
                    >{{ t("navbar.login") }}</RouterLink
                >
            </li>
            <li class="nav-item" v-else>
                <span @click="toggleCartSidebar">
                    <span class="logout pe-2">{{
                        cartStore.items.length
                    }}</span>
                    <i class="bi bi-cart-fill fs-2 logout pe-4"></i>
                </span>
                <div :class="['cart-sidebar', { show: showCartSidebar }]">
                    <CartSidebar @close="closeCartSidebar" />
                </div>
                <a href="#" @click="logout" v-if="isLoggedIn">
                    <i class="bi bi-box-arrow-right fs-2 logout me-4"></i>
                </a>
            </li>
        </ul>
    </header>
</template>