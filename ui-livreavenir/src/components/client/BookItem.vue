<script setup>
import { defineProps } from "vue";

const props = defineProps(["item"]);
</script>
<template>
    <div class="row book-owner pt-3 mt-5 d-flex align-items-center">
        <div class="col-12 col-md-2">
            <p class="text-center">
                {{ item?.user?.firstName }} {{ item?.user?.lastName }}
            </p>
        </div>
        <div class="col-12 col-md-3">
            <p class="text-center">{{ item?.condition?.name }}</p>
            <p class="text-center">Ajouté le {{ item?.addedAt }}</p>
        </div>
        <div class="col-12 col-md-2">
            <p class="text-center">Points</p>
            <p class="text-center">{{ item?.pointsPrice }}</p>
        </div>
        <div class="col-12 col-md-3">
            <p class="text-center">{{ item?.description }}</p>
        </div>
        <div class="col-12 col-md-2 d-flex flex-column justify-content-center">
            <p class="text-center m-0">Emprenter</p>
            <button class="btn" href="#">
                <i class="bi bi-handbag"></i>
            </button>
        </div>
    </div>
</template>