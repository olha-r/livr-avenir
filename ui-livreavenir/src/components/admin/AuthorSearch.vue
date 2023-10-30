<template>
    <div class="col-md-12 mb-3">
        <!-- Display selected authors -->
        <div v-if="selectedAuthors.length">
            <span
                class="p-2"
                v-for="author in selectedAuthors"
                :key="author.id"
            >
                <i class="bi bi-check-circle-fill"></i>
                {{ author.firstName }} {{ author.lastName }}
            </span>
        </div>
        <div>
            <div class="input-group">
                <input
                    v-model="searchAuthorQuery"
                    placeholder="Chercher auteur..."
                    class="form-control"
                />
            </div>
            <div class="div" v-if="searchAuthorQuery">
                <div class="row">
                    <div
                        class="col-md-4"
                        v-for="author in filteredAuthors"
                        :key="author.authorCode"
                    >
                        <div
                            class="card"
                            @click="addAuthorToSelectedList(author)"
                        >
                            <div class="card-body">
                                <p class="card-title">
                                    {{ author.firstName }}
                                    {{ author.lastName }}
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script setup>
import { ref, computed, onBeforeMount } from "vue";
import { storeToRefs } from "pinia";
import { AuthorStore } from "../../stores/author-store";
const authorStore = AuthorStore();
const authorStoreObj = AuthorStore();
const { author_list } = storeToRefs(authorStoreObj);

const searchAuthorQuery = ref("");
const newAuthor = ref({ firstName: "", lastName: "" });
onBeforeMount(() => {
    authorStoreObj.get_author_list();
});
const authors = ref([]);
const selectedAuthors = ref([]);

// Function to filter authors based on the searchAuthorQuery
const filteredAuthors = computed(() => {
    authors.value = author_list.value;
    return authors.value.filter((author) => {
        return (
            author.firstName
                .toLowerCase()
                .indexOf(searchAuthorQuery.value.toLowerCase()) != -1 ||
            author.lastName
                .toLowerCase()
                .indexOf(searchAuthorQuery.value.toLowerCase()) != -1
        );
    });
});
// Function to add the selected author to the list
const addAuthorToSelectedList = (author) => {
    selectedAuthors.value.push(author);
    searchAuthorQuery.value = "";
};
</script>