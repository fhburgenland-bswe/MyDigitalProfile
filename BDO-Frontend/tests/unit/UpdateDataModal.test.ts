import { describe, it, expect, vi, beforeEach, afterEach } from 'vitest';
import { mount, VueWrapper } from '@vue/test-utils';
import UpdateDataModal from '@/components/Modals/UpdateDataModal.vue';

// Beispiel für Verwendung von `vi`
vi.mock('@/services/user.service', () => ({
    updateUserData: vi.fn()
}));

describe('UpdateDataModal.vue', () => {
    let wrapper: VueWrapper<any>; // Typisiere den Wrapper korrekt

    beforeEach(() => {
        wrapper = mount(UpdateDataModal, {
            props: {
                isVisible: true,
                field: 'ort'
            }
        });
    });

    afterEach(() => {
        wrapper.unmount();
    });

    it('renders modal when isVisible is true and field is set', () => {
        expect(wrapper.isVisible()).toBe(true);
        expect(wrapper.find('label').text()).toContain('Neuer Wert für Ort');
    });

    it('validates input and emits update on submission', async () => {
        wrapper.find('input').setValue('Berlin');
        await wrapper.find('form').trigger('submit.prevent');
        expect(wrapper.emitted('update')).toBeTruthy();
    });

    it('shows error message for invalid input', async () => {
        wrapper.find('input').setValue('123'); // Invalid for 'ort'
        await wrapper.find('form').trigger('submit.prevent');
        expect(wrapper.text()).toContain('Der Ort darf nur Buchstaben enthalten.');
    });
});
