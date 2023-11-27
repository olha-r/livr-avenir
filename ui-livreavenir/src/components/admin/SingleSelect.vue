<script setup>
import Multiselect from "@vueform/multiselect";
defineProps({
    options: [],
    value: Number,
});
const value = null;
const updateValue = (value) => {
    // Emit an event to update the value in the parent component
    emit("updateValue", value);
};
</script>

<template>
    [{{ options }}]
    <div>
        <Multiselect
            v-model.trim="value"
            :options="options"
            @select="updateValue"
        />
    </div>
</template>