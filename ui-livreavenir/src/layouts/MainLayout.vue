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
        <div
            v-if="error"
            class="col-2 error-message text-center text-white p-3 bg-danger mt-3"
        >
            {{ error }}
        </div>
        <NotificationComponent />
        <RouterView />
        <footer class="text-center text-lg-start text-muted">
            <div class="text-center p-4">&copy; Copyright 2023 Livr'avenir</div>
        </footer>
    </div>
</template>


