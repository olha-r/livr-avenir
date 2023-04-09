<script>
import { useRoute } from 'vue-router';

export default {
    setup() {
        return {
            route: useRoute()
        }
    },
    data() {
        return {
            id: this.route.params.id,
            book: {}
        }
    },
    methods: {
        async initBook() {
            const resp = await this.$http.get(`/books/${this.id}/detail`);
            this.book = resp.body;
        }
    },
    beforeMount() {
        this.initBook();
    }
}
</script>
<template>
<main class="container-xl my-5">
    <div class="justify-content-center align-items-center book-details-container">
        <div class="input-group mb-3">
            <input class="form-control" type="text" placeholder="Tapez pour rechercher">
            <button class="btn btn-primary" type="button" id="bookSearch"><i class="bi bi-search"></i></button>
        </div>
        <div class="row mt-3">
            <h3>{{ book.name }}</h3>
            <div class="row d-flex justify-content-center">
                <div class="col">
                    <p class="author">{{ book.author }}<span class="text-muted fs-6"> (Author)</span></p>
                </div>
                <div class="col">
                    <p class="text-end">
                        <i class="bi bi-star-fill"></i>
                        <i class="bi bi-star-fill"></i>
                        <i class="bi bi-star-fill"></i>
                        <i class="bi bi-star-half"></i>
                        <i class="bi bi-star"></i>
                    </p>
                </div>
            </div>
            <div class="col-12 col-md-5 book-image ps-0">
                <img src="../assets/images/book-image-example.jpg" class="img-fluid" alt="book image">
            </div>
            <div class="col book-info p-3">
                <div class="d-flex justify-content-end">
                    <button class="btn d-flex align-items-center" href="home.html">Ajouter en favoris <i class="bi bi-heart img-like ps-2"></i></button>
                    <!-- <i class="bi bi-heart-fill"></i> -->
                </div>
                <p class="fst-italic">Résumé:</p>
                <p>{{ book.description }}</p>
                <div class="row">
                    <p><span class="badge text-bg-warning me-1">{{ book.category }}</span><span class="badge text-bg-warning me-1">{{ book.category }}</span></p>
                    <p >Caractéristiques:</p>

                    <div class="col">
                        <p><span class="fw-light">ISBN: </span>{{ book.isbn }}</p>
                    </div>
                    <div class="col">
                        <p><span class="fw-light">L'année de parution: </span>{{ book.year }}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <p><span class="fw-light">Edition: </span>{{ book.edition }}</p>
                    </div>
                    <div class="col">
                        <p><span class="fw-light">Nombres de pages: </span>560</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <p><span class="fw-light">Format: </span>{{ book.formatName }}</p>
                    </div>
                    <div class="col">
                        <p><span class="fw-light">Langue: </span>{{ book.languageName }}</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row book-owner pt-3 mt-5 d-flex align-items-center">
            <div class="col-12 col-md-2 d-flex justify-content-center">
                <img src="../assets/images/avatar-man.png" class="avatar" alt="avatar">
            </div>
            <div class="col-12 col-md-2">
                <p class="text-center">Jonh Doe</p>
            </div>
            <div class="col-12 col-md-2">
                <p class="text-center">{{ book.conditionName}}</p>
                <p class="text-center">Ajouté le 23/10/22</p>
            </div>
            <div class="col-12 col-md-2">
                <p class="text-center">{{book.point}}</p>
                <p class="text-center">points</p>
            </div>
            <div class="col-12 col-md-2 d-flex flex-column justify-content-center">
                <p class="text-center m-0">Une question?</p>
                <button class="btn" href="#"><i class="bi bi-envelope"></i></button>
            </div>
            <div class="col-12 col-md-2 d-flex flex-column justify-content-center">
                <p class="text-center m-0">Emprenter</p>
                <button class="btn" href="#"><i class="bi bi-handbag"></i></button>
            </div>
        </div>
    </div>

</main>
</template>
