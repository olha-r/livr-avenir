<template>
    <main class="container-xl my-5">
        <div class="justify-content-center align-items-center book-details-container">
            <div class="input-group mb-3">
                <input class="form-control" type="text" placeholder="Tapez pour rechercher">
                <button class="btn btn-primary" type="button" id="bookSearch"><i class="bi bi-search"></i></button>
            </div>
            <div class="row mt-3">
                <h3>Les camarades</h3>
                <div class="row d-flex justify-content-center">
                    <div class="col">
                        <p class="author">Erich Maria Remarque<span class="text-muted fs-6"> (Author)</span></p>
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
                        <button class="btn d-flex align-items-center" href="home.html">Ajouter en favoris <i
                                class="bi bi-heart img-like ps-2"></i></button>
                        <!-- <i class="bi bi-heart-fill"></i> -->
                    </div>
                    <p class="fst-italic">Résumé:</p>
                    <p>Dans l'Allemagne des années trente, trois anciens soldats - trois camarades - tentent de
                        survivre
                        grâce à la vente de voitures d'occasion et au garage qu'ils ont monté. Face au désespoir
                        ambiant
                        certains trouvent refuge dans le nazisme, d'autres dans l'alcool et les femmes, parfois même
                        dans la mort. Misère des meublés, banalité de la prostitution, cabarets sordides constituent
                        le
                        quotidien de nombreux laissés-pour-compte. Robby est l'un d'eux. Mais voilà qu'apparaît la
                        fragile Pat, et avec elle la possibilité de redonner enfin un sens à la vie... L'auteur d'À
                        l'ouest rien de nouveau nous offre un nouveau chef-d'oeuvre.</p>
                    <div class="row">
                        <p><span class="badge text-bg-warning me-1">Category 1</span><span
                                class="badge text-bg-warning me-1">Category
                                2</span></p>
                        <p class="fst-italic">Caractéristiques:</p>

                        <div class="col">
                            <p><span class="fw-lighter">ISBN: </span>2070454673</p>
                        </div>
                        <div class="col">
                            <p><span class="fw-lighter">L'année de parution: </span>2014</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <p><span class="fw-lighter">Edition:</span> Gallimard</p>
                        </div>
                        <div class="col">
                            <p><span class="fw-lighter">Nombres de pages: </span>560</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <p><span class="fw-lighter">Format: </span>Broché</p>
                        </div>
                        <div class="col">
                            <p><span class="fw-lighter">Langue: </span>Français</p>
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
                    <p class="text-center">Bon état</p>
                    <p class="text-center">Ajouté le 23/10/22</p>
                </div>
                <div class="col-12 col-md-2">
                    <p class="text-center">2</p>
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