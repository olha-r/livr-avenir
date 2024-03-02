<script setup>
import { onMounted, ref } from "vue";
import { storeToRefs } from "pinia";
import { useRoute } from "vue-router";
import { useBookStore } from "@/stores/book-store";

const baseUrl = import.meta.env.VITE_IMG_BASE_URL;
const bookStore = useBookStore();
const { book_details } = storeToRefs(bookStore);
const route = useRoute();
const book_id = route.params.id;
const imageUrl = ref("");
onMounted(async () => {
    await bookStore.get_book_details(book_id);
});
</script>

<template>
    <main class="container-xl my-5">
        <div
            class="justify-content-center align-items-center book-details-container"
        >
            <!-- <div class="input-group mb-3">
                <input
                    class="form-control"
                    type="text"
                    placeholder="Tapez pour rechercher"
                />
                <button class="btn btn-primary" type="button" id="bookSearch">
                    <i class="bi bi-search"></i>
                </button>
            </div> -->
            <div class="row mt-3">
                <h3>{{ book_details?.book?.title }}</h3>
                <div class="row d-flex justify-content-center">
                    <div class="col">
                        <p class="author">
                            <span
                                v-for="(
                                    author, index
                                ) in book_details?.authorlist"
                                :key="index"
                            >
                                {{ author.firstName }} {{ author.lastName }}
                                <span v-if="index < authorList.length - 1"
                                    >,
                                </span> </span
                            ><span class="text-muted fs-6"> (Auteur(s))</span>
                        </p>
                    </div>
                </div>
                <div class="col-12 col-md-5 book-image ps-0">
                    <img
                        :src="
                            book_details?.book?.coverImageUrl
                                ? baseUrl + book_details?.book?.coverImageUrl
                                : baseUrl + 'default-image.jpg'
                        "
                        alt="Cover Image"
                        class="img-fluid"
                    />
                </div>
                <div class="col book-info p-3">
                    <p class="fst-italic">Résumé:</p>
                    <p>{{ book_details?.book?.summary }}</p>
                    <div class="row">
                        <p>
                            <span class="badge text-bg-warning me-1">{{
                                book_details?.book?.category?.name
                            }}</span>
                        </p>
                        <p>Caractéristiques:</p>

                        <div class="col">
                            <p>
                                <span class="fw-light">ISBN: </span
                                >{{ book_details?.book?.isbn }}
                            </p>
                        </div>
                        <div class="col">
                            <p>
                                <span class="fw-light"
                                    >L'année de parution: </span
                                >{{ book_details?.book?.publicationYear }}
                            </p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <p>
                                <span class="fw-light">Edition </span
                                >{{ book_details?.book?.publisher?.name }}
                            </p>
                        </div>
                        <div class="col">
                            <p>
                                <span class="fw-light">Nombres de pages: </span
                                >{{ book_details?.book?.pageCount }}
                            </p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <p>
                                <span class="fw-light">Langue: </span>
                                {{ book_details?.book?.language?.name }}
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row book-owner pt-3 mt-5 d-flex align-items-center">
                <div class="col-12 col-md-2 d-flex justify-content-center">
                    <img
                        src="../assets/images/avatar-man.png"
                        class="avatar"
                        alt="avatar"
                    />
                </div>
                <div class="col-12 col-md-2">
                    <p class="text-center">Jonh Doe</p>
                </div>
                <div class="col-12 col-md-2">
                    <p class="text-center">Condition</p>
                    <p class="text-center">Ajouté le 23/10/22</p>
                </div>
                <div class="col-12 col-md-2">
                    <p class="text-center">Points</p>
                    <p class="text-center">points</p>
                </div>
                <div
                    class="col-12 col-md-2 d-flex flex-column justify-content-center"
                >
                    <p class="text-center m-0">Une question?</p>
                    <button class="btn" href="#">
                        <i class="bi bi-envelope"></i>
                    </button>
                </div>
                <div
                    class="col-12 col-md-2 d-flex flex-column justify-content-center"
                >
                    <p class="text-center m-0">Emprenter</p>
                    <button class="btn" href="#">
                        <i class="bi bi-handbag"></i>
                    </button>
                </div>
            </div>
        </div>
    </main>
</template>