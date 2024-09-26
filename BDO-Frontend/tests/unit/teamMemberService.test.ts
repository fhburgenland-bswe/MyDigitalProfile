import { describe, it, expect, vi, afterEach, Mock } from 'vitest';
import { getTeamMembers, getTeamMemberById, updateTeamMember } from '@/services/teamMemberService';

// Set up fetch mock
vi.stubGlobal('fetch', vi.fn());

describe('teamMemberService', () => {
    afterEach(() => {
        vi.clearAllMocks();
    });

    it('should fetch team members', async () => {
        const data = [{ id: 1, name: 'John Doe' }];
        (fetch as Mock).mockResolvedValue({
            ok: true,
            json: async () => data,
        });

        const result = await getTeamMembers();
        expect(result).toEqual(data);
        expect(fetch).toHaveBeenCalledWith('http://localhost:8080/api/team-members', { credentials: 'include' });
    });

    it('should fetch a team member by ID', async () => {
        const data = { id: 1, name: 'John Doe' };
        (fetch as Mock).mockResolvedValue({
            ok: true,
            json: async () => data,
        });

        const result = await getTeamMemberById(1);
        expect(result).toEqual(data);
        expect(fetch).toHaveBeenCalledWith('http://localhost:8080/api/team-members/1', { credentials: 'include' });
    });

    it('should update a team member', async () => {
        const data = { id: 1, name: 'John Doe' };
        (fetch as Mock).mockResolvedValue({
            ok: true,
            json: async () => data,
        });

        const result = await updateTeamMember(1, data);
        expect(result).toEqual(data);
        expect(fetch).toHaveBeenCalledWith('http://localhost:8080/api/team-members/1', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            credentials: 'include',
            body: JSON.stringify(data),
        });
    });

    it('should handle errors when fetching team members', async () => {
        (fetch as Mock).mockResolvedValue({
            ok: false,
        });

        await expect(getTeamMembers()).rejects.toThrow('Network response was not ok');
    });

    it('should handle errors when fetching a team member by ID', async () => {
        (fetch as Mock).mockResolvedValue({
            ok: false,
        });

        await expect(getTeamMemberById(1)).rejects.toThrow('Network response was not ok');
    });

    it('should handle errors when updating a team member', async () => {
        (fetch as Mock).mockResolvedValue({
            ok: false,
        });

        await expect(updateTeamMember(1, {})).rejects.toThrow('Network response was not ok');
    });
});
