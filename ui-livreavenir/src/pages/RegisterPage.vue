<template>
    <main class="container-xl my-1">
        <div class="row justify-content-center align-items-center">
            <div class="d-flex justify-content-center mt-4">
                <h1>Créer un compte</h1>
            </div>
            <form novalidate @submit.prevent="onSubmit" class="col-md-5">
                <div class="mb-3">
                    <div class="row">
                        <div class="col-md-6">
                            <label for="firstName" class="form-label required"
                                >Prénom</label
                            >
                            <input
                                v-model.trim="state.user.firstName"
                                name="firstName"
                                id="firstName"
                                type="text"
                                class="form-control"
                                :class="{
                                    'is-invalid': v$.user.firstName.$error,
                                }"
                            />
                            <ValidationMessage :model="v$.user.firstName" />
                        </div>
                        <div class="col-md-6">
                            <label for="lastName" class="form-label required"
                                >Nom</label
                            >
                            <input
                                v-model.trim="state.user.lastName"
                                name="lastName"
                                id="lastName"
                                type="text"
                                class="form-control"
                                :class="{
                                    'is-invalid': v$.user.lastName.$error,
                                }"
                            />
                            <ValidationMessage :model="v$.user.lastName" />
                        </div>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label required">Email</label>
                    <input
                        v-model.trim="state.user.email"
                        name="email"
                        id="email"
                        type="email"
                        class="form-control"
                        aria-describedby="emailHelp"
                        :class="{ 'is-invalid': v$.user.email.$error }"
                    />
                    <div id="emailHelp" class="form-text">
                        ex.: prenom.nom@domain.com
                    </div>
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
                        :class="{ 'is-invalid': v$.user.password.$error }"
                    />
                    <div id="passwordHelp" class="form-text">
                        Au moins 1 majuscule et 1 miniscule, au moins un nombre
                        au moins 1 de !@#%&*?
                    </div>
                    <ValidationMessage :model="v$.user.password" />
                </div>
                <div class="mb-3">
                    <label for="confirmPassword" class="form-label required"
                        >Confirmer le mot de passe</label
                    >
                    <input
                        v-model.trim="state.user.confirmPassword"
                        name="confirmPassword"
                        id="confirmPassword"
                        type="password"
                        class="form-control"
                        :class="{
                            'is-invalid': v$.user.confirmPassword.$error,
                        }"
                    />
                    <ValidationMessage :model="v$.user.confirmPassword" />
                </div>
                <button type="submit" class="btn btn-primary col-12 mb-3">
                    Créer
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
    name: "RegisterPage",
    components: {
        ValidationMessage,
    },
    setup() {
        const state = reactive({
            user: {
                firstName: null,
                lastName: null,
                email: null,
                password: null,
                confirmPassword: null,
            },
        });
        const validPassword = helpers.regex(
            /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%&*?]).{8,42}$/
        );
        const rules = computed(() => {
            return {
                user: {
                    firstName: {
                        required: helpers.withMessage(
                            "Veuillez renseigner ce champ.",
                            required
                        ),
                        minLength: helpers.withMessage(
                            "Veuillez saisir au moins 2 caractères.",
                            minLength(2)
                        ),
                        maxLength: helpers.withMessage(
                            "Veuillez saisir moins de 30 caractères.",
                            maxLength(30)
                        ),
                    },
                    lastName: {
                        required: helpers.withMessage(
                            "Veuillez renseigner ce champ.",
                            required
                        ),
                        minLength: helpers.withMessage(
                            "Veuillez saisir au moins 2 caractères.",
                            minLength(2)
                        ),
                        maxLength: helpers.withMessage(
                            "Veuillez saisir moins de 100 caractères.",
                            maxLength(100)
                        ),
                    },
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
                        validPassword: helpers.withMessage(
                            "Mot de passe non valide.",
                            validPassword
                        ),
                    },
                    confirmPassword: {
                        required: helpers.withMessage(
                            "Veuillez renseigner ce champ.",
                            required
                        ),
                        sameAs: helpers.withMessage(
                            "Veuillez saisir les mots de passe identiques.",
                            sameAs(state.user.password)
                        ),
                    },
                },
            };
        });
        const v$ = useValidate(rules, state);
        return {
            state,
            v$,
            validPassword,
        };
    },
    methods: {
        async onSubmit() {
            this.v$.$validate();
            if (!this.v$.$error) {
                const my_user = this.state.user;
                delete my_user.confirmPassword;
                const resp = await fetch("http://localhost:8080/auth/sign-up", {
                    method: "POST",
                    headers: {
                        method: "Post",
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(my_user),
                });
                if (resp.status === 204) {
                    alert(
                        `Utilisateur ${my_user.email} a été créer avec success.`
                    );
                } else {
                    alert(
                        `Nous n'avons pas pu créer utilisateur ${my_user.email}.`
                    );
                }
            }
        },
    },
};
</script>
