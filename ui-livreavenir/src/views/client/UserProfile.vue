<script setup>
import { onMounted, reactive } from "vue";
import { useUserStore } from "@/stores/user-store";
import { storeToRefs } from "pinia";
const userStore = useUserStore();
const { user_profile } = storeToRefs(userStore);
const showStates = reactive({
    userInfo: true,
    offeredBooks: false,
    orderedBooks: false,
    sentBooks: false,
    pointsNumber: false,
});
const toggleSection = (section) => {
    for (const key in showStates) {
        showStates[key] = key === section;
    }
};
const activeClass = (section) => {
    return showStates[section] ? "active" : "btn-outline-primary";
};
onMounted(async () => {
    await userStore.get_user_details();
});
</script>

<template>
    <main class="container-xl my-5">
        <h3 class="text-center mb-3">User profile</h3>
        <div class="d-flex justify-content-between">
            <a
                class="btn btn-outline-primary m-1 p-4"
                :class="activeClass('userInfo')"
                @click="toggleSection('userInfo')"
                >User info</a
            >
            <a
                class="btn btn-outline-primary m-1 p-4"
                :class="activeClass('offeredBooks')"
                @click="toggleSection('offeredBooks')"
                >Livres proposées</a
            >
            <a
                class="btn btn-outline-primary m-1 p-4"
                :class="activeClass('orderedBooks')"
                @click="toggleSection('orderedBooks')"
                >Livres commandées</a
            >
            <a
                class="btn btn-outline-primary m-1 p-4"
                :class="activeClass('sentBooks')"
                @click="toggleSection('sentBooks')"
                >Livres envoyées</a
            >
            <a
                class="btn btn-outline-primary m-1 p-4"
                :class="activeClass('pointsNumber')"
                @click="toggleSection('pointsNumber')"
                >Points number</a
            >
        </div>
        <div v-if="showStates.userInfo">
            <h6>Email</h6>
            <p>{{ user_profile?.email }}</p>
            <h6>Full name</h6>
            <p>{{ user_profile?.firstName }} {{ user_profile?.lastName }}</p>
            <h6>Inscrit depuis:</h6>
            <p>{{ user_profile?.registrationDate }}</p>
        </div>

        <div v-if="showStates.offeredBooks">
            <h6>Listes des livre à echanger</h6>
            <p>Lires</p>
        </div>

        <div v-if="showStates.orderedBooks">
            <h6>Livres commandées</h6>
            <p>Pas d'info</p>
        </div>

        <div v-if="showStates.sentBooks">
            <h6>Livres envoyées</h6>
            <p>Pas d'info</p>
        </div>

        <div v-if="showStates.pointsNumber">
            <h6>Points number</h6>
            <p>{{ user_profile?.pointsNumber }}</p>
        </div>
    </main>
</template>
