import { describe, it, expect, vi, beforeEach, afterEach } from 'vitest';
import { mount, flushPromises } from '@vue/test-utils';
import LoginPage from '@/components/LoginPage/LoginPage.vue';
import { login } from '@/services/login.service';
import { useRouter, useRoute } from 'vue-router';
import { toast } from "vue3-toastify";


// Mock the login service
vi.mock('@/services/login.service', () => ({
    login: vi.fn(),
}));

// Create mock router instance
const mockRouter = {
    push: vi.fn()
};

const mockRoute = {
    query: {}
};

// Mock "useRouter" to return the mock router instance
vi.mock('vue-router', () => ({
    useRouter: () => mockRouter,
    useRoute: () => mockRoute,
}));

// Mock the toast
vi.mock("vue3-toastify", () => ({
    toast: vi.fn()
}));

describe('LoginPage.vue', () => {
    let wrapper;

    beforeEach(() => {
        login.mockClear();
        mockRouter.push.mockClear();
        wrapper = mount(LoginPage, {
            global: {
                mocks: {
                    useRouter: () => mockRouter
                }
            }
        });
    });

    afterEach(() => {
        wrapper.unmount();
    });

    it('should handle login with correct email and password successfully', async () => {
        const mockLoginResponse = { success: true, message: 'Login successful', userId: 1 };
        login.mockResolvedValue(mockLoginResponse);
        const emailInput = wrapper.find('input[name="email"]');
        const passwordInput = wrapper.find('input[name="password"]');
        const form = wrapper.find('form');

        await emailInput.setValue('test@example.com');
        await passwordInput.setValue('password');
        await form.trigger('submit');
        await flushPromises();

        expect(login).toHaveBeenCalledWith('test@example.com', 'password');
        expect(mockRouter.push).toHaveBeenCalledWith('/Main');
    });

    it('should handle login failure with incorrect email and password', async () => {
        const mockLoginResponse = { success: false, message: 'Login failed' };
        login.mockResolvedValue(mockLoginResponse);
        const form = wrapper.find('form');

        await form.trigger('submit');
        await flushPromises();

        expect(login).toHaveBeenCalled();
        expect(mockRouter.push).not.toHaveBeenCalled();
    });
});