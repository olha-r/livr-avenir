<template>
    <div>
        <div
            v-if="pageStore.alert.show"
            class="text-center alert container mt-3"
            :class="alertClass"
        >
            {{ pageStore.alert.message }}
        </div>
    </div>
</template>
  
<script setup>
import { computed, ref, watch } from "vue";
import { usePageStore } from "../../stores/page-store";

const pageStore = usePageStore();
const alertClass = computed(() => {
    return `text-center alert alert-${pageStore.alert.type}`;
});

watch(
    () => pageStore.alert,
    (newAlert) => {
        type.value = newAlert.type;
        message.value = newAlert.message;
        show.value = newAlert.show;
        // Automatically hide the alert after 3 seconds Don't work
        if (show.value) {
            setTimeout(() => {
                pageStore.alert.show = false;
            }, 1000);
        }
    }
);
</script>
