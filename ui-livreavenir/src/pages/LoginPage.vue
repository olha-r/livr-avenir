<template>
    <main class="container-xl my-5">
        <div class="row justify-content-center align-items-center">
            <div class="d-flex justify-content-center mt-4">
                <h1>Se connecter</h1>
            </div>
            <form novalidate @submit.prevent="onSubmit" class="col-md-5">
                <div class="mb-3">
                    <label for="email" class="form-label required">Email</label>
                    <input
                        v-model.trim="state.user.email"
                        name="email"
                        id="email"
                        type="email"
                        class="form-control"
                        :class="{ 'is-invalid': v$.user.email.$error }"
                        aria-describedby="emailHelp"
                    />
                    <ValidationMessage :model="v$.user.email" />
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label required"
                        >Mot de passe</label
                    >
                    <input
                        v-model.trim="state.user.password"
                        name="password"
                        id="password"
                        type="password"
                        class="form-control"
                        :class="{
                            'is-invalid': v$.user.password.$error,
                        }"
                    />
                    <ValidationMessage :model="v$.user.password" />
                </div>
                <button nametype="submit" class="btn btn-primary col-12 mb-3">
                    Se connecter
                </button>
            </form>
        </div>
    </main>
</template>

<script>
import { reactive, computed } from "vue";
import useValidate from "@vuelidate/core";
import {
    email,
    helpers,
    minLength,
    maxLength,
    required,
    sameAs,
} from "@vuelidate/validators";
import ValidationMessage from "../components/commons/ValidationMessage.vue";
export default {
    name: "LoginPage",
    components: {
        ValidationMessage,
    },
    setup() {
        const state = reactive({
            user: {
                email: null,
                password: null,
            },
        });
        const rules = computed(() => {
            return {
                user: {
                    email: {
                        required: helpers.withMessage(
                            "Veuillez renseigner ce champ.",
                            required
                        ),
                        email: helpers.withMessage(
                            "Veuillez saisir votre adresse e-mail au format votrenom@example.com",
                            email
                        ),
                    },
                    password: {
                        required: helpers.withMessage(
                            "Veuillez renseigner ce champ.",
                            required
                        ),
                    },
                },
            };
        });
        const v$ = useValidate(rules, state);
        return {
            state,
            v$,
        };
    },
    methods: {
        async onSubmit() {
            this.v$.$validate();
            if (!this.v$.$error) {
                const my_user = this.state.user;
                const resp = await fetch("http://localhost:8080/auth/sign-in", {
                    method: "POST",
                    headers: {
                        method: "Post",
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(my_user),
                });
                if (resp.status === 200) {
                    alert(`Utilisateur ${my_user.email} est connecté`);
                } else {
                    alert(
                        `Nous n'avons pas pu trouvé utilisateur ${my_user.email}.`
                    );
                }
            }
        },
    },
};
</script>
