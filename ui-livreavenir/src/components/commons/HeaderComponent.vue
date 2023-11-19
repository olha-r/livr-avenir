<script setup>
import { storeToRefs } from "pinia";
import { useAuthStore } from "@/stores/auth-store";
import { ref, computed, onMounted } from "vue";

const authStore = useAuthStore();
const { isAdmin } = storeToRefs(authStore);
console.log(isAdmin.value);
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
                        <li class="nav-item" v-if="isAdmin">
                            <RouterLink
                                class="nav-link"
                                :to="{ name: 'admin-dashboard' }"
                                >Admin</RouterLink
                            >
                        </li>
                        <li class="nav-item">
                            <RouterLink class="nav-link" :to="{ name: 'home' }"
                                >Accueil</RouterLink
                            >
                        </li>
                    </ul>

                    <div class="navbar-header navbar-right pull-right">
                        <ul class="nav navbar-nav navbar-right pull-right">
                            <li class="nav-item">
                                <RouterLink
                                    class="nav-link"
                                    :to="{ name: 'register' }"
                                    >S'inscrire</RouterLink
                                >
                            </li>
                            <li class="nav-item">
                                <RouterLink
                                    class="nav-link"
                                    :to="{ name: 'login' }"
                                    >Se connecter</RouterLink
                                >
                            </li>
                            <!-- <li>
                            <i class="bi bi-person-circle"></i>
                        </li> -->
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
    </header>
</template>