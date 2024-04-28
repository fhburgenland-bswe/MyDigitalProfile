import { describe, it, expect, vi, beforeEach, afterEach } from 'vitest';
import { mount, flushPromises } from '@vue/test-utils';
import LoginPage from '@/components/LoginPage/LoginPage.vue';
import { login } from '@/services/login.service';
import { useRouter } from 'vue-router';
import { toast } from "vue3-toastify";

// Mock the login service
vi.mock('@/services/login.service', () => ({
    login: vi.fn(),
}));

// Create mock router instance
const mockRouter = {
    push: vi.fn()
};

// Mock "useRouter" to return the mock router instance
vi.mock('vue-router', () => ({
    useRouter: () => mockRouter,
}));

// Mock the toast
vi.mock('vue3-toastify', () => ({
    toast: vi.fn(),
}));

describe('LoginPage.vue', () => {
    let wrapper;

    beforeEach(() => {
        // Reset mock calls before each test
        mockRouter.push.mockClear();
        login.mockClear();
        toast.mockClear();

        // Mount the component with global mocks
        wrapper = mount(LoginPage, {
            global: {
                plugins: [useRouter],
                stubs: ['router-link', 'router-view']
            }
        });
    });

    afterEach(() => {
        wrapper.unmount();
    });



    /**
     * Tests successful login handling. It submits the form with valid credentials,
     * checks the login function's call, and verifies navigation to the main page.
     */
    it('should handle login success', async () => {
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


    /**
     * Tests handling of login failure. It submits the form with incorrect credentials,
     * ensures the login service is called, and checks for the appropriate error toast.
     */
    it('should handle login failure', async () => {
        const mockLoginResponse = { success: false, message: 'Login failed' };
        login.mockResolvedValue(mockLoginResponse);
        const form = wrapper.find('form');

        await form.trigger('submit');

        await flushPromises();

        expect(login).toHaveBeenCalled();
        expect(toast).toHaveBeenCalledWith('E-Mail oder Passwort falsch', {
            theme: 'colored',
            type: 'error',
            position: 'bottom-center',
            dangerouslyHTMLString: true,
        });
    });
});
