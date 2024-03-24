<script setup>
import { storeToRefs } from "pinia";
import HeaderComponent from "../components/commons/HeaderComponent.vue";
import NotificationComponent from "../components/commons/NotificationComponent.vue";
import { useGlobalStore } from "../stores/global-errors-store";
const store = useGlobalStore();
const { error } = storeToRefs(store);
</script>

<template>
    <div>
        <HeaderComponent />
        <div>
            <div
                v-if="error"
                class="text-center alert alert-danger container mt-3 p-3 mt-3"
            >
                {{ error }}
            </div>
        </div>
        <NotificationComponent />
        <RouterView />
        <footer class="text-center text-lg-start text-muted">
            <div class="text-center p-4">&copy; Copyright 2024 Livr'avenir</div>
        </footer>
    </div>
</template>


