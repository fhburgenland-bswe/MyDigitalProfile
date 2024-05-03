import { describe, it, expect, vi, beforeEach, afterEach } from 'vitest';
import { mount, flushPromises } from '@vue/test-utils';
import MainPage from '@/components/MainPage/MainPage.vue';
import * as userService from '@/services/user.service';
import { createRouter, createWebHistory } from 'vue-router';

// Set up localStorage mock
vi.stubGlobal('localStorage', {
    getItem: vi.fn(() => '123'),  // Mock the getItem method to return a user ID
    setItem: vi.fn(),
    removeItem: vi.fn(),
});

const getUserDataSpy = vi.spyOn(userService, 'getUserData').mockResolvedValue({ vorname: 'Franz', nachname: 'Huber' });

const routes = [{ path: '/', component: MainPage }];
const router = createRouter({
    history: createWebHistory(),
    routes,
});
router.push = vi.fn(); // Mock the push method

describe('MainPage.vue', () => {
    let wrapper;

    beforeEach(async () => {
        vi.clearAllMocks();
        wrapper = mount(MainPage, {
            global: {
                plugins: [router]
            }
        });
        await router.isReady();
        await flushPromises();
        await wrapper.vm.$nextTick();
    });

    afterEach(() => {
        wrapper.unmount();
    });

    it('should fetch user data on mount and handle success', async () => {
        expect(getUserDataSpy).toHaveBeenCalled();
        expect(wrapper.text()).toContain('Franz Huber');
    });



    it('toggles menu open state when hamburger button is clicked', async () => {
        expect(wrapper.vm.isMenuOpen).toBe(false);
        await wrapper.find('.hamburger-menu').trigger('click');
        expect(wrapper.vm.isMenuOpen).toBe(true);
        await wrapper.find('.hamburger-menu').trigger('click');
        expect(wrapper.vm.isMenuOpen).toBe(false);
    });

    it('should show update data modal when editable field is clicked', async () => {
        await wrapper.find('.editable-icon').trigger('click');
        expect(wrapper.vm.isModalVisible).toBe(true);
        expect(wrapper.vm.editableField).toBe('standort');
    });



});
