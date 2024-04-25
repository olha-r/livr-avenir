<script setup>
import { onMounted, reactive } from 'vue';
import { useUserStore } from '@/stores/user-store';
import { useBookItemStore } from '@/stores/book-item-store';
import { storeToRefs } from 'pinia';
import OfferedBooksSection from '@/components/client/OfferedBooksSection.vue';
import { useI18n } from 'vue-i18n';
const { d } = useI18n();
const { t } = useI18n();
const userStore = useUserStore();
const { user_profile } = storeToRefs(userStore);
const bookItemStore = useBookItemStore();
const { items_by_user } = storeToRefs(bookItemStore);
const showStates = reactive({
	userInfo: false,
	offeredBooks: true,
	orderedBooks: false,
	sentBooks: false,
	pointsNumber: false
});
const toggleSection = (section) => {
	for (const key in showStates) {
		showStates[key] = key === section;
	}
};
const activeClass = (section) => {
	return showStates[section] ? 'active' : 'btn-outline-primary';
};
onMounted(async () => {
	await userStore.get_user_details();
	await bookItemStore.get_items_by_user();
});
</script>

<template>
	<main class="container-xl my-5">
		<h3 class="text-center mb-3">User profile</h3>
		<div class="d-flex justify-content-around mb-4">
			<a
				class="btn btn-outline-primary btn-user-section m-1 p-4"
				:class="activeClass('userInfo')"
				@click="toggleSection('userInfo')"
			>
				{{ t('client.profile.section.userInfo.label') }}
			</a>
			<a
				class="btn btn-outline-primary m-1 p-4"
				:class="activeClass('offeredBooks')"
				@click="toggleSection('offeredBooks')"
			>
				{{ t('client.profile.section.offeredBooks.label') }}
			</a>
			<!-- <a
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
            > -->
			<a
				class="btn btn-outline-primary m-1 p-4"
				:class="activeClass('pointsNumber')"
				@click="toggleSection('pointsNumber')"
			>
				{{ t('client.profile.section.pointsNumber.label') }}</a
			>
		</div>
		<div v-if="showStates.userInfo">
			<h6>{{ t('client.profile.section.userInfo.email') }}</h6>
			<p>{{ user_profile?.email }}</p>
			<h6>{{ t('client.profile.section.userInfo.fullName') }}</h6>
			<p>{{ user_profile?.firstName }} {{ user_profile?.lastName }}</p>
			<h6>{{ t('client.profile.section.userInfo.registrationDate') }}</h6>
			<p>{{ d(user_profile?.registrationDate, 'short', 'fr-FR') }}</p>
		</div>

		<div v-if="showStates.offeredBooks">
			<h6>{{ t('client.profile.section.offeredBooks.title') }}</h6>
			<OfferedBooksSection :offeredBookList="items_by_user" />
			<!-- <pre>{{ items_by_user }}</pre> -->
		</div>

		<!-- <div v-if="showStates.orderedBooks">
            <h6>Livres commandées</h6>
            <p>Pas d'info</p>
        </div>

        <div v-if="showStates.sentBooks">
            <h6>Livres envoyées</h6>
            <p>Pas d'info</p>
        </div> -->

		<div v-if="showStates.pointsNumber">
			<h6>{{ t('client.profile.section.pointsNumber.label') }}</h6>
			<p>{{ user_profile?.pointsNumber }}</p>
		</div>
	</main>
</template>
