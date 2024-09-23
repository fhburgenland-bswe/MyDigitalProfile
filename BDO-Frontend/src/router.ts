import { createRouter, createWebHistory } from 'vue-router';
import MainPage from './components/MainPage/MainPage.vue';
import LoginPage from '@/components/LoginPage/LoginPage.vue';
import UserProfile from '@/components/TeamMemberPage/UserProfile.vue';
import TeamMemberList from '@/components/TeamMemberPage/TeamMemberList.vue';
import TeamMemberDetail from '@/components/TeamMemberPage/TeamMemberDetail.vue';

const routes = [
    { path: '/Main', component: MainPage },
    { path: '/', component: LoginPage },
    { path: '/user-profile', component: UserProfile },
    { path: '/team-members', component: TeamMemberList },
    { path: '/team-members/:id', component: TeamMemberDetail, props: true }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;