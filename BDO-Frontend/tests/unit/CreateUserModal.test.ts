import { describe, it, expect, vi, beforeEach, afterEach } from 'vitest';
import { mount, flushPromises, VueWrapper } from '@vue/test-utils';
import CreateUserModal from '@/components/Modals/CreateUserModal.vue';
import * as userService from '@/services/user.service';

// Mocking the userService module
vi.mock('@/services/user.service');

describe('CreateUserModal.vue', () => {
    // Typisierung der wrapper-Variable mit VueWrapper und der spezifischen Komponente
    let wrapper: VueWrapper<InstanceType<typeof CreateUserModal>>;

    beforeEach(() => {
        wrapper = mount(CreateUserModal, {
            props: {
                isVisible: true
            }
        });
    });

    afterEach(() => {
        wrapper.unmount();
    });

    it('renders modal when isVisible is true', () => {
        expect(wrapper.isVisible()).toBe(true);
    });

    it('closes modal on cancel', async () => {
        await wrapper.find('.cancel-btn').trigger('click');
        expect(wrapper.emitted('close')).toHaveLength(1);
    });

    it('calls userService createUser on form submit', async () => {
        // Mock-Implementierung der createUser-Funktion von userService
        const mockCreateUser = vi.spyOn(userService, 'createUser').mockResolvedValue({ success: true });

        // Simuliert das Ausfüllen des Formulars
        await wrapper.find('input[name="username"]').setValue('TestUser');
        await wrapper.find('input[name="email"]').setValue('test@example.com');
        await wrapper.find('form').trigger('submit.prevent'); // Verhindert das Standardverhalten des Formulars

        await flushPromises(); // Wartet auf alle asynchronen Operationen

        // Überprüft, ob createUser mit den richtigen Parametern aufgerufen wurde
        expect(mockCreateUser).toHaveBeenCalledWith({
            username: 'TestUser',
            email: 'test@example.com',
        });

        // Überprüft, ob das Modal geschlossen wurde
        expect(wrapper.emitted('close')).toHaveLength(1);

        mockCreateUser.mockRestore(); // Stellt die Original-Implementierung wieder her
    });
});
