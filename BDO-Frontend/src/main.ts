import './assets/main.css'
import 'vue3-toastify/dist/index.css';

import { createApp } from 'vue'
import router from './router'
import App from './App.vue'
import Vue3Toastify from "vue3-toastify";



createApp(App)
    .use(router)
    .use(Vue3Toastify, {
        multiple: false,
    })
    .mount('#app')
