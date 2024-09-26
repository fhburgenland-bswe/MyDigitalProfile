import { describe, it, expect, vi, beforeEach, afterEach, Mock } from 'vitest';
import { mount, flushPromises } from '@vue/test-utils';
import LoginPage from '@/components/LoginPage/LoginPage.vue';
import { login } from '@/services/login.service';
import { createRouter, createMemoryHistory } from 'vue-router'; // Importiere die notwendigen Funktionen

// Mock the login service
vi.mock('@/services/login.service', () => ({
    login: vi.fn(),
}));

// Mock the toast (entferne diesen Teil, wenn toast nicht verwendet wird)
// vi.mock("vue3-toastify", () => ({
//     toast: vi.fn()
// }));

// Mock routes (Beispielrouten definieren, falls sie benötigt werden)
const routes = [
    { path: '/', component: {} },
    { path: '/Main', component: {} }
];

// Router initialisieren
const router = createRouter({
    history: createMemoryHistory(),
    routes,
});

describe('LoginPage.vue', () => {
    let wrapper: ReturnType<typeof mount>;

    beforeEach(() => {
        (login as Mock).mockClear();
        router.push = vi.fn(); // Mock für router.push
        wrapper = mount(LoginPage, {
            global: {
                plugins: [router], // Router als Plugin hinzufügen
            }
        });
    });

    afterEach(() => {
        wrapper.unmount();
    });

    it('should handle login with correct email and password successfully', async () => {
        const mockLoginResponse = { success: true, message: 'Login successful', userId: 1 };
        (login as Mock).mockResolvedValue(mockLoginResponse);
        const emailInput = wrapper.find('input[name="email"]');
        const passwordInput = wrapper.find('input[name="password"]');
        const form = wrapper.find('form');

        await emailInput.setValue('test@example.com');
        await passwordInput.setValue('password');
        await form.trigger('submit');
        await flushPromises();

        expect(login).toHaveBeenCalledWith('test@example.com', 'password');
        expect(router.push).toHaveBeenCalledWith('/Main'); // Angepasste Router-Verwendung
    });

    it('should handle login failure with incorrect email and password', async () => {
        const mockLoginResponse = { success: false, message: 'Login failed' };
        (login as Mock).mockResolvedValue(mockLoginResponse);
        const form = wrapper.find('form');

        await form.trigger('submit');
        await flushPromises();

        expect(login).toHaveBeenCalled();
        expect(router.push).not.toHaveBeenCalled();
    });
});
