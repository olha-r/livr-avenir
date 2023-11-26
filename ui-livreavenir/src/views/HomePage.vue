<script setup>
import { onBeforeMount } from "vue";
import SearchComponent from "../components/commons/SearchComponent.vue";
import { storeToRefs } from "pinia";
import { BookStore } from "../stores/book-store";

import { useI18n } from "vue-i18n";
const { t } = useI18n();

const bookStoreObj = BookStore();
const { lastAddedBooks } = storeToRefs(bookStoreObj);
onBeforeMount(() => {
    bookStoreObj.get_last_added_books();
});
const baseUrl = import.meta.env.VITE_IMG_BASE_URL;
</script>
<template>
    <main class="container-xl my-2 home-page">
        <div class="mb-4 rounded-3 home-page-img position-relative">
            <div
                class="position-absolute top-50 start-50 translate-middle-x text-center"
            >
                <h1>{{ t("homepage.mainTitle") }}</h1>
                <!-- <h6>
                    Envie d'echanger tes livres? Decouvre comment ça marche.
                </h6>
                <button class="btn btn-lg btn-dark" type="button">
                    A propos
                </button> -->
            </div>
        </div>
        <!-- <SearchComponent /> -->

        <!-- <div class="d-flex flex-row justify-content-around mb-3">
            <button type="button" class="btn btn-primary me-3">
                57 livres disponible
            </button>
            <button type="button" class="btn btn-primary me-3">
                21 livres emprentés
            </button>
            <button type="button" class="btn btn-primary">
                6 livres ajoutés en favoris
            </button>
        </div> -->

        <h3 class="text-center my-4">{{ t("homepage.title") }}</h3>

        <div
            class="row row-cols-2 row-cols-md-3 row-cols-lg-4 g-4 justify-content-center align-items-start"
        >
            <div
                class="col d-flex align-items-stretch"
                v-for="(item, index) in lastAddedBooks"
                :key="index"
            >
                <!-- <a :href="`/books/${item.book.id}/detail`"> -->
                <div class="card mx-3 d-flex flex-column">
                    <img
                        :src="
                            item.book.coverImageUrl
                                ? baseUrl + item.book.coverImageUrl
                                : baseUrl + 'default-image.jpg'
                        "
                        class="card-img-bottom"
                        style="object-fit: cover"
                        alt="Cover Image"
                    />

                    <div
                        class="card-body d-flex flex-column justify-content-between"
                    >
                        <h6 class="card-title text-center">
                            {{ item.book.title }}
                        </h6>
                        <div class="card-subtitle mb-2 text-muted text-center">
                            <p
                                class="p-0 m-0"
                                v-for="(author, authorIndex) in item.listAuthor"
                                :key="authorIndex"
                            >
                                {{ author.firstName }} {{ author.lastName }}
                                <span
                                    v-if="
                                        authorIndex < item.listAuthor.length - 1
                                    "
                                    >,
                                </span>
                            </p>
                        </div>
                    </div>
                </div>
                <!-- </a> -->
            </div>
        </div>
    </main>
</template>
<style scoped>
.card-img-bottom {
    height: 350px;
    object-fit: cover;
    /* background-size: cover;
    background-position: center; */
}
</style>

