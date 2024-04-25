<script setup>
import { onMounted, reactive, computed, ref } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { storeToRefs } from 'pinia';
import { useI18n } from 'vue-i18n';
import { useVuelidate } from '@vuelidate/core';
import {
	required,
	minLength,
	maxLength,
	numeric,
	helpers
} from '@vuelidate/validators';
import { useBookStore } from '@/stores/book-store';
import { usePageStore } from '@/stores/page-store';
import { usePublisherStore } from '@/stores/publisher-store';
import { useLanguageStore } from '@/stores/language-store';
import { useCategoryStore } from '@/stores/category-store';
import { useAuthStore } from '@/stores/auth-store';
import LabelValues from '../../components/commons/LabelValues.vue';
import ValidationMessage from '../../components/commons/ValidationMessage.vue';
import SearchMultiSelect from '../../components/commons/SearchMultiSelect.vue';

const { t } = useI18n();
const route = useRoute();
const bookId = ref(route.params.id);
const router = useRouter();
const inputs = reactive({
	isbn: null,
	title: null,
	publicationYear: null,
	pageCount: null,
	summary: null,
	publisher: null,
	categoryId: null,
	languageId: null,
	authorList: [],
	coverImageUrl: null
});
const isIsbnValid = (value) => {
	const len = value.length;
	return len === 10 || len === 13;
};
const requiredMessage = `${t('admin.validationMessages.required')}`;
const rules = computed(() => {
	return {
		isbn: {
			required: helpers.withMessage(requiredMessage, required),
			isISBNValid: helpers.withMessage(
				`${t('admin.validationMessages.isISBNValid')}`,
				(value) => isIsbnValid(value)
			),
			numeric: helpers.withMessage(
				`${t('admin.validationMessages.numeric')}`,
				numeric
			)
		},
		title: {
			required: helpers.withMessage(requiredMessage, required),
			minLength: helpers.withMessage(
				`${t('admin.validationMessages.minLengthTitle')}`,
				minLength(2)
			),
			maxLength: helpers.withMessage(
				`${t('admin.validationMessages.maxLengthTitle')}`,
				maxLength(200)
			)
		},
		publicationYear: {
			required: helpers.withMessage(requiredMessage, required),
			numeric: helpers.withMessage(
				`${t('admin.validationMessages.numeric')}`,
				numeric
			),
			minLength: helpers.withMessage(
				`${t('admin.validationMessages.minMaxLengthPublicationYear')}`,
				minLength(4)
			),
			maxLength: helpers.withMessage(
				`${t('admin.validationMessages.minMaxLengthPublicationYear')}`,
				maxLength(4)
			)
		},
		pageCount: {
			required: helpers.withMessage(requiredMessage, required),
			numeric: helpers.withMessage(
				`${t('admin.validationMessages.numeric')}`,
				numeric
			),
			maxLength: helpers.withMessage(
				`${t('admin.validationMessages.maxLengthPageCount')}`,
				maxLength(5)
			)
		},
		summary: {
			required: helpers.withMessage(requiredMessage, required),
			minLength: helpers.withMessage(
				`${t('admin.validationMessages.minLengthSummary')}`,
				minLength(10)
			),
			maxLength: helpers.withMessage(
				`${t('admin.validationMessages.maxLengthSummary')}`,
				maxLength(1000)
			)
		},
		publisher: {
			required: helpers.withMessage(requiredMessage, required)
		},
		categoryId: {
			required: helpers.withMessage(requiredMessage, required)
		},
		languageId: {
			required: helpers.withMessage(requiredMessage, required)
		},
		authorList: [
			{
				required: helpers.withMessage(requiredMessage, required)
			}
		],
		coverImageUrl: {
			maxValue: (coverImageUrl) => {
				return coverImageUrl ? coverImageUrl.size <= 1048576 : true;
			}
		}
	};
});
const image = ref('');
const v$ = useVuelidate(rules, inputs);
const languageStore = useLanguageStore();
const categoryStore = useCategoryStore();
const publisherStore = usePublisherStore();
const bookStore = useBookStore();
const pageStore = usePageStore();
const { list_languages } = storeToRefs(languageStore);
const { list_categories } = storeToRefs(categoryStore);
const { publisher_list } = storeToRefs(publisherStore);
const { book_for_update } = storeToRefs(bookStore);
onMounted(async () => {
	// Fetch book details based on the bookId
	await bookStore.get_book_for_update(bookId.value);
	// Set the form inputs with the fetched data
	const bookDetails = bookStore.book_for_update;
	inputs.isbn = bookDetails.book.isbn || null;
	inputs.title = bookDetails.book.title || null;
	inputs.publicationYear = bookDetails.book.publicationYear || null;
	inputs.pageCount = bookDetails.book.pageCount || null;
	inputs.summary = bookDetails.book.summary || null;
	inputs.publisher = bookDetails.book.publisher.id || null;
	inputs.categoryId = bookDetails.book.category.id || null;
	inputs.languageId = bookDetails.book.language.id || null;
	//inputs.coverImageUrl = bookDetails.book.coverImageUrl || null;
	image.value = bookDetails.book.coverImageUrl || null;
	const idArray = bookDetails.listAuthor.map((author) => author.id);
	inputs.authorList = idArray || [];
	// Fetch other necessary data
	languageStore.get_list_languages();
	categoryStore.get_list_categories();
	publisherStore.get_publisher_list();
});
const authStore = useAuthStore();
const { token } = authStore;
const update_book = async () => {
	await v$.value.$validate();
	if (!v$.value.$error) {
		const formData = new FormData();
		formData.append('isbn', inputs.isbn);
		formData.append('title', inputs.title);
		formData.append('publicationYear', inputs.publicationYear);
		formData.append('pageCount', inputs.pageCount);
		formData.append('summary', inputs.summary);
		formData.append('publisher', inputs.publisher);
		formData.append('categoryId', inputs.categoryId);
		formData.append('languageId', inputs.languageId);
		formData.append('authorList', inputs.authorList);
		if (inputs.coverImageUrl != null) {
			formData.append('coverImageUrl', inputs.coverImageUrl);
		}
		const resp = await bookStore.update_book(bookId.value, formData, token);

		if (resp.status === 204) {
			pageStore.alert.type = 'success';
			pageStore.alert.message = `Livre a été mis à jour avec succès.`;
			pageStore.alert.show = true;
			router.push('/admin');
			setTimeout(() => {
				pageStore.alert.show = false;
			}, 5000);
		} else {
			pageStore.alert.type = 'error';
			pageStore.alert.message = `Nous n'avons pas pu mettre à jour le livre.`;
			pageStore.alert.show = true;
			setTimeout(() => {
				pageStore.alert.show = false;
			}, 5000);
		}
	}
};
const newImage = ref(false);
const handleImageUpload = (event) => {
	newImage.value = true;
	inputs.coverImageUrl = event.target.files[0];
};
const baseUrl = import.meta.env.VITE_IMG_BASE_URL;
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
				<h3>
					{{ t('admin.bookForm.titleUpdate') }}
				</h3>
			</div>
			<form novalidate @submit.prevent="update_book">
				<div class="row">
					<div class="col-md-12 mb-3">
						<label for="isbn" class="form-label required">{{
							t('admin.bookForm.isbn')
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
							t('admin.bookForm.bookTitle')
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
							t('admin.bookForm.author.label')
						}}</label>
						<SearchMultiSelect
							:authorList="inputs.authorList"
							@updateAuthorList="updateAuthorList"
						/>
						<ValidationMessage :model="v$.authorList" />
					</div>
					<div class="col-md-12 mb-3">
						<!-- <PublisherSearch></PublisherSearch> -->
						<div class="col-md-12 mb-3">
							<label for="edition" class="form-label required">{{
								t('admin.bookForm.publisher.label')
							}}</label>
							<select
								v-model.number="inputs.publisher"
								id="publisher"
								class="form-select"
							>
								<option selected disabled value="0">
									{{ t('admin.bookForm.publisher.option') }}
								</option>
								<LabelValues :items="publisher_list" />
							</select>
							<ValidationMessage :model="v$.publisher" />
						</div>
					</div>
					<div class="col-md-6 mb-3">
						<label for="publicationYear" class="form-label required">{{
							t('admin.bookForm.publicationYear')
						}}</label>
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
							t('admin.bookForm.pageCount')
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
							t('admin.bookForm.language.label')
						}}</label>
						<select
							v-model.number="inputs.languageId"
							id="languageId"
							class="form-select"
						>
							<option selected disabled value="0">
								{{ t('admin.bookForm.language.option') }}
							</option>
							<LabelValues :items="list_languages" />
						</select>
						<ValidationMessage :model="v$.languageId" />
					</div>
					<div class="col-md-6 mb-3">
						<label for="categoryId" class="form-label required">{{
							t('admin.bookForm.category.label')
						}}</label>
						<select
							v-model.number="inputs.categoryId"
							id="categoryId"
							class="form-select"
						>
							<option selected disabled value="0">
								{{ t('admin.bookForm.category.option') }}
							</option>
							<LabelValues :items="list_categories" />
						</select>
						<ValidationMessage :model="v$.categoryId" />
					</div>
					<div class="col-md-12 mb-3">
						<label for="summary" class="form-label required">{{
							t('admin.bookForm.summary')
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
							t('admin.bookForm.imageUpdate')
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
						<br />
						<div>
							<img
								v-if="image && !newImage"
								:src="baseUrl + image"
								alt="Selected Image"
								class="image-update"
							/>
						</div>
					</div>
				</div>
				<div class="d-flex justify-content-center">
					<button
						type="submit"
						class="btn btn-lg btn-primary col-md-4 col-12 my-3"
					>
						{{ t('admin.bookForm.buttonUpdate') }}
					</button>
				</div>
			</form>
		</div>
	</main>
</template>
