import axios from 'axios';

const BASE_URL = 'http://localhost:8080'; // URL of the Spring Boot Backend

export const getUserData = async (): Promise<any> => {
    const username = localStorage.getItem('username');
    console.log('Attempting to fetch data for username:', username);

    if (!username) {
        throw new Error('Username is missing');
    }

    try {
        const response = await axios.get(`${BASE_URL}/api/user/${username}/user`, {
            withCredentials: true
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching user data:', error);
        throw error;
    }
};

export const updateUserData = async (username: string, data: any): Promise<any> => {
    try {
        const response = await axios.put(`${BASE_URL}/api/user/${username}`, data, {
            withCredentials: true
        });
        return response.data;
    } catch (error) {
        console.error('Error updating user data:', error);
        throw error;
    }
};

export const createUser = async (userData: any): Promise<any> => {
    console.log('Submitting new user:', JSON.stringify(userData));
    try {
        const response = await axios.post(`${BASE_URL}/api/register`, userData, {
            withCredentials: true
        });
        return response.data;
    } catch (error) {
        console.error('Error creating user:', error);
        throw error;
    }
};

export const logout = (): void => {
    localStorage.removeItem('username');
    console.log('User logged out and session cleared');
};
