<script setup>
import { storeToRefs } from "pinia";
import { useI18n } from "vue-i18n";
import { useAuthStore } from "@/stores/auth-store";

const { t } = useI18n();
const authStore = useAuthStore();
const { isAdmin, isLoggedIn, userFullName } = storeToRefs(authStore);
const logout = () => {
    authStore.logout();
};
</script>

<template>
    <header>
        <nav class="navbar navbar-expand-lg bg-light">
            <div class="container-fluid">
                <RouterLink class="navbar-brand" :to="{ name: 'home' }"
                    ><img
                        src="../../assets/images/logo/logo.png"
                        alt="logo"
                        style="height: 40px"
                /></RouterLink>
                <button
                    class="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                >
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <RouterLink
                                class="nav-link"
                                :to="{ name: 'home' }"
                                >{{ t("navbar.homePageItem") }}</RouterLink
                            >
                        </li>
                        <li class="nav-item" v-if="isLoggedIn">
                            <RouterLink
                                class="nav-link"
                                :to="{ name: 'profile' }"
                                >User profile</RouterLink
                            >
                        </li>
                        <li class="nav-item" v-if="isLoggedIn && isAdmin">
                            <RouterLink
                                class="nav-link"
                                :to="{ name: 'admin-dashboard' }"
                                >{{ t("navbar.adminItem") }}</RouterLink
                            >
                        </li>
                    </ul>

                    <div class="navbar-header navbar-right pull-right">
                        <ul class="nav navbar-nav navbar-right pull-right">
                            <li class="nav-item" v-if="!isLoggedIn">
                                <RouterLink
                                    class="nav-link"
                                    :to="{ name: 'register' }"
                                    >{{ t("navbar.signUp") }}</RouterLink
                                >
                            </li>
                            <li class="nav-item" v-if="!isLoggedIn">
                                <RouterLink
                                    class="nav-link"
                                    :to="{ name: 'login' }"
                                    >{{ t("navbar.login") }}</RouterLink
                                >
                            </li>

                            <!-- <li>
                            <i class="bi bi-person-circle"></i>
                        </li> -->
                        </ul>
                    </div>
                </div>
                <div class="navbar-text">
                    <a href="#" @click="logout" v-if="isLoggedIn">
                        <i class="bi bi-box-arrow-right fs-2 logout me-4"></i>
                    </a>
                </div>
            </div>
        </nav>
    </header>
</template>