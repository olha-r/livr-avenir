<script setup>
import AdminHeader from "../components/admin/AdminHeader.vue";
const components = {
    AdminHeader,
};
</script>

<template>
    <div>
        <AdminHeader />
        <RouterView />
    </div>
</template>


