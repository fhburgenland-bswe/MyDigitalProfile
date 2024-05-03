import { describe, it, expect, vi, beforeEach } from 'vitest';
import * as userService from '@/services/user.service';
import axios from 'axios';

vi.mock('axios', () => ({
    get: vi.fn(),
    patch: vi.fn()
}));

describe('user.service tests', () => {
    beforeEach(() => {
        vi.resetAllMocks();
        localStorage.clear();
    });


    it('logout should remove userId from localStorage', () => {
        localStorage.setItem('userId', '123');
        userService.logout();

        expect(localStorage.getItem('userId')).toBeNull();
    });
});
