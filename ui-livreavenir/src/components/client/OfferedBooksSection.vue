<script setup>
import { defineProps } from "vue";

const props = defineProps(["offeredBookList"]);
const baseUrl = import.meta.env.VITE_IMG_BASE_URL;
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
                        {{ item?.book?.isbn }}
                    </div>
                    <p class="card-text mb-auto">
                        {{ item?.book?.coverImageUrl }}
                    </p>
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

