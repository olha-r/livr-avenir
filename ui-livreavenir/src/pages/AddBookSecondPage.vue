<template>
    <main class="container-xl my-5">
        <div class="justify-content-center align-items-center book-details-container">
            <div class="text-center">
                <h1>Ajouter un livre (2/2)</h1>
            </div>
            <form>
                <div class="row">
                    <div class="col-md-6">
                        <label for="description" class="form-label">Description</label>
                        <textarea name="description" id="description" class="form-control" rows="5"></textarea>
                    </div>
                    <div class="col-md-6">
                        <label for="state" class="form-label">Etat du livre</label>
                        <select id="state" class="form-select">
                            <option value="">Choisir...</option>
                            <option>Excellent</option>
                            <option>Très bon</option>
                            <option>Bon</option>
                            <option>Satisfaisant</option>
                        </select>


                        <label for="estimation" class="form-label pt-3">Estimation en points</label>
                        <select class="form-select" id="estimation" required>
                            <option value="">Choisir...</option>
                            <option>1</option>
                        </select>
                    </div>
                </div>

                <div class="row my-3">
                    <div class="col-md-6">
                        <p>Categories:</p>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="category">
                                    <label class="form-check-label" for="category">Littérature</label>
                                </div>
                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="category">
                                    <label class="form-check-label" for="category">Jeunesse</label>
                                </div>

                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="category">
                                    <label class="form-check-label" for="category">Bandes dessinées</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="category">
                                    <label class="form-check-label" for="category">Loisir - Nature - Voyage</label>
                                </div>
                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="category">
                                    <label class="form-check-label" for="category">Art - Musique - Cinéma</label>
                                </div>
                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="category">
                                    <label class="form-check-label" for="category">Autre</label>
                                </div>
                            </div>
                        </div>


                    </div>
                
                    <div class="col-md-6 mt-3">
                        <div class="mb-3">
                            <div class="mb-3">
                                <label for="imageUpload" class="form-label">Ajouter l'image de votre livre</label>
                                <input name="imageUpload" id="imageUpload" type="file" class="form-control">
                            </div>
                        </div>
                    </div>
                </div>
                    <div class="d-flex justify-content-center">
                        <button type="submit" class="btn btn-lg btn-primary col-md-4 my-3">Ajouter</button>
                    </div>


            </form>
        </div>
    </main>
</template>