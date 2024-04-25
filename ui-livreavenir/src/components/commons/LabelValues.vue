<script setup>
const props = defineProps(['items']);
</script>

<template>
	<option v-for="item in items" :key="item.id" :value="item.id">
		{{ item.name }}
	</option>
</template>
