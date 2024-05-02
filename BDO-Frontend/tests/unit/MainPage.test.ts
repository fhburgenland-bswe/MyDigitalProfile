import { describe, it, expect, vi, beforeEach, afterEach } from 'vitest';
import { mount, flushPromises } from '@vue/test-utils';
import MainPage from '@/components/MainPage/MainPage.vue';
import * as userService from '@/services/user.service';
import { createRouter, createWebHistory } from 'vue-router';

// Set up localStorage mock
vi.stubGlobal('localStorage', {
    getItem: vi.fn(() => '123'),  // Assuming 'userId' needs to be present
    setItem: vi.fn(),
    removeItem: vi.fn(),
});

// Continue with your existing setup
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
        await wrapper.vm.$nextTick();  // Ensure Vue has processed everything
    });

    afterEach(() => {
        wrapper.unmount();
    });

    it('should fetch user data on mount and handle success', async () => {
        expect(getUserDataSpy).toHaveBeenCalled();
        expect(wrapper.text()).toContain('Franz Huber');
    });

    it('should handle errors during data fetching', async () => {
        getUserDataSpy.mockRejectedValueOnce(new Error('Fetch error'));
        await flushPromises();
        expect(getUserDataSpy).toHaveBeenCalled();
        expect(wrapper.text()).toContain('Error fetching user data: ');
    });

    it('toggles menu open state when hamburger button is clicked', async () => {
        expect(wrapper.vm.isMenuOpen).toBe(false);
        await wrapper.find('.hamburger-menu').trigger('click');
        expect(wrapper.vm.isMenuOpen).toBe(true);
        await wrapper.find('.hamburger-menu').trigger('click');
        expect(wrapper.vm.isMenuOpen).toBe(false);
    });
});
