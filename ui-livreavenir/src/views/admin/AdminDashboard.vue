<script setup>
import { onBeforeMount } from "vue";
import SearchComponent from "../../components/commons/SearchComponent.vue";
import ToastComponent from "../../components/commons/ToastComponent.vue";
import { storeToRefs } from "pinia";
import { BookStore } from "../../stores/book-store";
import { AuthStore } from "../../stores/auth-store";
import { usePageStore } from "../../stores/page-store";
import ToastComponentVue from "../../components/commons/ToastComponent.vue";
const bookStore = BookStore();
const bookStoreObj = BookStore();
const { bookListForAdmin } = storeToRefs(bookStoreObj);
onBeforeMount(() => {
    bookStoreObj.get_book_list_admin();
});
const authStoreObj = AuthStore();
const { token } = authStoreObj;
const baseUrl = import.meta.env.VITE_IMG_BASE_URL;
const pageStore = usePageStore();
const remove = async (id) => {
    const resp = await bookStore.delete_book(id, token);
    if (resp.status === 204) {
        pageStore.alert.type = "success";
        pageStore.alert.message = `Livre a été supprimer avec success.`;
        pageStore.alert.show = true;
        console.log(`Livre a été supprimer avec success.`);
    } else {
        pageStore.alert.type = "error";
        pageStore.alert.message = `Nous n'avons pas pu supprimer le livre.`;
        pageStore.alert.show = true;
        console.error(`Nous n'avons pas pu supprimer le livre.`);
    }
};
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
        <div class="row row-cols-2 row-cols-md-4 g-5 table-responsive-lg">
            <table class="table table-hover align-middle">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">ISBN</th>
                        <th scope="col">Titre</th>
                        <th scope="col">Auteur(s)</th>
                        <th scope="col">Edition</th>
                        <th scope="col">Categorie</th>
                        <th scope="col">Image</th>
                        <th scope="col" colspan="2">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(item, index) in bookListForAdmin" :key="index">
                        <th scope="row">{{ index + 1 }}</th>
                        <td>{{ item.book.isbn }}</td>
                        <td>{{ item.book.title }}</td>
                        <td>
                            <span
                                v-for="(author, authorIndex) in item.listAuthor"
                                :key="authorIndex"
                            >
                                {{ author.firstName }}
                                {{ author.lastName }}
                                <span
                                    v-if="
                                        authorIndex < item.listAuthor.length - 1
                                    "
                                    >,
                                    <br />
                                </span>
                            </span>
                        </td>
                        <td>
                            {{ item.book.publisher.name }}
                        </td>
                        <td>
                            <span class="badge text-bg-warning me-1">{{
                                item.book.category.name
                            }}</span>
                        </td>
                        <td>
                            <img
                                :src="
                                    item.book.coverImageUrl
                                        ? baseUrl + item.book.coverImageUrl
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
                                    params: { id: item.book.id },
                                }"
                                title="Update book"
                            >
                                <i class="bi bi-pencil-square text-primary"></i>
                            </RouterLink>
                        </td>
                        <td>
                            <a href="#" @click="remove(item.book.id)">
                                <i class="bi bi-trash3 text-danger"></i
                            ></a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </main>
</template>

