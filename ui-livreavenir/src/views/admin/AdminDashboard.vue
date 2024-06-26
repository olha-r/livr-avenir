<script setup>
import { onMounted, ref } from 'vue';
import { storeToRefs } from 'pinia';
import { useI18n } from 'vue-i18n';
import { useBookStore } from '@/stores/book-store';
import { usePageStore } from '@/stores/page-store';

const { t } = useI18n();
const bookStore = useBookStore();
const { bookListForAdmin } = storeToRefs(bookStore);
onMounted(() => {
	bookStore.get_book_list_admin();
});
const baseUrl = import.meta.env.VITE_IMG_BASE_URL;
const pageStore = usePageStore();
const remove = async (id) => {
	const resp = await bookStore.delete_book(id);
	if (resp.status === 204) {
		pageStore.alert.type = 'success';
		pageStore.alert.message = `${t(
			'admin.dashboard.deleteBook.successMessage'
		)}`;
		pageStore.alert.show = true;
		await bookStore.get_book_list_admin();
		setTimeout(() => {
			pageStore.alert.show = false;
		}, 6000);
	} else {
		pageStore.alert.type = 'error';
		pageStore.alert.message = `${t('admin.dashboard.deleteBook.errorMessage')}`;
		pageStore.alert.show = true;
		setTimeout(() => {
			pageStore.alert.show = false;
		}, 6000);
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
		<div class="table-responsive">
			<table class="table table-hover align-middle">
				<caption>
					Manage books
				</caption>
				<thead>
					<tr>
						<th id="header-index">#</th>
						<th id="header-isbn">{{ t('admin.dashboard.table.isbn') }}</th>
						<th id="header-title">
							{{ t('admin.dashboard.table.bookTitle') }}
						</th>
						<th id="header-author">{{ t('admin.dashboard.table.author') }}</th>
						<th id="header-publisher">
							{{ t('admin.dashboard.table.publisher') }}
						</th>
						<th id="header-category">
							{{ t('admin.dashboard.table.category') }}
						</th>
						<th id="header-image">{{ t('admin.dashboard.table.image') }}</th>
						<th id="header-actions" colspan="2">
							{{ t('admin.dashboard.table.actions') }}
						</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(item, index) in bookListForAdmin" :key="index">
						<td
							headers="header-index"
							@click="openBookModal(item.book, item.listAuthor)"
						>
							{{ index + 1 }}
						</td>
						<td
							headers="header-isbn"
							@click="openBookModal(item.book, item.listAuthor)"
						>
							{{ item.book.isbn }}
						</td>
						<td
							headers="header-title"
							@click="openBookModal(item.book, item.listAuthor)"
						>
							{{ item.book.title }}
						</td>
						<td
							headers="header-author"
							@click="openBookModal(item.book, item.listAuthor)"
						>
							<span
								v-for="(author, authorIndex) in item.listAuthor"
								:key="authorIndex"
							>
								{{ author.firstName }} {{ author.lastName }}
								<span v-if="authorIndex < item.listAuthor.length - 1"
									>,<br
								/></span>
							</span>
						</td>
						<td
							headers="header-publisher"
							@click="openBookModal(item.book, item.listAuthor)"
						>
							{{ item.book.publisher.name }}
						</td>
						<td
							headers="header-category"
							@click="openBookModal(item.book, item.listAuthor)"
						>
							<span class="badge text-bg-warning px-3 category-badge">{{
								item.book.category.name
							}}</span>
						</td>
						<td
							headers="header-image"
							@click="openBookModal(item.book, item.listAuthor)"
						>
							<img
								:src="
									item.book.coverImageUrl
										? baseUrl + item.book.coverImageUrl
										: baseUrl + 'default-image.jpg'
								"
								alt="Cover Image"
								class="book-image-admin-dashboard"
							/>
						</td>
						<td headers="header-actions">
							<RouterLink
								:to="{ name: 'update-book', params: { id: item.book.id } }"
								title="Update book"
							>
								<i class="bi bi-pencil-square fs-4 me-2"></i>
							</RouterLink>
						</td>
						<td headers="header-actions">
							<a href="#" @click="remove(item.book.id)" class="mx-3">
								<i class="bi bi-trash3 text-danger fs-4"></i>
							</a>
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
