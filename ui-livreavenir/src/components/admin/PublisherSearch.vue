<script setup>
import { ref, computed, onBeforeMount } from "vue";
import { storeToRefs } from "pinia";
import { publisherStore } from "@/stores/publisher-store";

const publisherStoreObj = publisherStore();
const { publisher_list } = storeToRefs(publisherStoreObj);

const searchPublisherQuery = ref("");
const newPublisher = ref({ firstName: "", lastName: "" });
onBeforeMount(() => {
    publisherStoreObj.get_publisher_list();
});
const publishers = ref([]);
const selectedPublishers = ref([]);

// Function to filter publishers based on the searchpublisherQuery
const filteredPublishers = computed(() => {
    publishers.value = publisher_list.value;
    return publishers.value.filter((publisher) => {
        return (
            publisher.name
                .toLowerCase()
                .indexOf(searchPublisherQuery.value.toLowerCase()) != -1
        );
    });
});
// Function to add the selected publisher to the list
const addPublisherToSelectedList = (publisher) => {
    selectedPublishers.value.push(publisher);
    searchPublisherQuery.value = "";
};
</script>

<template>
    <div class="col-md-12 mb-3">
        {{ publisher_list }}
        <!-- Display selected publishers -->
        <div v-if="selectedPublishers.length">
            <span
                class="p-2"
                v-for="publisher in selectedPublishers"
                :key="publisher.id"
            >
                <i class="bi bi-check-circle-fill"></i>
                {{ publisher.name }}
            </span>
        </div>
        <div>
            <div class="input-group">
                <input
                    v-model="searchPublisherQuery"
                    placeholder="Chercher l'edition..."
                    class="form-control"
                />
            </div>
            <div class="div" v-if="searchPublisherQuery">
                <div class="row">
                    <div
                        class="col-md-4"
                        v-for="publisher in filteredPublishers"
                        :key="publisher.code"
                    >
                        <div
                            class="card"
                            @click="addPublisherToSelectedList(publisher)"
                        >
                            <div class="card-body">
                                <p class="card-title">
                                    {{ publisher.name }}
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
