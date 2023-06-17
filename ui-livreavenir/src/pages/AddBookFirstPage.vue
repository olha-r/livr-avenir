<template>
    <main class="container-xl my-5">
        <div
            class="justify-content-center align-items-center book-details-container"
        >
            <div class="text-center">
                <h1>Ajouter un livre (1/2)</h1>
            </div>
            <form @submit.prevent="add_new_book">
                <div class="row">
                    <div class="col-md-8 mb-3">
                        <label for="name" class="form-label"
                            >Titre de livre</label
                        >
                        <input
                            v-model.trim="inputs.name"
                            name="name"
                            id="name"
                            type="text"
                            class="form-control"
                        />
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="isbn" class="form-label">ISBN</label>
                        <input
                            v-model.trim="inputs.isbn"
                            name="isbn"
                            id="isbn"
                            type="text"
                            class="form-control"
                        />
                    </div>

                    <div class="col-md-8 mb-3">
                        <label for="author" class="form-label"
                            >Auteur de livre</label
                        >
                        <input
                            v-model.trim="inputs.author"
                            name="author"
                            id="author"
                            type="text"
                            class="form-control"
                        />
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="year" class="form-label"
                            >L'année de publication</label
                        >
                        <input
                            v-model.trim="inputs.year"
                            name="year"
                            id="year"
                            type="text"
                            class="form-control"
                        />
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="year" class="form-label">Edition</label>
                        <input
                            v-model.trim="inputs.edition"
                            name="edition"
                            id="edition"
                            type="text"
                            class="form-control"
                        />
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="formatId" class="form-label">Format</label>
                        <select
                            v-model.number="inputs.formatId"
                            name="formatId"
                            id="formatId"
                            class="form-select"
                        >
                            <option selected disabled value="0">
                                Choisir format...
                            </option>
                            <LabelValues :items="list_formats" />
                        </select>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="languageId" class="form-label"
                            >Langue</label
                        >
                        <select
                            v-model.number="inputs.languageId"
                            name="languageId"
                            id="languageId"
                            class="form-select"
                        >
                            <option selected disabled value="0">
                                Choisir langue...
                            </option>
                            <LabelValues :items="list_languages" />
                        </select>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="description" class="form-label"
                                >Description</label
                            >
                            <textarea
                                v-model.trim="inputs.description"
                                name="description"
                                id="description"
                                class="form-control"
                                rows="5"
                            ></textarea>
                        </div>
                        <div class="col-md-6">
                            <label for="conditionId" class="form-label"
                                >Etat du livre</label
                            >
                            <select
                                v-model.number="inputs.conditionId"
                                id="conditionId"
                                class="form-select"
                            >
                                <option selected disabled value="0">
                                    Choisir état...
                                </option>
                                <LabelValues :items="list_conditions" />
                            </select>

                            <label for="point" class="form-label pt-3"
                                >Estimation en points</label
                            >
                            <select
                                v-model.number="inputs.point"
                                class="form-select"
                                id="point"
                                required
                            >
                                <option selected value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                        </div>
                    </div>

                    <div class="row my-3">
                        <div class="col-md-6">
                            <p>Categories:</p>
                            <div class="row">
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
                                <!-- <div v-for="(category, index) in listCategories" :key="index">
                                <input class="form-check-input" type="checkbox" :id="category.id" :value="category.id" v-model="inputs.categoryId">
                                <label class="form-check-label ps-2" :for="category.id">{{ category.name }}</label>
                            </div> -->
                            </div>
                        </div>

                        <div class="col-md-6 mt-3">
                            <div class="mb-3">
                                <div class="mb-3">
                                    <label for="image" class="form-label"
                                        >Ajouter l'image de votre livre</label
                                    >
                                    <!-- <input name="image" id="image" type="file" class="form-control"> -->
                                    <input
                                        v-model.trim="inputs.image"
                                        name="image"
                                        id="image"
                                        type="text"
                                        class="form-control"
                                    />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="d-flex justify-content-center">
                    <button
                        type="submit"
                        class="btn btn-lg btn-primary col-md-4 my-3"
                    >
                        Continuer
                    </button>
                </div>
            </form>
        </div>
    </main>
</template>

<script>
import LabelValues from "../components/commons/LabelValues.vue";
import { ref, onBeforeMount } from "vue";
import { storeToRefs } from "pinia";
import { AddBookFormStore } from "../stores/add-book-form-store";
import { BookStore } from "../stores/book-store";
import { AuthStore } from "../stores/auth-store";

export default {
    name: "AddBook",
    components: {
        LabelValues,
    },
    setup() {
        const addBookStoreObj = AddBookFormStore();
        const {
            list_formats,
            list_languages,
            list_conditions,
            list_categories,
        } = storeToRefs(addBookStoreObj);
        onBeforeMount(() => {
            addBookStoreObj.get_list_formats();
            addBookStoreObj.get_list_languages();
            addBookStoreObj.get_list_conditions();
            addBookStoreObj.get_list_categories();
        });
        const inputs = {
            name: null,
            isbn: null,
            author: null,
            year: null,
            year: null,
            formatId: 0,
            languageId: 0,
            description: null,
            conditionId: 0,
            point: null,
            categoryId: 0,
            image: null,
        };
        const authStoreObj = AuthStore();
        const { token } = authStoreObj;
        return {
            list_formats,
            list_languages,
            list_conditions,
            list_categories,
            inputs,
            token,
        };
    },
    methods: {
        async add_new_book() {
            const book_store = BookStore();
            const new_book = this.inputs;
            console.log("new_book", new_book);
            console.log("token", this.token);
            const resp = await book_store.add_new_book(new_book, this.token);
            console.log("resp", resp);

            if (resp.status === 204) {
                alert(
                    `L'annonce pour le livre ${new_book.name} a été créer avec success.`
                );
            } else {
                alert(
                    `Nous n'avons pas pu créer l'nnonce pour le livre ${new_book.name}.`
                );
            }
        },
    },
};
</script>
