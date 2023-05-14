<script>
import { ref } from 'vue';
export default {
    name: 'RegisterPage',
    setup() {
        const user = ref({
            firstName: null,
            lastName: null,
            email: null,
            password: null,
            confirmPassword: null,
        });
        const onSubmit = async() => {
            const my_user = user.value;
            console.log(my_user);
            delete my_user.confirmPassword;
            const resp = await fetch('http://localhost:8080/auth/sign-up', {
                method: 'POST',
                headers: {
                    'method': 'Post',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(
                    my_user)
            })
            console.log('Form submit for user register', resp);
            if (resp.status === 204) {
                alert(`Utilisateur ${my_user.email} a été créer avec success.`);
            } else {
                console.error(resp);
                alert(`Nous n'avons pas pu créer utilisateur ${my_user.email}.`);
            }
    };
        return {
                user,
                onSubmit
        }
    }
}
</script>
<template>
    <main class="container-xl my-5">
        <div class="row justify-content-center align-items-center">
            <div class="d-flex justify-content-center mt-4">
                <h1>Créer un compte</h1>
            </div>
            <form novalidate @submit.prevent="onSubmit" class="col-md-5">
                <div class="mb-3">
                    <div class="row">
                        <div class="col-md-6">
                            <label for="firstName" class="form-label required">Prénom</label>
                            <input v-model.trim="user.firstName" name="firstName" id="firstName" type="text" class="form-control">
                        </div>
                        <div class="col-md-6">
                            <label for="lastName" class="form-label required">Nom</label>
                            <input v-model.trim="user.lastName"  name="lastName" id="lastName" type="text" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label required">Email</label>
                    <input v-model.trim="user.email"  name="email" id="email" type="email" class="form-control" aria-describedby="emailHelp">
                    <div id="emailHelp" class="form-text">ex.: prenom.nom@domain.com</div>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label required">Mot de passe</label>
                    <input v-model.trim="user.password"  name="password" id="password" type="password" class="form-control">
                    <div id="passwordHelp" class="form-text">ex.: Motdepasse123!</div>
                </div>
                <div class="mb-3">
                    <label for="confirmPassword" class="form-label required">Confirmer le mot de passe</label>
                    <input name="confirmPassword" id="confirmPassword" type="password" class="form-control">
                </div>
                <button type="submit" class="btn btn-primary col-12 mb-3">Créer</button>
            </form>
        </div>
    </main>
</template>