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
                            v-model.trim="formData.isbn"
                            name="isbn"
                            id="isbn"
                            type="text"
                            class="form-control"
                        />
                        <ValidationMessage :model="v$.isbn" />
                    </div>
                    <div class="col-md-12 mb-3">
                        <label for="title" class="form-label"
                            >Titre de livre</label
                        >
                        <input
                            v-model.trim="formData.title"
                            name="title"
                            id="title"
                            type="text"
                            class="form-control"
                        />
                        <ValidationMessage :model="v$.title" />
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="author" class="form-label"
                            >Prénom de l'auteur</label
                        >
                        <input
                            v-model.trim="formData.authors[0].firstName"
                            name="author"
                            id="author"
                            type="text"
                            class="form-control"
                        />
                        <ValidationMessage :model="v$.authors[0].firstName" />
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="author" class="form-label"
                            >Nom de l'auteur</label
                        >
                        <input
                            v-model.trim="formData.authors[0].lastName"
                            name="author"
                            id="author"
                            type="text"
                            class="form-control"
                        />
                        <ValidationMessage :model="v$.authors[0].lastName" />
                    </div>

                    <div class="col-md-12 mb-3">
                        <label for="edition" class="form-label">Edition</label>
                        <input
                            v-model.trim="formData.publisher.name"
                            name="edition"
                            id="edition"
                            type="text"
                            class="form-control"
                        />
                        <ValidationMessage :model="v$.publisher.name" />
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="publicationYear" class="form-label"
                            >L'année de publication</label
                        >
                        <input
                            v-model.trim="formData.publicationYear"
                            name="publicationYear"
                            id="publicationYear"
                            type="text"
                            class="form-control"
                        />
                        <ValidationMessage :model="v$.publicationYear" />
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="pageCount" class="form-label"
                            >Nombre de pages</label
                        >
                        <input
                            v-model.trim="formData.pageCount"
                            name="pageCount"
                            id="pageCount"
                            type="text"
                            class="form-control"
                        />
                        <ValidationMessage :model="v$.pageCount" />
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="languageId" class="form-label"
                            >Langue</label
                        >
                        <select
                            v-model.number="formData.languageIdList"
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
                        <ValidationMessage :model="v$.languageIdList" />
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="categoryId" class="form-label"
                            >Category</label
                        >
                        <select
                            v-model.number="formData.categoryId"
                            id="categoryId"
                            class="form-select"
                        >
                            <option selected disabled value="0">
                                Choisir category...
                            </option>
                            <LabelValues :items="list_categories" />
                        </select>
                        <ValidationMessage :model="v$.categoryId" />
                    </div>

                    <div class="col-md-12 mb-3">
                        <label for="summary" class="form-label"
                            >Description</label
                        >
                        <textarea
                            v-model.trim="formData.summary"
                            name="summary"
                            id="summary"
                            class="form-control"
                            rows="5"
                        ></textarea>
                        <ValidationMessage :model="v$.summary" />
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
import { onBeforeMount, reactive, computed } from "vue";
import { storeToRefs } from "pinia";
import { AddBookFormStore } from "../../stores/add-book-form-store";
import { BookStore } from "../../stores/book-store";
import { AuthStore } from "../../stores/auth-store";
import useValidate, { useVuelidate } from "@vuelidate/core";
import {
    required,
    minLength,
    maxLength,
    numeric,
    helpers,
} from "@vuelidate/validators";
import ValidationMessage from "../../components/commons/ValidationMessage.vue";

const formData = reactive({
    isbn: null,
    title: null,
    publicationYear: null,
    pageCount: null,
    summary: null,
    publisher: {},
    categoryId: null,
    userId: 14,
    languageIdList: [],
    authors: [{ firstName: null, lastName: null }],
});
const rules = computed(() => {
    return {
        isbn: {
            required: helpers.withMessage(
                "Veuillez renseigner ce champ.",
                required
            ),
            minLength: helpers.withMessage(
                "Veuillez saisir au moins 10 caractères.",
                minLength(10)
            ),
            maxLength: helpers.withMessage(
                "Veuillez saisir moins 10 ou 13 caractères.",
                maxLength(13)
            ),
            numeric: helpers.withMessage(
                "Veuillez saisir seulement des nombres.",
                numeric
            ),
        },
        title: {
            required: helpers.withMessage(
                "Veuillez renseigner ce champ.",
                required
            ),
            minLength: helpers.withMessage(
                "Veuillez saisir au moins 2 caractères.",
                minLength(2)
            ),
            maxLength: helpers.withMessage(
                "Veuillez saisir moins de 200 caractères.",
                maxLength(200)
            ),
        },
        publicationYear: {
            required: helpers.withMessage(
                "Veuillez renseigner ce champ.",
                required
            ),
        },
        pageCount: {
            required: helpers.withMessage(
                "Veuillez renseigner ce champ.",
                required
            ),
            numeric: helpers.withMessage(
                "Veuillez saisir seulement des nombres.",
                numeric
            ),
        },
        summary: {
            required: helpers.withMessage(
                "Veuillez renseigner ce champ.",
                required
            ),
            minLength: helpers.withMessage(
                "Veuillez saisir au moins 2 caractères.",
                minLength(2)
            ),
            maxLength: helpers.withMessage(
                "Veuillez saisir moins de 1000 caractères.",
                maxLength(1000)
            ),
        },
        publisher: {
            name: {
                required: helpers.withMessage(
                    "Veuillez renseigner ce champ.",
                    required
                ),
                minLength: helpers.withMessage(
                    "Veuillez saisir au moins 2 caractères.",
                    minLength(2)
                ),
                maxLength: helpers.withMessage(
                    "Veuillez saisir moins de 300 caractères.",
                    maxLength(300)
                ),
            },
        },
        categoryId: {
            required: helpers.withMessage(
                "Veuillez renseigner ce champ.",
                required
            ),
        },
        userId: {
            required: helpers.withMessage(
                "Veuillez renseigner ce champ.",
                required
            ),
        },
        languageIdList: {
            required: helpers.withMessage(
                "Veuillez renseigner ce champ.",
                required
            ),
        },
        authors: [
            {
                firstName: {
                    required: helpers.withMessage(
                        "Veuillez renseigner ce champ.",
                        required
                    ),
                },
                lastName: { required },
            },
        ],
    };
});

const v$ = useVuelidate(rules, formData);

const addBookStoreObj = AddBookFormStore();
const { list_languages, list_categories } = storeToRefs(addBookStoreObj);
onBeforeMount(() => {
    addBookStoreObj.get_list_languages();
    addBookStoreObj.get_list_categories();
});

const authStoreObj = AuthStore();
const { token } = authStoreObj;
const bookStore = BookStore();

const add_new_book = async () => {
    const result = await v$.value.$validate();

    if (!v$.value.$error) {
        console.log("No errors");
        // console.log(inputs);
        // console.log(token);
        // const formData = new FormData();
        // formData.append("isbn", inputs.isbn);
        // formData.append("title", inputs.title);
        // formData.append("publicationYear", inputs.publicationYear);
        // formData.append("pageCount", inputs.pageCount);
        // formData.append("summary", inputs.summary);
        // formData.append("publisher", inputs.publisher);
        // formData.append("categoryId", inputs.categoryId);
        // formData.append("userId", inputs.userId);
        // formData.append("languageIdList", inputs.languageIdList);
        // formData.append("authors", inputs.authors);
        // const resp = await bookStore.add_new_book(formData, token);
        // console.log("resp", resp);

        // if (resp.status === 204) {
        //     alert(`Livre a été créer avec success.`);
        // } else {
        //     alert(`Nous n'avons pas pu créer le livre.`);
        // }
    } else {
        console.log("There are errors");
    }
};
const handleImageUpload = (event) => {
    formData.image = event.target.files[0];
};
</script>
