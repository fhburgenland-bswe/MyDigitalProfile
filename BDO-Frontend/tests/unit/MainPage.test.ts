import { describe, it, expect, vi, beforeEach, afterEach } from 'vitest';
import { mount, flushPromises } from '@vue/test-utils';
import MainPage from '../../../src/components/MainPage/MainPage.vue'
import * as userService from '@/services/user.service';

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

    it('should fetch user data on mount and handle success', async () => {
        const mockUserData = { vorname: 'John', nachname: 'Doe' };
        userService.getUserData.mockResolvedValue(mockUserData);
        wrapper = mount(MainPage);
        await flushPromises();
        expect(userService.getUserData).toHaveBeenCalled();
        expect(wrapper.text()).toContain('John Doe');
    });

    it('should handle errors during data fetching', async () => {
        userService.getUserData.mockRejectedValue(new Error('Fetch error'));
        wrapper = mount(MainPage);
        await flushPromises();
        expect(userService.getUserData).toHaveBeenCalled();
        expect(wrapper.text()).toContain('Error fetching user data:');
    });

    it('toggles menu open state when hamburger button is clicked', async () => {
        wrapper = mount(MainPage);
        expect(wrapper.vm.isMenuOpen).toBe(false);
        await wrapper.find('.hamburger-menu').trigger('click');
        expect(wrapper.vm.isMenuOpen).toBe(true);
        await wrapper.find('.hamburger-menu').trigger('click');
        expect(wrapper.vm.isMenuOpen).toBe(false);
    });
});
