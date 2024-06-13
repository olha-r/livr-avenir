<script setup>
import { onMounted, reactive, computed } from 'vue';
import { useRouter } from 'vue-router';
import { storeToRefs } from 'pinia';
import { useI18n } from 'vue-i18n';
import { useVuelidate } from '@vuelidate/core';
import {
	required,
	requiredIf,
	minLength,
	maxLength,
	numeric,
	helpers
} from '@vuelidate/validators';
import { useBookStore } from '@/stores/book-store';
import { useAuthStore } from '@/stores/auth-store';
import { usePageStore } from '@/stores/page-store';
import { usePublisherStore } from '@/stores/publisher-store';
import { useLanguageStore } from '@/stores/language-store';
import { useCategoryStore } from '@/stores/category-store';
import LabelValues from '@/components/commons/LabelValues.vue';
import SearchMultiSelect from '@/components/commons/SearchMultiSelect.vue';
import ValidationMessage from '@/components/commons/ValidationMessage.vue';

const { t } = useI18n();
const requiredMessage = `${t('admin.validationMessages.required')}`;
const isIsbnValid = (value) => {
	const len = value.length;
	return len === 10 || len === 13;
};
const inputs = reactive({
	isbn: null,
	title: null,
	publicationYear: null,
	pageCount: null,
	summary: null,
	publisher: null,
	categoryId: null,
	languageId: [],
	authorList: [],
	coverImageUrl: undefined
});
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
			required: helpers.withMessage(
				`${t('admin.validationMessages.coverImageUrl')}`,
				requiredIf(() => {
					return inputs.coverImageUrl === undefined;
				})
			),
			maxValue: (coverImageUrl) => {
				return coverImageUrl ? coverImageUrl.size <= 1048576 : true;
			}
		}
	};
});
// const v$ = useVuelidate(rules, inputs);
const languageStore = useLanguageStore();
const categoryStore = useCategoryStore();
const publisherStore = usePublisherStore();
const { publisher_list } = storeToRefs(publisherStore);
const { list_languages } = storeToRefs(languageStore);
const { list_categories } = storeToRefs(categoryStore);
onMounted(async () => {
	await languageStore.get_list_languages();
	await categoryStore.get_list_categories();
	await publisherStore.get_publisher_list();
});
const authStore = useAuthStore();
const { token } = authStore;
const bookStore = useBookStore();
const router = useRouter();
const pageStore = usePageStore();

const add_new_book = async () => {
	// await v$.value.$validate();
	// if (!v$.value.$error) {
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
	formData.append('coverImageUrl', inputs.coverImageUrl);
	const resp = await bookStore.add_new_book(formData, token);

	if (resp.status === 204) {
		pageStore.alert.type = 'success';
		pageStore.alert.message = `${t('admin.bookForm.successMessage')}`;
		pageStore.alert.show = true;
		router.push('/admin');
		setTimeout(() => {
			pageStore.alert.show = false;
		}, 5000);
	} else {
		pageStore.alert.type = 'error';
		pageStore.alert.message = `${t('admin.bookForm.errorMessage')}`;
		pageStore.alert.show = true;
		console.error(`Nous n'avons pas pu créer le livre.`);
		setTimeout(() => {
			pageStore.alert.show = false;
		}, 3000);
	}
};
// };
const handleImageUpload = (event) => {
	inputs.coverImageUrl = event.target.files[0];
};
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
				<h3>{{ t('admin.bookForm.titleCreate') }}</h3>
			</div>
			<form novalidate @submit.prevent="add_new_book">
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
						<!-- <ValidationMessage :model="v$.isbn" /> -->
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
						<!-- <ValidationMessage :model="v$.title" /> -->
					</div>

					<div class="col-md-12 mb-3">
						<label for="authorId" class="form-label required">{{
							t('admin.bookForm.author.label')
						}}</label>
						<SearchMultiSelect
							:authorList="inputs.authorList"
							@updateAuthorList="updateAuthorList"
						/>
						<!-- <ValidationMessage :model="v$.authorList" /> -->
					</div>
					<div class="col-md-12 mb-3">
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
							<!-- <ValidationMessage :model="v$.publisher" /> -->
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
						<!-- <ValidationMessage :model="v$.publicationYear" /> -->
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
						<!-- <ValidationMessage :model="v$.pageCount" /> -->
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
						<!-- <ValidationMessage :model="v$.languageId" /> -->
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
						<!-- <ValidationMessage :model="v$.categoryId" /> -->
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
						<!-- <ValidationMessage :model="v$.summary" /> -->
					</div>
					<div class="col-md-12 mb-3">
						<label for="image" class="form-label required">{{
							t('admin.bookForm.imageCreate')
						}}</label>
						<input
							name="image"
							id="image"
							type="file"
							accept="image/png,image/jpeg"
							class="form-control"
							@change="handleImageUpload"
						/>
						<!-- <ValidationMessage :model="v$.coverImageUrl" /> -->
					</div>
				</div>
				<div class="d-flex justify-content-center">
					<button
						type="submit"
						class="btn btn-lg btn-primary col-md-4 col-12 my-3"
					>
						{{ t('admin.bookForm.buttonCreate') }}
					</button>
				</div>
			</form>
		</div>
	</main>
</template>
