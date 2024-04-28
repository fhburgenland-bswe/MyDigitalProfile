import { describe, it, expect, vi, beforeEach, afterEach } from 'vitest';
import { mount, flushPromises } from '@vue/test-utils';
import MainPage from '@/components/MainPage/MainPage.vue'
import * as userService from '@/services/user.service';


// Mock the getUserData function from user.service
vi.mock('@/services/user.service', () => ({
    getUserData: vi.fn(),
}));

describe('MainPage.vue', () => {
    let wrapper;

    beforeEach(() => {
        userService.getUserData.mockClear();
    });

    afterEach(() => {
        wrapper.unmount();
    });

    /**
     * Test to verify that user data is fetched on component mount and is handled correctly on success.
     * It checks if the service call was made and that the component correctly displays user data.
     */
    it('should fetch user data on mount and handle success', async () => {
        const mockUserData = { vorname: 'Franz', nachname: 'Huber' };
        userService.getUserData.mockResolvedValue(mockUserData);
        wrapper = mount(MainPage);
        await flushPromises();
        expect(userService.getUserData).toHaveBeenCalled();
        expect(wrapper.text()).toContain('Franz Huber');
    });

    /**
     * Test to verify that the component properly handles and displays errors during data fetching.
     * It ensures that the error handling mechanism is functional by checking for error messages in the component.
     */
    it('should handle errors during data fetching', async () => {
        userService.getUserData.mockRejectedValue(new Error('Fetch error'));
        wrapper = mount(MainPage);
        await flushPromises();
        expect(userService.getUserData).toHaveBeenCalled();
        expect(wrapper.text()).toContain('Error fetching user data:');
    });

    /**
     * Test to verify that the menu toggle functionality works as expected.
     * It simulates user interaction by clicking the hamburger menu button and checks the toggling of the menu's open state.
     */
    it('toggles menu open state when hamburger button is clicked', async () => {
        wrapper = mount(MainPage);
        expect(wrapper.vm.isMenuOpen).toBe(false);
        await wrapper.find('.hamburger-menu').trigger('click');
        expect(wrapper.vm.isMenuOpen).toBe(true);
        await wrapper.find('.hamburger-menu').trigger('click');
        expect(wrapper.vm.isMenuOpen).toBe(false);
    });
});
