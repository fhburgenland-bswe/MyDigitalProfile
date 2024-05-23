import { describe, it, expect, vi, beforeEach, afterEach } from 'vitest';
import { mount, flushPromises } from '@vue/test-utils';
import CreateUserModal from '@/components/Modals/CreateUserModal.vue';
import * as userService from '@/services/user.service';

vi.mock('@/services/user.service');

describe('CreateUserModal.vue', () => {
    let wrapper;

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


});
