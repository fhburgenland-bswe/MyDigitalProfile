import axios from 'axios';

export async function getTeamMembers() {
    const response = await axios.get('/api/team-members');
    return response.data;
}

export async function getTeamMemberById(id: number) {
    const response = await axios.get(`/api/team-members/${id}`);
    return response.data;
}

export async function updateTeamMember(id: number, teamMember: any) {
    const response = await axios.put(`/api/team-members/${id}`, teamMember);
    return response.data;
}