<script setup>
import AdminHeader from "../components/admin/AdminHeader.vue";
import NotificationComponent from "../components/commons/NotificationComponent.vue";
</script>

<template>
    <div>
        <AdminHeader />
        <NotificationComponent />
        <RouterView />
    </div>
</template>


