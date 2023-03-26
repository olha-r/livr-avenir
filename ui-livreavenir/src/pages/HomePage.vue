<template>
    <main class="container-xl my-2 home-page">
        <div class="mb-4 rounded-3 home-page-img position-relative">
            <div class="position-absolute top-50 start-50 translate-middle-x text-center">
                <h1>Coffre aux trésors</h1>
                <h6>Envie d'echanger tes livres? Decouvre comment ça marche.</h6>
                <button class="btn btn-lg btn-dark" type="button">A propos</button>
            </div>
        </div>
        <div class="input-group mb-3">
            <input class="form-control" type="text" placeholder="Tapez pour rechercher">
            <button class="btn btn-primary" type="button" id="bookSearch"><i class="bi bi-search"></i></button>
        </div>
        <div class="d-flex flex-row justify-content-around mb-3">
            <button type="button" class="btn btn-primary me-3">57 livres disponible</button>
            <button type="button" class="btn btn-primary me-3">21 livres emprentés</button>
            <button type="button" class="btn btn-primary">6 livres ajoutés en favoris</button>
        </div>

        <h3 class="text-center my-4">Derniers livres ajoutés récemment</h3>

        <div class="row row-cols-2 row-cols-md-4 g-5">

            <div class="col">
                <div class="card">
                    <img src="../assets/images/book-image-example.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title text-center">Titre de livre</h5>
                        <p class="card-subtitle mb-2 text-muted text-center">Auteur de livre</p>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <img src="../assets/images/book-image-example.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title text-center">Titre de livre</h5>
                        <p class="card-subtitle mb-2 text-muted text-center">Auteur de livre</p>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <img src="../assets/images/book-image-example.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title text-center">Titre de livre</h5>
                        <p class="card-subtitle mb-2 text-muted text-center">Auteur de livre</p>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <img src="../assets/images/book-image-example.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title text-center">Titre de livre</h5>
                        <p class="card-subtitle mb-2 text-muted text-center">Auteur de livre</p>
                    </div>
                </div>
            </div>
        </div>

        <h3 class="text-center my-4">Derniers livres ajoutés en favoris</h3>

        <div class="row row-cols-2 row-cols-md-4 g-5">
            <div class="col">
                <div class="card">
                    <img src="../assets/images/book-image-example.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title text-center">Titre de livre</h5>
                        <p class="card-subtitle mb-2 text-muted text-center">Auteur de livre</p>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <img src="../assets/images/book-image-example.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title text-center">Titre de livre</h5>
                        <p class="card-subtitle mb-2 text-muted text-center">Auteur de livre</p>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <img src="../assets/images/book-image-example.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title text-center">Titre de livre</h5>
                        <p class="card-subtitle mb-2 text-muted text-center">Auteur de livre</p>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <img src="../assets/images/book-image-example.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title text-center">Titre de livre</h5>
                        <p class="card-subtitle mb-2 text-muted text-center">Auteur de livre</p>
                    </div>
                </div>
            </div>
        </div>

    </main>
</template>

<script>

</script>