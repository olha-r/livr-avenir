import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { createPinia } from 'pinia'

import toast from './plugins/toast';

import './assets/css/main.css';

const app = createApp(App);
const pinia = createPinia();

app.use(router);
app.use(pinia);
app.use(toast);

app.mount('#app')
