<script setup>
import { onBeforeMount } from "vue";
import SearchComponent from "../../components/SearchComponent.vue";
import ToastComponent from "../../components/commons/ToastComponent.vue";
import { storeToRefs } from "pinia";
import { BookStore } from "../../store/book-store";
import { AuthStore } from "../../store/auth-store";
import { usePageStore } from "../../store/page-store";
import ToastComponentVue from "../../components/commons/ToastComponent.vue";
const bookStore = BookStore();
const bookStoreObj = BookStore();
const { lastAddedBooks } = storeToRefs(bookStoreObj);
onBeforeMount(() => {
    bookStoreObj.get_last_added_books();
});
const authStoreObj = AuthStore();
const { token } = authStoreObj;
const baseUrl = import.meta.env.VITE_IMG_BASE_URL;
const remove = async (id) => {
    const resp = await bookStore.delete_book(id, token);
};
const pageStore = usePageStore();
</script>
<template>
    <main class="container-xl my-2 home-page">
        <h3 class="text-center my-4">Livres disponibles</h3>
        <div class="container text-end">
            <RouterLink class="nav-link" :to="{ name: 'add-book' }">
                <button type="button" class="btn btn-primary m-3">
                    Ajouter livre
                </button></RouterLink
            >
        </div>
        <div class="row row-cols-2 row-cols-md-4 g-5">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">ISBN</th>
                        <th scope="col">Titre</th>
                        <th scope="col">Auteur(s)</th>
                        <th scope="col">Image</th>
                        <th scope="col" colspan="2">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(book, index) in lastAddedBooks" :key="book.id">
                        <th scope="row">{{ index + 1 }}</th>
                        <td>{{ book.isbn }}</td>
                        <td>{{ book.title }}</td>
                        <td>
                            <span
                                v-for="(author, index) in book.authors"
                                :key="index"
                            >
                                {{ author.firstName }} {{ author.lastName }}
                                <span v-if="index < book.authors.length - 1"
                                    >,
                                </span>
                            </span>
                        </td>
                        <td>
                            <img
                                :src="
                                    book.coverImageUrl
                                        ? baseUrl + book.coverImageUrl
                                        : baseUrl + 'default-image.jpg'
                                "
                                alt="Cover Image"
                                style="width: auto; height: 120px"
                            />
                        </td>
                        <td>
                            <RouterLink
                                :to="{
                                    name: 'update-book',
                                    params: { id: book.id },
                                }"
                                title="Update sticker"
                            >
                                <i class="bi bi-pencil-square text-primary"></i>
                            </RouterLink>
                        </td>
                        <td>
                            <a href="#" @click="remove(book.id)">
                                <i class="bi bi-trash3 text-danger"></i
                            ></a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </main>
</template>

