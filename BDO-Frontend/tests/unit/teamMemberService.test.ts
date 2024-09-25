import axios from 'axios';
import { getTeamMembers, getTeamMemberById, updateTeamMember } from '../services/teamMemberService';

jest.mock('axios');
const mockedAxios = axios as jest.Mocked<typeof axios>;

describe('teamMemberService', () => {
    afterEach(() => {
        jest.clearAllMocks();
    });

    it('should fetch team members', async () => {
        const data = [{ id: 1, name: 'John Doe' }];
        mockedAxios.get.mockResolvedValue({ data });

        const result = await getTeamMembers();
        expect(result).toEqual(data);
        expect(mockedAxios.get).toHaveBeenCalledWith('http://localhost:8080/api/team-members', { withCredentials: true });
    });

    it('should fetch a team member by ID', async () => {
        const data = { id: 1, name: 'John Doe' };
        mockedAxios.get.mockResolvedValue({ data });

        const result = await getTeamMemberById(1);
        expect(result).toEqual(data);
        expect(mockedAxios.get).toHaveBeenCalledWith('http://localhost:8080/api/team-members/1', { withCredentials: true });
    });

    it('should update a team member', async () => {
        const data = { id: 1, name: 'John Doe' };
        mockedAxios.put.mockResolvedValue({ data });

        const result = await updateTeamMember(1, data);
        expect(result).toEqual(data);
        expect(mockedAxios.put).toHaveBeenCalledWith('http://localhost:8080/api/team-members/1', data, { withCredentials: true });
    });

    it('should handle errors when fetching team members', async () => {
        mockedAxios.get.mockRejectedValue(new Error('Network Error'));

        await expect(getTeamMembers()).rejects.toThrow('Network Error');
    });

    it('should handle errors when fetching a team member by ID', async () => {
        mockedAxios.get.mockRejectedValue(new Error('Network Error'));

        await expect(getTeamMemberById(1)).rejects.toThrow('Network Error');
    });

    it('should handle errors when updating a team member', async () => {
        mockedAxios.put.mockRejectedValue(new Error('Network Error'));

        await expect(updateTeamMember(1, {})).rejects.toThrow('Network Error');
    });
});