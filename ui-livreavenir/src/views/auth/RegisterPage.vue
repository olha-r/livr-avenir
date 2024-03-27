<script setup>
import { reactive, computed } from "vue";
import { useRouter } from "vue-router";
import { useI18n } from "vue-i18n";
import useValidate from "@vuelidate/core";
import {
    email,
    helpers,
    minLength,
    maxLength,
    required,
    sameAs,
} from "@vuelidate/validators";
import ValidationMessage from "@/components/commons/ValidationMessage.vue";
import { useAuthStore } from "@/stores/auth-store";
import { usePageStore } from "@/stores/page-store";

const { t } = useI18n();
const router = useRouter();
const authStore = useAuthStore();
const user = reactive({
    firstName: null,
    lastName: null,
    email: null,
    password: null,
    confirmPassword: null,
});
const validPassword = helpers.regex(
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%&*?]).{8,42}$/
);
const rules = computed(() => {
    return {
        firstName: {
            required: helpers.withMessage(
                `${t("admin.validationMessages.required")}`,
                required
            ),
            minLength: helpers.withMessage(
                `${t("admin.validationMessages.minLengthFirstName")}`,
                minLength(2)
            ),
            maxLength: helpers.withMessage(
                `${t("admin.validationMessages.maxLengthFirstName")}`,
                maxLength(30)
            ),
        },
        lastName: {
            required: helpers.withMessage(
                `${t("admin.validationMessages.required")}`,
                required
            ),
            minLength: helpers.withMessage(
                `${t("admin.validationMessages.minLengthLastName")}`,
                minLength(2)
            ),
            maxLength: helpers.withMessage(
                `${t("admin.validationMessages.maxLengthLastName")}`,
                maxLength(100)
            ),
        },
        email: {
            required: helpers.withMessage(
                `${t("admin.validationMessages.required")}`,
                required
            ),
            email: helpers.withMessage(
                `${t("admin.validationMessages.email")}`,
                email
            ),
        },
        password: {
            required: helpers.withMessage(
                `${t("admin.validationMessages.required")}`,
                required
            ),
            validPassword: helpers.withMessage(
                `${t("admin.validationMessages.validPassword")}`,
                validPassword
            ),
        },
        confirmPassword: {
            required: helpers.withMessage(
                `${t("admin.validationMessages.required")}`,
                required
            ),
            sameAs: helpers.withMessage(
                `${t("admin.validationMessages.sameAsPassword")}`,
                sameAs(user.password)
            ),
        },
    };
});
const v$ = useValidate(rules, user);
const pageStore = usePageStore();
const onSubmit = async () => {
    const resp = await v$.value.$validate();
    if (!v$.value.$error) {
        delete user.confirmPassword;
        const resp = await authStore.register(user);
        if (resp.status === 204) {
            v$.value.$reset();
            router.push("/auth/login");
            pageStore.alert.type = "success";
            pageStore.alert.message = `Utilisateur ${user.email} a été créé avec succès. Vous pouvez vous connecter.`;
            pageStore.alert.show = true;
            setTimeout(() => {
                pageStore.alert.show = false;
            }, 5000);
        } else {
            alert(`Nous n'avons pas pu créer utilisateur ${user.email}.`);
        }
    }
};
</script>

<template>
    <main class="container-xl my-1">
        <div class="row justify-content-center align-items-center">
            <div class="d-flex justify-content-center mt-4">
                <h3>{{ t("signUpForm.title") }}</h3>
            </div>
            <form novalidate @submit.prevent="onSubmit" class="col-md-5">
                <div class="mb-3">
                    <div class="row">
                        <div class="col-md-6">
                            <label
                                for="firstName"
                                class="form-label required"
                                >{{ t("signUpForm.firstName") }}</label
                            >
                            <input
                                v-model.trim="user.firstName"
                                name="firstName"
                                id="firstName"
                                type="text"
                                class="form-control"
                                :class="{
                                    'is-invalid': v$.firstName.$error,
                                }"
                            />
                            <ValidationMessage :model="v$.firstName" />
                        </div>
                        <div class="col-md-6">
                            <label for="lastName" class="form-label required">{{
                                t("signUpForm.lastName")
                            }}</label>
                            <input
                                v-model.trim="user.lastName"
                                name="lastName"
                                id="lastName"
                                type="text"
                                class="form-control"
                                :class="{
                                    'is-invalid': v$.lastName.$error,
                                }"
                            />
                            <ValidationMessage :model="v$.lastName" />
                        </div>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label required">{{
                        t("signUpForm.email.label")
                    }}</label>
                    <input
                        v-model.trim="user.email"
                        name="email"
                        id="email"
                        type="email"
                        class="form-control"
                        aria-describedby="emailHelp"
                        :class="{ 'is-invalid': v$.email.$error }"
                    />
                    <div id="emailHelp" class="form-text">
                        {{ t("signUpForm.email.helpText") }}
                    </div>
                    <ValidationMessage :model="v$.email" />
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label required">{{
                        t("signUpForm.password.label")
                    }}</label>
                    <input
                        v-model.trim="user.password"
                        name="password"
                        id="password"
                        type="password"
                        class="form-control"
                        :class="{ 'is-invalid': v$.password.$error }"
                    />
                    <div id="passwordHelp" class="form-text">
                        {{ t("signUpForm.password.helpText") }}
                    </div>
                    <ValidationMessage :model="v$.password" />
                </div>
                <div class="mb-3">
                    <label for="confirmPassword" class="form-label required">{{
                        t("signUpForm.confirmPassword")
                    }}</label>
                    <input
                        v-model.trim="user.confirmPassword"
                        name="confirmPassword"
                        id="confirmPassword"
                        type="password"
                        class="form-control"
                        :class="{
                            'is-invalid': v$.confirmPassword.$error,
                        }"
                    />
                    <ValidationMessage :model="v$.confirmPassword" />
                </div>
                <button type="submit" class="btn btn-primary col-12 mb-3">
                    {{ t("signUpForm.button") }}
                </button>
            </form>
        </div>
    </main>
</template>
