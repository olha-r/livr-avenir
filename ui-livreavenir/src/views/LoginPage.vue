<script setup>
import { reactive, computed } from "vue";
import { useRouter } from "vue-router";
import useValidate from "@vuelidate/core";
import { email, helpers, required } from "@vuelidate/validators";
import { AuthStore } from "../store/auth-store";
import ValidationMessage from "../components/commons/ValidationMessage.vue";
import { storeToRefs } from "pinia";
import { usePageStore } from "../store/page-store";

const user = reactive({
    email: null,
    password: null,
});
const rules = computed(() => {
    return {
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
    };
});
const v$ = useValidate(rules, user);
const authStore = AuthStore();
const { userRole } = storeToRefs(authStore);
const pageStore = usePageStore();
const router = useRouter();
const onSubmit = async () => {
    await v$.value.$validate();
    if (!v$.value.$error) {
        console.log("No errors");
        console.log(user);
        const resp = await authStore.login(user);
        console.log("Response", resp);
        if (resp.status === 200) {
            const token = resp.body.token;
            console.log("Token after login", token);
            console.log(`Utilisateur ${user.email} est connecté`);
            if (userRole.value == "ADMIN") {
                router.push("/admin");
                pageStore.alert.type = "success";
                pageStore.alert.message = `Utilisateur ${user.email} est connecté`;
                pageStore.alert.show = true;
            } else {
                pageStore.alert.type = "success";
                pageStore.alert.message = `Utilisateur ${user.email} est connecté`;
                pageStore.alert.show = true;
            }
        } else {
            pageStore.alert.type = "error";
            pageStore.alert.message = `Nous n'avons pas pu trouvé utilisateur ${user.email}.`;
            pageStore.alert.show = true;
            console.log(
                `Nous n'avons pas pu trouvé utilisateur ${user.email}.`
            );
        }
    } else {
        console.log("There are errors");
    }
};
</script>
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
                        v-model.trim="user.email"
                        name="email"
                        id="email"
                        type="email"
                        class="form-control"
                        :class="{ 'is-invalid': v$.email.$error }"
                        aria-describedby="emailHelp"
                    />
                    <ValidationMessage :model="v$.email" />
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label required"
                        >Mot de passe</label
                    >
                    <input
                        v-model.trim="user.password"
                        name="password"
                        id="password"
                        type="password"
                        class="form-control"
                        :class="{
                            'is-invalid': v$.password.$error,
                        }"
                    />
                    <ValidationMessage :model="v$.password" />
                </div>
                <button nametype="submit" class="btn btn-primary col-12 mb-3">
                    Se connecter
                </button>
            </form>
        </div>
    </main>
</template>


