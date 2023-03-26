<template>
    <main class="container-xl my-5">
        <div class="justify-content-center align-items-center book-details-container">
            <div class="text-center">
                <h1>Ajouter un livre (1/2)</h1>
            </div>
            <form>
                <div class="row">
                    <div class="col-md-8 mb-3">
                        <label for="title" class="form-label">Titre de livre</label>
                        <input name="title" id="title" type="text" class="form-control">
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="isbn" class="form-label">ISBN</label>
                        <input name="isbn" id="isbn" type="text" class="form-control">
                    </div>

                    <div class="col-md-8 mb-3">
                        <label for="author" class="form-label">Auteur de livre</label>
                        <input name="author" id="author" type="text" class="form-control">
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="publicationYear" class="form-label">L'année de publication</label>
                        <input name="publicationYear" id="publicationYear" type="text" class="form-control">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="edition" class="form-label">Edition</label>
                        <input name="edition" id="edition" type="text" class="form-control">
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="format" class="form-label">Format</label>
                        <select name="format" id="format" class="form-select">
                            <option value="">Choisir...</option>
                            <option>Broché</option>
                            <option>Relié</option>
                        </select>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="language" class="form-label">Langue</label>
                        <select name="language" id="language" class="form-select">
                            <option value="">Choisir...</option>
                            <option>Français</option>
                            <option>Anglais</option>
                            <option>Autre</option>
                        </select>
                    </div>
                </div>
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-lg btn-primary col-md-4 my-3">Continuer</button>
                </div>
            </form>
        </div>
    </main>
</template>