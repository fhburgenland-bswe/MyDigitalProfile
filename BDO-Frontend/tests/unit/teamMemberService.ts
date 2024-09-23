import axios from 'axios';
import { getTeamMembers, getTeamMemberById, updateTeamMember } from '@/services/teamMemberService';

jest.mock('axios');

describe('teamMemberService', () => {
    it('fetches team members correctly', async () => {
        const teamMembers = [{ id: 1, name: 'John Doe' }];
        (axios.get as jest.Mock).mockResolvedValue({ data: teamMembers });

        const result = await getTeamMembers();
        expect(result).toEqual(teamMembers);
    });

    it('fetches team member by id correctly', async () => {
        const teamMember = { id: 1, name: 'John Doe' };
        (axios.get as jest.Mock).mockResolvedValue({ data: teamMember });

        const result = await getTeamMemberById(1);
        expect(result).toEqual(teamMember);
    });

    it('updates team member correctly', async () => {
        const updatedTeamMember = { id: 1, name: 'John Doe' };
        (axios.put as jest.Mock).mockResolvedValue({ data: updatedTeamMember });

        const result = await updateTeamMember(1, updatedTeamMember);
        expect(result).toEqual(updatedTeamMember);
    });
});