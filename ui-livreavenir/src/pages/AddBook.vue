<script>
import LabelValues from '../components/commons/LabelValues.vue';
export default {
    name: 'AddBook',
    components: {
        LabelValues
    },
    data() {
        return {
            formats: [],
            languages: [],
            conditions: [],
            categories: [],
            inputs: {
                name: null,
                isbn: null,
                author: null,
                year: null,
                edition: null,
                image: null,
                description: null,
                point: null,
                conditionId: 0,
                languageId: 0,
                formatId: 0,
                categoryId: 0
            }
        }
    },
    methods: {
        async submit() {
            const postData = this.inputs
            console.log(postData);
            const resp = await fetch('http://localhost:8080/books', {
                method: 'POST',
                headers: {
                    'method': 'Post',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(
                    postData)
                   
            })
            console.log(resp);
        },
        async initConditions() {
            const resp = await this.$http.get('/conditions');
            this.conditions = resp.body;
        },
        async initLanguages() {
            const resp = await this.$http.get('/languages');
            this.languages = resp.body;
        },
        async initFormats() {
            const resp = await this.$http.get('/formats');
            this.formats = resp.body;
        },
        async initCategories() {
            const resp = await this.$http.get('/categories');
            this.categories = resp.body;
        },
    },
    beforeMount() {
        this.initConditions();
        this.initLanguages();
        this.initFormats();
        this.initCategories();
    }
}
</script>
<template>
<h1>Create a new sticker</h1>
<form novalidate @submit.prevent="submit">
    <div class="row mb-3">
        <div class="col-12">
            <label for="name" class="form-label required">Name</label>
            <input v-model.trim="inputs.name" id="name" name="name" type="text" class="form-control">
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-12">
            <label for="isbn" class="form-label required">Isbn</label>
            <input v-model.trim="inputs.isbn" id="isbn" name="isbn" type="text" class="form-control">
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-12">
            <label for="author" class="form-label required">author</label>
            <input v-model.trim="inputs.author" id="author" name="author" type="text" class="form-control">
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-12">
            <label for="year" class="form-label required">year</label>
            <input v-model.trim="inputs.year" id="year" name="year" type="text" class="form-control">
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-12">
            <label for="edition" class="form-label required">edition</label>
            <input v-model.trim="inputs.edition" id="edition" name="edition" type="text" class="form-control">
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-12">
            <label for="image" class="form-label required">image</label>
            <input v-model.trim="inputs.image" id="image" name="image" type="text" class="form-control">
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-12">
            <label for="point" class="form-label required">point</label>
            <input v-model.trim="inputs.point" id="point" name="point" type="text" class="form-control">
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-12">
            <label for="description" class="form-label required">Description</label>
            <textarea v-model.trim="inputs.description" id="description" name="description" maxlength="1000" rows="3" class="form-control"></textarea>
        </div>
    </div>
    
    <div class="row">
        <div class="col-md-4 mb-3">
            <label for="conditionId" class="form-label">Condition</label>
            <select v-model.number="inputs.conditionId" id="sizeId" name="sizeId" class="form-select">
                <option selected disabled value="0">Choose a condition</option>
                <LabelValues :items="conditions" />
            </select>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 mb-3">
            <label for="languageId" class="form-label">Language</label>
            <select v-model.number="inputs.languageId" id="languageId" name="languageId" class="form-select">
                <option selected disabled value="0">Choose a condition</option>
                <LabelValues :items="languages" />
            </select>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 mb-3">
            <label for="formatId" class="form-label">Format</label>
            <select v-model.number="inputs.formatId" id="formatId" name="formatId" class="form-select">
                <option selected disabled value="0">Choose a condition</option>
                <LabelValues :items="formats" />
            </select>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 mb-3">
            <label for="categoryId" class="form-label">categoryId</label>
            <select v-model.number="inputs.categoryId" id="categoryId" name="categoryId" class="form-select">
                <option selected disabled value="0">Choose a condition</option>
                <LabelValues :items="categories" />
            </select>
        </div>
    </div>
    <div class="d-grid d-md-flex justify-content-md-end mb-3">
        <button type="submit" class="btn btn-dark">Sauvegarder</button>
    </div>
</form>
</template>
