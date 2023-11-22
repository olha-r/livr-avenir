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
  
<script setup>
import { AuthorStore } from "../../stores/author-store";
import { storeToRefs } from "pinia";
import Multiselect from "@vueform/multiselect";
import { onMounted, ref, toRef, watch } from "vue";
const props = defineProps(["authorList"]);
const authorStoreObj = AuthorStore();
const { author_list } = storeToRefs(authorStoreObj);
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
    await authorStoreObj.get_author_list();
    getOptions();
});
const updateAuthorList = (value) => {
    // Emit an event to update the authorList in the parent component
    emit("updateAuthorList", value);
};
</script>
