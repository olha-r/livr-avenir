<script setup>
import { storeToRefs } from "pinia";
import { useI18n } from "vue-i18n";
import { useAuthStore } from "@/stores/auth-store";

const { t } = useI18n();
const authStore = useAuthStore();
const { isLoggedIn, userFullName } = storeToRefs(authStore);
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
                        style="height: 35px"
                /></RouterLink>

                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-left">
                        <li class="nav-item">
                            <RouterLink
                                class="nav-link"
                                :to="{ name: 'home' }"
                                >{{ t("navbar.homePageItem") }}</RouterLink
                            >
                        </li>
                        <li>
                            <RouterLink
                                class="nav-link"
                                :to="{ name: 'admin-dashboard' }"
                                >{{ t("navbarAdmin.bookItem") }}</RouterLink
                            >
                        </li>
                        <li>
                            <!-- <li class="nav-item">
                                <RouterLink
                                class="nav-link"
                                :to="{ name: 'manage-users' }"
                                >{{ t("navbarAdmin.userItem") }}</RouterLink
                                >
                            </li> -->
                        </li>
                    </ul>
                </div>
                <div class="navbar-text me-5 navbar-hello" v-if="isLoggedIn">
                    {{ t("navbarAdmin.hello") }}, {{ userFullName }}!
                </div>

                <div class="navbar-text">
                    <a href="#" @click="logout">
                        <i class="bi bi-box-arrow-right fs-2 logout me-4"></i>
                    </a>
                </div>
            </div>
        </nav>
    </header>
</template>