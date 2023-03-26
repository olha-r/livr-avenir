<template>
    <main class="container-xl my-5">
        <div class="row justify-content-center align-items-center">
            <div class="d-flex justify-content-center mt-4">
                <h1>Créer un compte</h1>
            </div>
            <form class="col-md-5">
                <div class="mb-3">
                    <div class="row">
                        <div class="col-md-6">
                            <label for="firstName" class="form-label required">Prénom</label>
                            <input name="firstName" id="firstName" type="text" class="form-control"
                                aria-describedby="firstNameHelp">
                        </div>
                        <div class="col-md-6">
                            <label for="lastName" class="form-label required">Nom</label>
                            <input name="lastName" id="lastName" type="text" class="form-control"
                                aria-describedby="lastNameHelp">
                        </div>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label required">Email</label>
                    <input name="email" id="email" type="email" class="form-control" aria-describedby="emailHelp">
                    <div id="emailHelp" class="form-text">ex.: prenom.nom@domain.com</div>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label required">Mot de passe</label>
                    <input name="password" id="password" type="password" class="form-control">
                    <div id="passwordHelp" class="form-text">ex.: Motdepasse123!</div>
                </div>
                <div class="mb-3">
                    <label for="confirmPassword" class="form-label required">Confirmer le mot de passe</label>
                    <input name="confirmPassword" id="confirmPassword" type="password" class="form-control">
                </div>
                <button nametype="submit" class="btn btn-primary col-12 mb-3">Créer</button>
            </form>
        </div>
    </main>
</template>