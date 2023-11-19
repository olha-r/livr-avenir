<script setup>
import { useRouter, useRoute } from "vue-router";
import LabelValues from "../../components/commons/LabelValues.vue";
import AuthorLabelValue from "../../components/commons/AuthorLabelValue.vue";
import { onMounted, reactive, computed, ref } from "vue";
import { storeToRefs } from "pinia";
import { AddBookFormStore } from "../../stores/add-book-form-store";
import { BookStore } from "../../stores/book-store";
import { AuthorStore } from "../../stores/author-store";
import { usePageStore } from "../../stores/page-store";
import { publisherStore } from "../../stores/publisher-store";
import { useVuelidate } from "@vuelidate/core";
import { AuthStore } from "../../stores/auth-store";
import {
    required,
    requiredIf,
    minLength,
    maxLength,
    numeric,
    helpers,
} from "@vuelidate/validators";
import ValidationMessage from "../../components/commons/ValidationMessage.vue";

const requiredMessage = "Veuillez renseigner ce champ.";

const route = useRoute();
const bookId = ref(route.params.id);
const router = useRouter();
const inputs = reactive({
    isbn: null,
    title: null,
    publicationYear: null,
    pageCount: null,
    summary: null,
    publisher: null,
    categoryId: null,
    languageId: null,
    authorList: [],
    coverImageUrl: undefined,
});

const rules = computed(() => {
    return {
        isbn: {
            required: helpers.withMessage(requiredMessage, required),
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
            required: helpers.withMessage(requiredMessage, required),
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
            required: helpers.withMessage(requiredMessage, required),
            numeric: helpers.withMessage(
                "Veuillez saisir seulement des nombres.",
                numeric
            ),
            minLength: helpers.withMessage(
                "Veuillez saisir 4 caractères.",
                minLength(4)
            ),
            maxLength: helpers.withMessage(
                "Veuillez saisir 4 caractères.",
                maxLength(4)
            ),
        },
        pageCount: {
            required: helpers.withMessage(requiredMessage, required),
            numeric: helpers.withMessage(
                "Veuillez saisir seulement des nombres.",
                numeric
            ),
            maxLength: helpers.withMessage(
                "Veuillez saisir moins de 5 caractères.",
                maxLength(5)
            ),
        },
        summary: {
            required: helpers.withMessage(requiredMessage, required),
            minLength: helpers.withMessage(
                "Veuillez saisir au moins 10 caractères.",
                minLength(10)
            ),
            maxLength: helpers.withMessage(
                "Veuillez saisir moins de 1000 caractères.",
                maxLength(1000)
            ),
        },
        publisher: {
            required: helpers.withMessage(requiredMessage, required),
        },
        categoryId: {
            required: helpers.withMessage(requiredMessage, required),
        },
        languageId: {
            required: helpers.withMessage(requiredMessage, required),
        },
        authorList: [
            {
                required: helpers.withMessage(requiredMessage, required),
            },
        ],
        coverImageUrl: {
            required: helpers.withMessage(
                "Veuillez ajouter une image.",
                requiredIf(() => {
                    return inputs.coverImageUrl === undefined;
                })
            ),
            maxValue: (coverImageUrl) => {
                return coverImageUrl ? coverImageUrl.size <= 1048576 : true;
            },
        },
    };
});

const v$ = useVuelidate(rules, inputs);
const addBookStoreObj = AddBookFormStore();
const { list_languages, list_categories } = storeToRefs(addBookStoreObj);
const publisherStoreObj = publisherStore();
const { publisher_list } = storeToRefs(publisherStoreObj);
const authorStoreObj = AuthorStore();
const { author_list } = storeToRefs(authorStoreObj);
const bookStore = BookStore();
const pageStore = usePageStore();
const { book_for_update } = storeToRefs(bookStore);
onMounted(async () => {
    // Fetch book details based on the bookId
    await bookStore.get_book_for_update(bookId.value);
    // Set the form inputs with the fetched data
    const bookDetails = bookStore.book_for_update;
    inputs.isbn = bookDetails.isbn || null;
    inputs.title = bookDetails.title || null;
    inputs.publicationYear = bookDetails.publicationYear || null;
    inputs.pageCount = bookDetails.pageCount || null;
    inputs.summary = bookDetails.summary || null;
    inputs.publisher = bookDetails.publisher.id || null;
    inputs.categoryId = bookDetails.category.id || null;
    inputs.languageId = bookDetails.language.id || null;
    inputs.coverImageUrl = bookDetails.coverImageUrl || null;
    inputs.authorList = bookDetails.authorList || [];
    // Fetch other necessary data
    addBookStoreObj.get_list_languages();
    addBookStoreObj.get_list_categories();
    publisherStoreObj.get_publisher_list();
    authorStoreObj.get_author_list();
});
const authStoreObj = AuthStore();
const { token } = authStoreObj;
const update_book = async () => {
    await v$.value.$validate();
    if (!v$.value.$error) {
        // Similar to your add_new_book function, but now use bookStore.update_book instead
        const formData = new FormData();
        formData.append("isbn", inputs.isbn);
        formData.append("title", inputs.title);
        formData.append("publicationYear", inputs.publicationYear);
        formData.append("pageCount", inputs.pageCount);
        formData.append("summary", inputs.summary);
        formData.append("publisher", inputs.publisher);
        formData.append("categoryId", inputs.categoryId);
        formData.append("languageId", inputs.languageId);
        formData.append("authorList", inputs.authorList);
        formData.append("coverImageUrl", inputs.coverImageUrl);

        const resp = await bookStore.update_book(bookId.value, formData, token);

        if (resp.status === 204) {
            // Handle success
            pageStore.alert.type = "success";
            pageStore.alert.message = `Livre a été mis à jour avec succès.`;
            pageStore.alert.show = true;
            router.push("/admin");
            setTimeout(() => {
                pageStore.alert.show = false;
            }, 5000);
        } else {
            // Handle error
            pageStore.alert.type = "error";
            pageStore.alert.message = `Nous n'avons pas pu mettre à jour le livre.`;
            pageStore.alert.show = true;
            setTimeout(() => {
                pageStore.alert.show = false;
            }, 5000);
        }
    } else {
        console.log("There are errors");
    }
};

const newImage = ref(false);
const handleImageUpload = (event) => {
    newImage.value = true;
    inputs.coverImageUrl = event.target.files[0];
};
const baseUrl = import.meta.env.VITE_IMG_BASE_URL;
</script>

<template>
    <main class="container-xl my-5">
        {{ inputs }}
        <div
            class="justify-content-center align-items-center book-details-container"
        >
            <div class="text-center">
                <h1>Modifier un livre</h1>
            </div>
            <form novalidate @submit.prevent="update_book">
                <div class="row">
                    <div class="col-md-12 mb-3">
                        <label for="isbn" class="form-label required"
                            >ISBN</label
                        >
                        <input
                            v-model.trim="inputs.isbn"
                            name="isbn"
                            id="isbn"
                            type="text"
                            class="form-control"
                        />
                        <ValidationMessage :model="v$.isbn" />
                    </div>
                    <div class="col-md-12 mb-3">
                        <label for="title" class="form-label required"
                            >Titre de livre</label
                        >
                        <input
                            v-model.trim="inputs.title"
                            name="title"
                            id="title"
                            type="text"
                            class="form-control"
                        />
                        <ValidationMessage :model="v$.title" />
                    </div>

                    <div class="col-md-12 mb-3">
                        <!-- <AuthorSearch></AuthorSearch> -->
                        <!-- <button class="btn btn-large">
                    Ajouter nouveau auteur
                </button> -->

                        <label for="authorId" class="form-label required"
                            >Auteur</label
                        >
                        <!-- <multiselect
                    v-model.number="inputs.authorList"
                    :options="testOptions"
                >
                    /></multiselect
                > -->
                        <select
                            v-model.number="inputs.authorList"
                            name="authorId"
                            id="authorId"
                            class="form-select"
                            size="4"
                            multiple
                        >
                            <AuthorLabelValue :items="author_list" />
                        </select>
                        <ValidationMessage :model="v$.authorList" />
                    </div>
                    <div class="col-md-12 mb-3">
                        <!-- <PublisherSearch></PublisherSearch> -->
                        <div class="col-md-12 mb-3">
                            <label for="edition" class="form-label required"
                                >Edition</label
                            >
                            <select
                                v-model.number="inputs.publisher"
                                id="publisher"
                                class="form-select"
                            >
                                <option selected disabled value="0">
                                    Choisir publisher...
                                </option>
                                <LabelValues :items="publisher_list" />
                            </select>
                            <ValidationMessage :model="v$.publisher" />
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="publicationYear" class="form-label required"
                            >L'année de publication</label
                        >
                        <input
                            v-model.trim="inputs.publicationYear"
                            name="publicationYear"
                            id="publicationYear"
                            type="text"
                            class="form-control"
                        />
                        <ValidationMessage :model="v$.publicationYear" />
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="pageCount" class="form-label required"
                            >Nombre de pages</label
                        >
                        <input
                            v-model.trim="inputs.pageCount"
                            name="pageCount"
                            id="pageCount"
                            type="text"
                            class="form-control"
                        />
                        <ValidationMessage :model="v$.pageCount" />
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="languageId" class="form-label required"
                            >Langue</label
                        >
                        <select
                            v-model.number="inputs.languageId"
                            id="languageId"
                            class="form-select"
                        >
                            <option selected disabled value="0">
                                Choisir langue...
                            </option>
                            <LabelValues :items="list_languages" />
                        </select>
                        <ValidationMessage :model="v$.languageId" />
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="categoryId" class="form-label required"
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
                        <ValidationMessage :model="v$.categoryId" />
                    </div>
                    <div class="col-md-12 mb-3">
                        <label for="summary" class="form-label required"
                            >Description</label
                        >
                        <textarea
                            v-model.trim="inputs.summary"
                            name="summary"
                            id="summary"
                            class="form-control"
                            rows="5"
                        ></textarea>
                        <ValidationMessage :model="v$.summary" />
                    </div>
                    <div class="col-md-12 mb-3">
                        <label for="image" class="form-label"
                            >Nouvelle image</label
                        >
                        <input
                            name="image"
                            id="image"
                            type="file"
                            accept="image/png,image/jpeg"
                            class="form-control"
                            @change="handleImageUpload"
                        />
                        <ValidationMessage :model="v$.coverImageUrl" />
                        <div v-if="inputs.coverImageUrl && !newImage">
                            <img
                                :src="baseUrl + inputs.coverImageUrl"
                                alt="Selected Image"
                                style="max-width: 100%; height: auto"
                            />
                        </div>
                    </div>
                </div>
                <div class="d-flex justify-content-center">
                    <button
                        type="submit"
                        class="btn btn-lg btn-primary col-md-4 col-12 my-3"
                    >
                        Modifier
                    </button>
                </div>
            </form>
        </div>
    </main>
</template>
