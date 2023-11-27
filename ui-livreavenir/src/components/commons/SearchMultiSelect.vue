<script setup>
import { onMounted, ref, toRef, watch } from "vue";
import Multiselect from "@vueform/multiselect";
import { storeToRefs } from "pinia";
import { useAuthorStore } from "@/stores/author-store";

const props = defineProps(["authorList"]);

const authorStore = useAuthorStore();
const { author_list } = storeToRefs(authorStore);
const options = ref([]);
const emit = defineEmits(["updateAuthorList"]);
const authorIdList = ref([]);
const authorsRo = toRef(props, "authorList"); // react to prop
watch(authorsRo, (value) => {
    authorIdList.value = authorsRo.value; // OK, textEnvoye is yours
});
const getOptions = () => {
    const author_options = author_list.value;
    for (const author of author_options) {
        const option = {};
        option["value"] = author.id;
        option["label"] = `${author.firstName} ${author.lastName}`;
        options.value.push(option);
    }
};
onMounted(async () => {
    await authorStore.get_author_list();
    getOptions();
});
const updateAuthorList = (value) => {
    // Emit an event to update the authorList in the parent component
    emit("updateAuthorList", value);
};
</script>

<template>
    <div>
        <Multiselect
            v-model.trim="authorIdList"
            :model-value="props.authorList"
            mode="tags"
            :options="options"
            :searchable="true"
            @input="updateAuthorList"
        />
    </div>
</template>