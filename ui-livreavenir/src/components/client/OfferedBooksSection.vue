<script setup>
import { ref } from 'vue';
import { useBookItemStore } from '@/stores/book-item-store';
import { usePageStore } from '@/stores/page-store';
import UpdateItemForm from '@/components/client/UpdateItemForm.vue';
import { useI18n } from 'vue-i18n';
const { d } = useI18n();
const { t } = useI18n();
const props = defineProps(['offeredBookList']);
const baseUrl = import.meta.env.VITE_IMG_BASE_URL;
const pageStore = usePageStore();
const bookItemStore = useBookItemStore();
const remove = async (id) => {
	const resp = await bookItemStore.delete_item(id);
	if (resp.status === 204) {
		pageStore.alert.type = 'success';
		pageStore.alert.message = `${t(
			'client.profile.section.offeredBooks.successMessage'
		)}`;
		pageStore.alert.show = true;
		await bookItemStore.get_items_by_user();
		setTimeout(() => {
			pageStore.alert.show = false;
		}, 6000);
	} else {
		pageStore.alert.type = 'error';
		pageStore.alert.message = `${t(
			'client.profile.section.offeredBooks.errorMessage'
		)}`;
		pageStore.alert.show = true;
		setTimeout(() => {
			pageStore.alert.show = false;
		}, 6000);
	}
};
const editMode = ref(false);
const editingItemId = ref(null);
const setEditMode = (id) => {
	editingItemId.value = id;
	editMode.value = true;
};
const exitEditMode = () => {
	editMode.value = false;
	editingItemId.value = null;
};
</script>
<template>
	<div class="row mb-4">
		<div class="col-md-6" v-for="(item, index) in offeredBookList" :key="index">
			<div class="card mb-3">
				<div class="row g-0">
					<div class="col-md-8">
						<div class="card-body">
							<h5 class="card-title">{{ item?.book?.title }}</h5>
							<p class="card-text text-muted mb-1">
								{{ t('client.bookDetailPage.bookDetail.isbn') }}
								{{ item?.book?.isbn }}
							</p>
							<div v-if="editMode && editingItemId === item.id">
								<UpdateItemForm :item="item" :exitEditMode="exitEditMode" />
							</div>
							<div v-else>
								<p class="card-text mb-1">
									{{ t('client.bookDetailPage.bookItemDetail.pointsPrice') }}
									{{ item?.pointsPrice }}
								</p>
								<p class="card-text mb-1">
									{{ t('client.bookDetailPage.bookItemDetail.condition') }}
									{{ item?.condition?.name }}
								</p>
								<p class="card-text mb-1">
									{{ t('client.bookDetailPage.bookItemDetail.date') }}
									{{ d(item?.addedAt, 'short', 'fr-FR') }}
								</p>
								<p class="card-text mb-1">
									{{ t('client.bookDetailPage.bookItemDetail.description') }}
								</p>
								<p class="card-text mb-1">
									{{ item?.description }}
								</p>
								<div class="text-center">
									<button
										class="btn btn-sm btn-outline-primary me-2"
										@click="setEditMode(item?.id)"
									>
										<i class="bi bi-pencil-fill me-1"></i>
										{{ t('client.bookDetailPage.bookItemDetail.btnModify') }}
									</button>
									<button
										class="btn btn-sm btn-outline-danger"
										@click="remove(item?.id)"
									>
										<i class="bi bi-trash-fill me-1"></i>
										{{ t('client.bookDetailPage.bookItemDetail.btnDelete') }}
									</button>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4 d-none d-md-block">
						<img
							:src="
								item?.book?.coverImageUrl
									? baseUrl + item?.book?.coverImageUrl
									: baseUrl + 'default-image.jpg'
							"
							:alt="`Couverture de  ${item?.book?.title}`"
							class="img-fluid img-book-cover offered-book"
						/>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>
