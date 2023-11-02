<script setup>
import HeaderComponent from "../components/commons/HeaderComponent.vue";
import NotificationComponent from "../components/commons/NotificationComponent.vue";
</script>

<template>
    <div>
        <HeaderComponent />
        <NotificationComponent />
        <RouterView />
        <footer class="text-center text-lg-start text-muted">
            <div class="text-center p-4">&copy; Copyright 2022 Livr'avenir</div>
        </footer>
    </div>
</template>


