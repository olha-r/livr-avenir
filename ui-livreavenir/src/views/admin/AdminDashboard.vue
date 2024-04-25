<script setup>
import { onMounted, ref } from 'vue';
import { storeToRefs } from 'pinia';
import { useI18n } from 'vue-i18n';
import { useBookStore } from '@/stores/book-store';
import { useAuthStore } from '@/stores/auth-store';
import { usePageStore } from '@/stores/page-store';
import SearchComponent from '@/components/commons/SearchComponent.vue';

const { t } = useI18n();
const bookStore = useBookStore();
const { bookListForAdmin } = storeToRefs(bookStore);
onMounted(() => {
	bookStore.get_book_list_admin();
});
const authStore = useAuthStore();
const { token } = authStore;
const baseUrl = import.meta.env.VITE_IMG_BASE_URL;
const pageStore = usePageStore();
const remove = async (id) => {
	const resp = await bookStore.delete_book(id, token);
	if (resp.status === 204) {
		bookStore.get_book_list_admin();
		pageStore.alert.type = 'success';
		pageStore.alert.message = `${t(
			'admin.dashboard.deleteBook.successMessage'
		)}`;
		pageStore.alert.show = true;
		setTimeout(() => {
			pageStore.alert.show = false;
		}, 6000); // Redirect after 3 seconds
	} else {
		pageStore.alert.type = 'error';
		pageStore.alert.message = `${t('admin.dashboard.deleteBook.errorMessage')}`;
		pageStore.alert.show = true;
		console.error(`Nous n'avons pas pu supprimer le livre.`);
		setTimeout(() => {
			pageStore.alert.show = false;
		}, 6000); // Redirect after 3 seconds
	}
};
let selectedBook = ref(null);
let selectedBookAuthorList = ref(null);
const openBookModal = (book, listAuthor) => {
	selectedBook.value = book;
	selectedBookAuthorList.value = listAuthor;
	const modal = new bootstrap.Modal(document.getElementById('bookModal'));
	modal.show();
};
</script>

<template>
	<main class="container-xl my-2 home-page">
		<h3 class="text-center my-4">
			{{ t('admin.dashboard.title') }}
		</h3>
		<div class="container text-end">
			<RouterLink class="nav-link" :to="{ name: 'add-book' }">
				<button type="button" class="btn btn-primary m-3">
					{{ t('admin.dashboard.addBookBtn') }}
				</button></RouterLink
			>
		</div>
		<div class="row row-cols-2 row-cols-md-4 g-5 table-responsive-lg">
			<table class="table table-hover align-middle">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">
							{{ t('admin.dashboard.table.isbn') }}
						</th>
						<th scope="col">
							{{ t('admin.dashboard.table.bookTitle') }}
						</th>
						<th scope="col">
							{{ t('admin.dashboard.table.author') }}
						</th>
						<th scope="col">
							{{ t('admin.dashboard.table.publisher') }}
						</th>
						<th scope="col">
							{{ t('admin.dashboard.table.category') }}
						</th>
						<th scope="col">
							{{ t('admin.dashboard.table.image') }}
						</th>
						<th scope="col" colspan="2">
							{{ t('admin.dashboard.table.actions') }}
						</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(item, index) in bookListForAdmin" :key="index">
						<th scope="row" @click="openBookModal(item.book, item.listAuthor)">
							{{ index + 1 }}
						</th>
						<td @click="openBookModal(item.book, item.listAuthor)">
							{{ item.book.isbn }}
						</td>
						<td @click="openBookModal(item.book, item.listAuthor)">
							{{ item.book.title }}
						</td>
						<td @click="openBookModal(item.book, item.listAuthor)">
							<span
								v-for="(author, authorIndex) in item.listAuthor"
								:key="authorIndex"
							>
								{{ author.firstName }}
								{{ author.lastName }}
								<span v-if="authorIndex < item.listAuthor.length - 1"
									>,
									<br />
								</span>
							</span>
						</td>
						<td @click="openBookModal(item.book, item.listAuthor)">
							{{ item.book.publisher.name }}
						</td>
						<td @click="openBookModal(item.book, item.listAuthor)">
							<span class="badge text-bg-warning px-3 category-badge">{{
								item.book.category.name
							}}</span>
						</td>
						<td @click="openBookModal(item.book, item.listAuthor)">
							<img
								:src="
									item.book.coverImageUrl
										? baseUrl + item.book.coverImageUrl
										: baseUrl + 'default-image.jpg'
								"
								alt="Cover Image"
								style="width: auto; height: 120px"
							/>
						</td>
						<td>
							<RouterLink
								:to="{
									name: 'update-book',
									params: { id: item.book.id }
								}"
								title="Update book"
							>
								<i class="bi bi-pencil-square fs-4 me-2"></i>
							</RouterLink>
						</td>
						<td>
							<a href="#" @click="remove(item.book.id)" class="mx-3">
								<i class="bi bi-trash3 text-danger fs-4"></i
							></a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="modal modal-lg fade" id="bookModal" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content" v-if="selectedBook">
					<div class="modal-header">
						<h5 class="modal-title" id="bookModalLabel">
							{{ selectedBook.title }}
						</h5>
						<button
							type="button"
							class="btn-close"
							data-bs-dismiss="modal"
						></button>
					</div>
					<div class="modal-body">
						<p>
							<strong>{{ t('admin.dashboard.bookInfo.author') }}</strong>

							<span
								v-for="(author, authorIndex) in selectedBookAuthorList"
								:key="authorIndex"
							>
								{{ author.firstName }}
								{{ author.lastName }}
								<span v-if="authorIndex < selectedBookAuthorList.length - 1"
									>,
								</span>
							</span>
						</p>
						<p>
							<strong>{{ t('admin.dashboard.bookInfo.isbn') }}</strong>
							{{ selectedBook.isbn }}
						</p>
						<p>
							<strong>{{ t('admin.dashboard.bookInfo.publisher') }}</strong>
							{{ selectedBook.publisher.name }}
						</p>
						<p>
							<strong>{{
								t('admin.dashboard.bookInfo.publicationYear')
							}}</strong>
							{{ selectedBook.publicationYear }}
						</p>
						<p>
							<strong>{{ t('admin.dashboard.bookInfo.pageCount') }}</strong>
							{{ selectedBook.pageCount }}
						</p>
						<p>
							<strong>{{ t('admin.dashboard.bookInfo.summary') }}</strong>
							{{ selectedBook.summary }}
						</p>
						<p>
							<strong>{{ t('admin.dashboard.bookInfo.language') }}</strong>
							{{ selectedBook.language.name }}
						</p>
						<p>
							<strong>{{ t('admin.dashboard.bookInfo.category') }}</strong>
							{{ selectedBook.category.name }}
						</p>
					</div>
					<div class="modal-footer">
						<button
							type="button"
							class="btn btn-secondary"
							data-bs-dismiss="modal"
						>
							{{ t('admin.dashboard.bookInfo.closeModal') }}
						</button>
					</div>
				</div>
			</div>
		</div>
	</main>
</template>
