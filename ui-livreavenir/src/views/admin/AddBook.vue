<script setup>
import SearchMultiSelect from "../../components/commons/SearchMultiSelect.vue";
import { useRouter } from "vue-router";
import LabelValues from "../../components/commons/LabelValues.vue";
import AuthorLabelValue from "../../components/commons/AuthorLabelValue.vue";
import { onMounted, reactive, computed } from "vue";
import { storeToRefs } from "pinia";
import { AddBookFormStore } from "../../stores/add-book-form-store";
import { BookStore } from "../../stores/book-store";
import { useAuthStore } from "../../stores/auth-store";
import { AuthorStore } from "../../stores/author-store";
import { usePageStore } from "../../stores/page-store";
import { publisherStore } from "../../stores/publisher-store";
import { useVuelidate } from "@vuelidate/core";
import {
    required,
    requiredIf,
    minLength,
    maxLength,
    numeric,
    helpers,
} from "@vuelidate/validators";
import ValidationMessage from "../../components/commons/ValidationMessage.vue";
import { useI18n } from "vue-i18n";
const { t } = useI18n();
const requiredMessage = "Veuillez renseigner ce champ.";

const inputs = reactive({
    isbn: null,
    title: null,
    publicationYear: null,
    pageCount: null,
    summary: null,
    publisher: null,
    categoryId: null,
    languageId: [],
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
                "Veuillez saisir 10 ou 13 caractères.",
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
onMounted(() => {
    console.log("Add book page token", token);
    addBookStoreObj.get_list_languages();
    addBookStoreObj.get_list_categories();
    publisherStoreObj.get_publisher_list();
    authorStoreObj.get_author_list();
});

const authStoreObj = useAuthStore();
const { token } = authStoreObj;
const bookStore = BookStore();
const router = useRouter();
const pageStore = usePageStore();

const add_new_book = async () => {
    await v$.value.$validate();
    if (!v$.value.$error) {
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
        console.log("Form data", inputs);
        const resp = await bookStore.add_new_book(formData, token);
        console.log("resp", resp);

        if (resp.status === 204) {
            pageStore.alert.type = "success";
            pageStore.alert.message = `Livre a été créé avec succès.`;
            pageStore.alert.show = true;
            console.log(`Livre a été créé avec succès.`);
            setTimeout(() => {
                router.push("/admin");
                pageStore.alert.show = false;
            }, 5000); // Redirect after 3 seconds
        } else {
            pageStore.alert.type = "error";
            pageStore.alert.message = `Nous n'avons pas pu créer le livre.`;
            pageStore.alert.show = true;
            console.error(`Nous n'avons pas pu créer le livre.`);
            setTimeout(() => {
                pageStore.alert.show = false;
            }, 5000);
        }
    } else {
        console.log("There are errors");
    }
};
const handleImageUpload = (event) => {
    inputs.coverImageUrl = event.target.files[0];
};
const updateAuthorList = (value) => {
    inputs.authorList = value;
};
</script>
<template>
    <main class="container-xl my-5">
        <div
            class="justify-content-center align-items-center book-details-container"
        >
            <div class="text-center">
                <h3>{{ t("admin.bookForm.titleCreate") }}</h3>
            </div>
            <form novalidate @submit.prevent="add_new_book">
                <div class="row">
                    <div class="col-md-12 mb-3">
                        <label for="isbn" class="form-label required">{{
                            t("admin.bookForm.isbn")
                        }}</label>
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
                        <label for="title" class="form-label required">{{
                            t("admin.bookForm.bookTitle")
                        }}</label>
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
                        <label for="authorId" class="form-label required">{{
                            t("admin.bookForm.author.label")
                        }}</label>
                        <SearchMultiSelect
                            :authorList="inputs.authorList"
                            @updateAuthorList="updateAuthorList"
                        />
                        <ValidationMessage :model="v$.authorList" />
                    </div>
                    <div class="col-md-12 mb-3">
                        <div class="col-md-12 mb-3">
                            <label for="edition" class="form-label required">{{
                                t("admin.bookForm.publisher.label")
                            }}</label>
                            <select
                                v-model.number="inputs.publisher"
                                id="publisher"
                                class="form-select"
                            >
                                <option selected disabled value="0">
                                    {{ t("admin.bookForm.publisher.option") }}
                                </option>
                                <LabelValues :items="publisher_list" />
                            </select>
                            <ValidationMessage :model="v$.publisher" />
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label
                            for="publicationYear"
                            class="form-label required"
                            >{{ t("admin.bookForm.publicationYear") }}</label
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
                        <label for="pageCount" class="form-label required">{{
                            t("admin.bookForm.pageCount")
                        }}</label>
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
                        <label for="languageId" class="form-label required">{{
                            t("admin.bookForm.language.label")
                        }}</label>
                        <select
                            v-model.number="inputs.languageId"
                            id="languageId"
                            class="form-select"
                        >
                            <option selected disabled value="0">
                                {{ t("admin.bookForm.language.option") }}
                            </option>
                            <LabelValues :items="list_languages" />
                        </select>
                        <ValidationMessage :model="v$.languageId" />
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="categoryId" class="form-label required">{{
                            t("admin.bookForm.category.label")
                        }}</label>
                        <select
                            v-model.number="inputs.categoryId"
                            id="categoryId"
                            class="form-select"
                        >
                            <option selected disabled value="0">
                                {{ t("admin.bookForm.category.option") }}
                            </option>
                            <LabelValues :items="list_categories" />
                        </select>
                        <ValidationMessage :model="v$.categoryId" />
                    </div>
                    <div class="col-md-12 mb-3">
                        <label for="summary" class="form-label required">{{
                            t("admin.bookForm.summary")
                        }}</label>
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
                        <label for="image" class="form-label">{{
                            t("admin.bookForm.imageCreate")
                        }}</label>
                        <input
                            name="image"
                            id="image"
                            type="file"
                            accept="image/png,image/jpeg"
                            class="form-control"
                            @change="handleImageUpload"
                        />
                        <ValidationMessage :model="v$.coverImageUrl" />
                    </div>
                </div>
                <div class="d-flex justify-content-center">
                    <button
                        type="submit"
                        class="btn btn-lg btn-primary col-md-4 col-12 my-3"
                    >
                        {{ t("admin.bookForm.buttonCreate") }}
                    </button>
                </div>
            </form>
        </div>
    </main>
</template>