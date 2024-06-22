<script setup>
import { onMounted, ref, toRef, watch } from 'vue';
import Multiselect from '@vueform/multiselect';
import { storeToRefs } from 'pinia';
import { useAuthorStore } from '@/stores/author-store';

const props = defineProps(['authorList', 'refresh']);

const authorStore = useAuthorStore();
const { author_list } = storeToRefs(authorStore);
const options = ref([]);
const emit = defineEmits(['updateAuthorList']);
const authorIdList = ref([]);
const authorList = toRef(props, 'authorList');
watch(authorList, (value) => {
	authorIdList.value = authorList.value;
});
const getOptions = () => {
	const author_options = author_list.value;
	for (const author of author_options) {
		const option = {};
		option['value'] = author.id;
		option['label'] = `${author.firstName} ${author.lastName}`;
		options.value.push(option);
	}
};
onMounted(async () => {
	refreshAuthorOptions();
});
const updateAuthorList = (value) => {
	emit('updateAuthorList', value);
};
const refreshAuthorOptions = async () => {
	await authorStore.get_author_list();
	getOptions();
};
watch(
	() => props.refresh,
	(newVal, oldVal) => {
		if (newVal !== oldVal) {
			refreshAuthorOptions();
		}
	}
);
</script>

<template>
	<div>
		<Multiselect
			v-model.trim="authorIdList"
			:model-value="props.authorList"
			mode="tags"
			:options="options"
			:searchable="true"
			@input="updateAuthorList"
		/>
	</div>
</template>
