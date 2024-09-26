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

// Mock userService method getUserData to return mock user data
const getUserDataSpy = vi.spyOn(userService, 'getUserData').mockResolvedValue({ vorname: 'Franz', nachname: 'Huber' });

// Create router instance for testing
const routes = [{ path: '/', component: MainPage }];
const router = createRouter({
    history: createWebHistory(),
    routes,
});

// Mock the push method of the router
router.push = vi.fn();

describe('MainPage.vue', () => {
    let wrapper: ReturnType<typeof mount> | null; // Typisiere den Wrapper korrekt

    beforeEach(async () => {
        vi.clearAllMocks(); // Clears mocks before each test
        // Mount the component with router
        wrapper = mount(MainPage, {
            global: {
                plugins: [router]
            }
        });
        await router.isReady(); // Make sure router is ready before running tests
        await flushPromises();
        await wrapper.vm.$nextTick(); // Wait for the Vue component to update
    });

    afterEach(() => {
        // Unmount wrapper and reset wrapper to null
        wrapper?.unmount();
        wrapper = null;
    });

    it('should fetch user data on mount and handle success', async () => {
        // Check if getUserData was called and the name is displayed
        expect(getUserDataSpy).toHaveBeenCalled();
        expect(wrapper?.text()).toContain('Franz Huber');
    });

    it('toggles menu open state when hamburger button is clicked', async () => {
        // Check initial state of menu
        expect(wrapper?.vm.isMenuOpen).toBe(false);
        // Click the hamburger button and check state
        await wrapper?.find('.hamburger-menu').trigger('click');
        expect(wrapper?.vm.isMenuOpen).toBe(true);
        // Click again to close the menu
        await wrapper?.find('.hamburger-menu').trigger('click');
        expect(wrapper?.vm.isMenuOpen).toBe(false);
    });

    it('should show update data modal when editable field is clicked', async () => {
        // Trigger the click on the editable icon and check modal visibility
        await wrapper?.find('.editable-icon').trigger('click');
        expect(wrapper?.vm.isModalVisible).toBe(true);
        expect(wrapper?.vm.editableField).toBe('standort');
    });
});
