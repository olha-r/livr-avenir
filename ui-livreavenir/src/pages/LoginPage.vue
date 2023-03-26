<template>
    <main class="container-xl my-5">
        <div class="row justify-content-center align-items-center">
            <div class="d-flex justify-content-center mt-4">
                <h1>Se connecter</h1>
            </div>
            <form class="col-md-5">
                <div class="mb-3">
                    <label for="email" class="form-label required">Email</label>
                    <input name="email" id="email" type="email" class="form-control" aria-describedby="emailHelp">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label required">Mot de passe</label>
                    <input name="password" id="password" type="password" class="form-control">
                </div>
                <button nametype="submit" class="btn btn-primary col-12 mb-3">Se connecter</button>
            </form>
        </div>
    </main>
</template>