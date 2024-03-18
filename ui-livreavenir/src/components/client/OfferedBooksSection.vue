<script setup>
import { defineProps } from "vue";
import { useBookItemStore } from "@/stores/book-item-store";
import { useAuthStore } from "@/stores/auth-store";
import { usePageStore } from "@/stores/page-store";
const props = defineProps(["offeredBookList"]);
const baseUrl = import.meta.env.VITE_IMG_BASE_URL;
const authStore = useAuthStore();
const pageStore = usePageStore();
const { token } = authStore;
const bookItemStore = useBookItemStore();
const remove = async (id) => {
    const resp = await bookItemStore.delete_item(id, token);
    if (resp.status === 204) {
        pageStore.alert.type = "success";
        pageStore.alert.message = `Votre exemplaire a été supprimé avec success.`;
        pageStore.alert.show = true;
        console.log(`Votre exemplaire a été supprimé avec success.`);
        setTimeout(() => {
            pageStore.alert.show = false;
        }, 6000); // Redirect after 3 seconds
    } else {
        pageStore.alert.type = "error";
        pageStore.alert.message = `Nous n'avons pas pu supprimer votre exemplaire.`;
        pageStore.alert.show = true;
        console.error(`Nous n'avons pas pu supprimer votre exemplaire.`);
        setTimeout(() => {
            pageStore.alert.show = false;
        }, 6000); // Redirect after 3 seconds
    }
};
</script>
<template>
    <div class="row mb-2">
        <div
            class="col-md-6"
            v-for="(item, index) in offeredBookList"
            :key="index"
        >
            <div
                class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250"
            >
                <div class="col p-4">
                    <h4 class="mb-0">{{ item?.book?.title }}</h4>
                    <div class="mb-1 text-body-secondary">
                        ISBN: {{ item?.book?.isbn }}
                    </div>
                    <p class="card-text mb-auto">
                        {{ item?.pointsPrice }} points
                    </p>
                    <p class="card-text mb-auto">
                        Condition: {{ item?.condition?.name }}
                    </p>
                    <p class="card-text mb-auto">
                        Description: {{ item?.description }}
                    </p>
                    <p class="card-text mb-auto">
                        Ajouter le: {{ item?.addedAt }}
                    </p>
                    <button type="button" class="btn btn-primary m-3">
                        Modifier
                    </button>

                    <a
                        class="btn btn-outline-danger m-3"
                        href="#"
                        @click="remove(item?.book?.id)"
                        >Supprimer</a
                    >
                </div>
                <div class="col-auto d-none d-lg-block">
                    <img
                        :src="
                            item?.book?.coverImageUrl
                                ? baseUrl + item?.book?.coverImageUrl
                                : baseUrl + 'default-image.jpg'
                        "
                        class="img-thumbnail img-book-user"
                    />
                </div>
            </div>
        </div>
    </div>
</template>

