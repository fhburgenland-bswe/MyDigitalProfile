import { describe, it, expect, vi, beforeEach } from 'vitest';
import * as userService from '@/services/user.service';

// Mock axios methods
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
        localStorage.setItem('username', 'test@user.de');
        userService.logout();

        expect(localStorage.getItem('username')).toBeNull();
    });
});
