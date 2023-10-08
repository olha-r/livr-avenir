<template>
    <main class="container-xl my-5">
        <div
            class="justify-content-center align-items-center book-details-container"
        >
            <div class="text-center">
                <h1>Ajouter un livre</h1>
            </div>
            <form novalidate @submit.prevent="add_new_book">
                <div class="row">
                    <div class="col-md-12 mb-3">
                        <label for="isbn" class="form-label">ISBN</label>
                        <input
                            v-model.trim="inputs.isbn"
                            name="isbn"
                            id="isbn"
                            type="text"
                            class="form-control"
                        />
                    </div>
                    <div class="col-md-12 mb-3">
                        <label for="title" class="form-label"
                            >Titre de livre</label
                        >
                        <input
                            v-model.trim="inputs.title"
                            name="title"
                            id="title"
                            type="text"
                            class="form-control"
                        />
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="author" class="form-label"
                            >Prénom de l'auteur</label
                        >
                        <input
                            v-model.trim="inputs.authors[0].firstName"
                            name="author"
                            id="author"
                            type="text"
                            class="form-control"
                        />
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="author" class="form-label"
                            >Nom de l'auteur</label
                        >
                        <input
                            v-model.trim="inputs.authors[0].lastName"
                            name="author"
                            id="author"
                            type="text"
                            class="form-control"
                        />
                    </div>

                    <div class="col-md-12 mb-3">
                        <label for="edition" class="form-label">Edition</label>
                        <input
                            v-model.trim="inputs.publisher.name"
                            name="edition"
                            id="edition"
                            type="text"
                            class="form-control"
                        />
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="publicationYear" class="form-label"
                            >L'année de publication</label
                        >
                        <input
                            v-model.trim="inputs.publicationYear"
                            name="publicationYear"
                            id="publicationYear"
                            type="text"
                            class="form-control"
                        />
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="pageCount" class="form-label"
                            >Nombre de pages</label
                        >
                        <input
                            v-model.trim="inputs.pageCount"
                            name="pageCount"
                            id="pageCount"
                            type="text"
                            class="form-control"
                        />
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="languageId" class="form-label"
                            >Langue</label
                        >
                        <select
                            v-model.number="inputs.languageIdList"
                            name="languageId"
                            id="languageId"
                            class="form-select"
                            multiple
                        >
                            <option selected disabled value="0">
                                Choisir langue...
                            </option>
                            <LabelValues :items="list_languages" />
                        </select>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="categoryId" class="form-label"
                            >Category</label
                        >
                        <select
                            v-model.number="inputs.categoryId"
                            id="categoryId"
                            class="form-select"
                        >
                            <option selected disabled value="0">
                                Choisir category...
                            </option>
                            <LabelValues :items="list_categories" />
                        </select>
                    </div>

                    <div class="col-md-12 mb-3">
                        <label for="summary" class="form-label"
                            >Description</label
                        >
                        <textarea
                            v-model.trim="inputs.summary"
                            name="summary"
                            id="summary"
                            class="form-control"
                            rows="5"
                        ></textarea>
                    </div>

                    <div class="col-md-12 mb-3">
                        <label for="image" class="form-label"
                            >Ajouter l'image de votre livre</label
                        >
                        <input
                            name="image"
                            id="image"
                            type="file"
                            accept="image/png,image/jpeg"
                            class="form-control"
                            @change="handleImageUpload"
                        />
                    </div>
                </div>
                <div class="d-flex justify-content-center">
                    <button
                        type="submit"
                        class="btn btn-lg btn-primary col-md-4 my-3"
                    >
                        Ajouter
                    </button>
                </div>
            </form>
        </div>
    </main>
</template>

<script setup>
import LabelValues from "../../components/commons/LabelValues.vue";
import { onBeforeMount } from "vue";
import { storeToRefs } from "pinia";
import { AddBookFormStore } from "../../stores/add-book-form-store";
import { BookStore } from "../../stores/book-store";
import { AuthStore } from "../../stores/auth-store";

const addBookStoreObj = AddBookFormStore();
const { list_languages, list_categories } = storeToRefs(addBookStoreObj);
onBeforeMount(() => {
    addBookStoreObj.get_list_languages();
    addBookStoreObj.get_list_categories();
});
const inputs = {
    isbn: null,
    title: null,
    publicationYear: null,
    pageCount: null,
    summary: null,
    publisher: {},
    categoryId: 0,
    userId: 14,
    languageIdList: [],
    authors: [{ firstName: null, lastName: null }],
};
const authStoreObj = AuthStore();
const { token } = authStoreObj;
const bookStore = BookStore();

const add_new_book = async () => {
    console.log(inputs);
    console.log(token);
    const formData = new FormData();
    formData.append("isbn", inputs.isbn);
    formData.append("title", inputs.title);
    formData.append("publicationYear", inputs.publicationYear);
    formData.append("pageCount", inputs.pageCount);
    formData.append("summary", inputs.summary);
    formData.append("publisher", inputs.publisher);
    formData.append("categoryId", inputs.categoryId);
    formData.append("userId", inputs.userId);
    formData.append("languageIdList", inputs.languageIdList);
    formData.append("authors", inputs.authors);
    const resp = await bookStore.add_new_book(formData, token);
    console.log("resp", resp);

    if (resp.status === 204) {
        alert(`Livre a été créer avec success.`);
    } else {
        alert(`Nous n'avons pas pu créer le livre.`);
    }
};
const handleImageUpload = (event) => {
    inputs.image = event.target.files[0];
};
</script>
