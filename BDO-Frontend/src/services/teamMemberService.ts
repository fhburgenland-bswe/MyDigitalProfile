import axios from 'axios';

const BASE_URL = 'http://localhost:8080'; // URL of the Spring Boot Backend

export async function getTeamMembers() {
    try {
        const response = await axios.get(`${BASE_URL}/api/team-members`, {
            withCredentials: true
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching team members:', error);
        throw error;
    }
}

export async function getTeamMemberById(id: number) {
    try {
        const response = await axios.get(`${BASE_URL}/api/team-members/${id}`, {
            withCredentials: true
        });
        return response.data;
    } catch (error) {
        console.error(`Error fetching team member with ID ${id}:`, error);
        throw error;
    }
}

export async function updateTeamMember(id: number, teamMember: any) {
    try {
        const response = await axios.put(`${BASE_URL}/api/team-members/${id}`, teamMember, {
            withCredentials: true
        });
        return response.data;
    } catch (error) {
        console.error(`Error updating team member with ID ${id}:`, error);
        throw error;
    }
}