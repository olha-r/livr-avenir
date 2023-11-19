<script>
export default {
    props: {
        items: {
            type: Array,
            default: [],
        },
    },
};
</script>
<template>
    <option disabled>Choisir author...</option>
    <option v-for="item in items" :key="item.id" :value="item.id">
        {{ item.firstName }} {{ item.lastName }}
    </option>
</template>
