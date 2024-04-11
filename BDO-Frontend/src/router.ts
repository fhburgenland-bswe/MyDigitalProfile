import { createRouter, createWebHistory } from 'vue-router'
import MainPage from './components/MainPage/MainPage.vue'
import LoginPage   from "@/components/LoginPage/LoginPage.vue";

const routes = [
    { path: '/Main', component: MainPage },
    { path: '/', component: LoginPage }

]

const router = createRouter({
    history: createWebHistory(),
    routes
})
export default router