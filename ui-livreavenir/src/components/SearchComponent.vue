<script>
export default {
    data() {
        return {
            searchQuery: "",
            searchResults: [],
        };
    },
    methods: {
        submitSearch() {
            // Perform search operation using searchQuery
            // and update searchResults array with matching results
        },
    },
};
</script>

<template>
    <div class="search">
        <form>
            <div class="input-group mb-3">
                <input
                    class="form-control"
                    type="text"
                    placeholder="Tapez pour rechercher"
                    v-model="searchQuery"
                />
                <button
                    class="btn btn-primary"
                    type="submit"
                    @click.prevent="submitSearch"
                    id="bookSearch"
                >
                    <i class="bi bi-search"></i>
                </button>
            </div>
        </form>

        <div v-if="searchResults.length">
            <h2>Search Results:</h2>
            <ul>
                <li v-for="(result, index) in searchResults" :key="index">
                    <router-link :to="result.path">{{
                        result.title
                    }}</router-link>
                    <p>{{ result.description }}</p>
                </li>
            </ul>
        </div>

        <!-- <div v-else>
        <p>No results found.</p>
    </div> -->
    </div>
</template>
