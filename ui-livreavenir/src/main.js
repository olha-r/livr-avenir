import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import i18n from './locales/i18n.js';
import { createPinia } from 'pinia'
import {http} from './services/axios.js';

import './assets/css/main.css';

const app = createApp(App);
const pinia = createPinia();
app.config.globalProperties.$axios = http;

app.use(router);
app.use(i18n);
app.use(pinia);

app.mount('#app')



