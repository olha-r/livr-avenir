<script setup>
import { onMounted, ref, reactive, computed, watch } from "vue";
import { storeToRefs } from "pinia";
import { useRoute } from "vue-router";
import { useBookStore } from "@/stores/book-store";

import { useAuthStore } from "@/stores/auth-store";
import { useBookItemStore } from "@/stores/book-item-store";
import { useI18n } from "vue-i18n";
import { useVuelidate } from "@vuelidate/core";
import LabelValues from "@/components/commons/LabelValues.vue";
import ValidationMessage from "@/components/commons/ValidationMessage.vue";
import { useConditionStore } from "@/stores/condition-store";
import { usePageStore } from "@/stores/page-store";
import BookItem from "@/components/client/BookItem.vue";
import {
    required,
    minLength,
    maxLength,
    numeric,
    helpers,
} from "@vuelidate/validators";

const baseUrl = import.meta.env.VITE_IMG_BASE_URL;
const bookStore = useBookStore();
const bookItemStore = useBookItemStore();
const { book_details } = storeToRefs(bookStore);
const authStore = useAuthStore();
const { token, isLoggedIn } = authStore;
const route = useRoute();
const book_id = route.params.id;
const imageUrl = ref("");
const conditionStore = useConditionStore();
const { list_conditions } = storeToRefs(conditionStore);
const { items_by_book } = storeToRefs(bookItemStore);
onMounted(async () => {
    await bookStore.get_book_details(book_id);
    await conditionStore.get_list_conditions();
    if (isLoggedIn) {
        await bookItemStore.get_items_by_book_id(book_id);
    }
});
const inputs = reactive({
    bookId: book_details?.book?.id,
    description: null,
    pointsPrice: null,
    conditionId: null,
});

const { t } = useI18n();
const requiredMessage = `${t("admin.validationMessages.required")}`;
const rules = computed(() => {
    return {
        pointsPrice: {
            required: helpers.withMessage(requiredMessage, required),
            numeric: helpers.withMessage(
                `${t("admin.validationMessages.numeric")}`,
                numeric
            ),
        },
        description: {
            required: helpers.withMessage(requiredMessage, required),
            minLength: helpers.withMessage(
                `${t("client.validationMessages.minLengthDescription")}`,

                minLength(10)
            ),
            maxLength: helpers.withMessage(
                `${t("client.validationMessages.maxLengthDescription")}`,
                maxLength(150)
            ),
        },
        conditionId: {
            required: helpers.withMessage(requiredMessage, required),
        },
    };
});
const v$ = useVuelidate(rules, inputs);
const pointsPriceOptions = [
    {
        id: 1,
        name: 1,
    },
    {
        id: 2,
        name: 2,
    },
    {
        id: 3,
        name: 3,
    },
    {
        id: 4,
        name: 4,
    },
    {
        id: 5,
        name: 5,
    },
];
const showModal = ref(false);
const openNewBookItemModal = () => {
    showModal.value = true;
    const modal = new bootstrap.Modal(document.getElementById("newBookItem"));
    modal.show();
};
const closeModal = () => {
    showModal.value = false; // Hide the modal
    const modalElement = document.getElementById("newBookItem");
    const modalBackdrop = document.querySelector(".modal-backdrop");
    if (modalBackdrop) {
        modalBackdrop.parentNode.removeChild(modalBackdrop); // Remove the modal backdrop from the DOM
    }
    const modal = new bootstrap.Modal(modalElement);
    modal.hide(); // Hide the Bootstrap modal
};
watch(book_details, (newBookDetails) => {
    if (newBookDetails && newBookDetails.book) {
        inputs.bookId = newBookDetails.book.id;
    }
});
const pageStore = usePageStore();

const add_new_book_item = async () => {
    await v$.value.$validate();
    if (!v$.value.$error) {
        const resp = await bookItemStore.add_new_book_item(inputs, token);
        if (resp.status === 204) {
            pageStore.alert.type = "success";
            pageStore.alert.message = `Votre livre a été ajoutée avec succès.`;
            pageStore.alert.show = true;
            closeModal();
            // Reset form fields
            inputs.description = null;
            inputs.pointsPrice = null;
            inputs.conditionId = null;
            v$.value.$reset();
            // Deactivate modal form
            showModal.value = false;
            await bookItemStore.get_items_by_book_id(book_id);
            setTimeout(() => {
                pageStore.alert.show = false;
            }, 5000);
        } else {
            pageStore.alert.type = "error";
            pageStore.alert.message = `Nous n'avons pas pu ajoutée le livre.`;
            pageStore.alert.show = true;
            setTimeout(() => {
                pageStore.alert.show = false;
            }, 3000);
        }
    }
};
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
                                ) in book_details?.authorList"
                                :key="index"
                            >
                                {{ author?.firstName }} {{ author?.lastName }}
                                <span
                                    v-if="
                                        index <
                                        book_details?.authorList.length - 1
                                    "
                                    >,
                                </span> </span
                            ><span class="text-muted fs-6">
                                {{
                                    t("client.bookDetailPage.bookDetail.author")
                                }}</span
                            >
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
                    <p class="fst-italic">
                        {{ t("client.bookDetailPage.bookDetail.description") }}
                    </p>
                    <p>{{ book_details?.book?.summary }}</p>
                    <div class="row">
                        <p>
                            <span
                                class="badge text-bg-warning px-3 category-badge me-1"
                                >{{ book_details?.book?.category?.name }}</span
                            >
                        </p>
                        <p>
                            {{ t("client.bookDetailPage.bookDetail.info") }}
                        </p>

                        <div class="col">
                            <p>
                                <span class="fw-light"
                                    >{{
                                        t(
                                            "client.bookDetailPage.bookDetail.isbn"
                                        )
                                    }} </span
                                >{{ book_details?.book?.isbn }}
                            </p>
                        </div>
                        <div class="col">
                            <p>
                                <span class="fw-light"
                                    >{{
                                        t(
                                            "client.bookDetailPage.bookDetail.publicationYear"
                                        )
                                    }} </span
                                >{{ book_details?.book?.publicationYear }}
                            </p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <p>
                                <span class="fw-light"
                                    >{{
                                        t(
                                            "client.bookDetailPage.bookDetail.publisher"
                                        )
                                    }} </span
                                >{{ book_details?.book?.publisher?.name }}
                            </p>
                        </div>
                        <div class="col">
                            <p>
                                <span class="fw-light">
                                    {{
                                        t(
                                            "client.bookDetailPage.bookDetail.pageCount"
                                        )
                                    }} </span
                                >{{ book_details?.book?.pageCount }}
                            </p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <p>
                                <span class="fw-light">
                                    {{
                                        t(
                                            "client.bookDetailPage.bookDetail.language"
                                        )
                                    }}
                                </span>
                                {{ book_details?.book?.language?.name }}
                            </p>
                        </div>
                    </div>
                    <button
                        v-if="isLoggedIn"
                        class="btn btn-outline-primary"
                        type="button"
                        id="addNewBookItem"
                        @click="openNewBookItemModal"
                    >
                        <i class="bi bi-plus">
                            {{
                                t("client.bookDetailPage.bookDetail.addItemBtn")
                            }}
                        </i>
                    </button>
                </div>
            </div>
            <span v-if="isLoggedIn">
                <span v-if="items_by_book">
                    <div v-for="(item, index) in items_by_book" :key="index">
                        <BookItem :item="item" />
                    </div>
                </span>

                <div v-else>
                    <div
                        class="row book-owner pt-3 mt-5 d-flex align-items-center"
                    >
                        <div class="col-12">
                            <p class="text-center">
                                {{
                                    t(
                                        "client.bookDetailPage.bookItemDetail.noItems"
                                    )
                                }}
                            </p>
                        </div>
                    </div>
                </div>
            </span>
        </div>
    </main>

    <!-- MODAL for add new item book -->

    <div
        class="modal modal-lg fade"
        id="newBookItem"
        tabindex="-1"
        :class="{ show: showModal }"
    >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="bookModalLabel">
                        {{ t("client.newItemForm.titleCreate") }}
                    </h5>
                    <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                    ></button>
                </div>
                <div class="modal-body">
                    <!-- Add new book item form -->
                    <form novalidate @submit.prevent="add_new_book_item">
                        <div class="row">
                            <div class="col-md-12 mb-3">
                                <label
                                    for="description"
                                    class="form-label required"
                                    >{{
                                        t(
                                            "client.newItemForm.description.label"
                                        )
                                    }}
                                </label>
                                <textarea
                                    v-model.trim="inputs.description"
                                    name="description"
                                    id="description"
                                    class="form-control"
                                    rows="5"
                                ></textarea>
                                <ValidationMessage :model="v$.description" />
                            </div>

                            <div class="col-md-6 mb-3">
                                <label
                                    for="conditionId"
                                    class="form-label required"
                                    >{{
                                        t("client.newItemForm.condition.label")
                                    }}
                                </label>
                                <select
                                    v-model.number="inputs.conditionId"
                                    id="conditionId"
                                    class="form-select"
                                >
                                    <option selected disabled value="0">
                                        {{
                                            t(
                                                "client.newItemForm.condition.option"
                                            )
                                        }}
                                    </option>
                                    <LabelValues :items="list_conditions" />
                                </select>
                                <ValidationMessage :model="v$.conditionId" />
                            </div>
                            <div class="col-md-6 mb-3">
                                <label
                                    for="pointsPrice"
                                    class="form-label required"
                                    >{{
                                        t(
                                            "client.newItemForm.pointsPrice.label"
                                        )
                                    }}
                                </label>
                                <select
                                    v-model.number="inputs.pointsPrice"
                                    id="pointsPrice"
                                    class="form-select"
                                >
                                    <option selected disabled value="0">
                                        {{
                                            t(
                                                "client.newItemForm.pointsPrice.option"
                                            )
                                        }}
                                    </option>
                                    <LabelValues :items="pointsPriceOptions" />
                                </select>
                                <ValidationMessage :model="v$.pointsPrice" />
                            </div>
                        </div>
                        <div class="d-flex justify-content-center">
                            <button
                                type="submit"
                                class="btn btn-lg btn-primary col-md-4 col-12 my-3"
                            >
                                {{ t("client.newItemForm.buttonCreate") }}
                            </button>
                        </div>
                    </form>

                    <!-- End form -->
                </div>
            </div>
        </div>
    </div>
    <!-- END MODAL for add new item book -->
</template>