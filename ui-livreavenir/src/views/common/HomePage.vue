<script setup>
import { onBeforeMount } from 'vue';
import { storeToRefs } from 'pinia';
import { useI18n } from 'vue-i18n';
import { useBookStore } from '@/stores/book-store';

const { t } = useI18n();
const bookStore = useBookStore();
const { lastAddedBooks } = storeToRefs(bookStore);
onBeforeMount(() => {
	bookStore.get_last_added_books();
});
const baseUrl = import.meta.env.VITE_IMG_BASE_URL;
</script>

<template>
	<main class="container-xl my-2 home-page">
		<div class="mb-4 rounded-3 home-page-img position-relative">
			<div
				class="position-absolute top-50 start-50 translate-middle-x text-center"
			>
				<h1>{{ t('homepage.mainTitle') }}</h1>
			</div>
		</div>

		<h3 class="text-center my-4">{{ t('homepage.title') }}</h3>

		<div
			class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4 justify-content-center align-items-center"
		>
			<div
				class="col d-flex justify-content-center"
				v-for="(item, index) in lastAddedBooks"
				:key="index"
			>
				<router-link
					:to="{ name: 'book-details', params: { id: item.book.id } }"
					class="d-flex"
				>
					<div class="card mx-4 d-flex flex-column">
						<div class="card-img-container">
							<img
								:src="
									item.book.coverImageUrl
										? baseUrl + item.book.coverImageUrl
										: baseUrl + 'default-image.jpg'
								"
								class="card-img-bottom"
								alt="Cover Image"
							/>
						</div>
						<div class="card-body d-flex flex-column justify-content-between">
							<h6 class="card-title text-center">
								{{ item.book.title }}
							</h6>
							<div class="card-subtitle mb-2 text-muted text-center">
								<p
									class="p-0 m-0"
									v-for="(author, authorIndex) in item.listAuthor"
									:key="authorIndex"
								>
									{{ author.firstName }} {{ author.lastName }}
									<span v-if="authorIndex < item.listAuthor.length - 1"
										>,
									</span>
								</p>
							</div>
						</div>
					</div>
				</router-link>
			</div>
		</div>
	</main>
</template>
