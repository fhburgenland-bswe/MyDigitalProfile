import axios from 'axios';

const BASE_URL = 'http://localhost:8080'; // URL of the Spring Boot Backend

export const getUserData = async (): Promise<any> => {
    const userId = localStorage.getItem('userId');
    const username = localStorage.getItem('username');
    console.log('Attempting to fetch data for user ID:', userId, 'and username:', username);

    if (!userId || !username) {
        throw new Error('User ID or username is missing');
    }

    try {
        const response = await axios.get(`${BASE_URL}/api/user/${username}/user/${userId}`, {
            withCredentials: true // Add this line to include credentials
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching user data:', error);
        throw error;
    }
};

export const updateUserData = async (userId: string, data: any): Promise<any> => {
    try {
        const username = localStorage.getItem('username'); // Get username from storage
        const response = await axios.put(`${BASE_URL}/api/user/${username}`, data, {
            withCredentials: true // Add this line to include credentials
        });
        return response.data;
    } catch (error) {
        console.error('Error updating user data:', error);
        throw error;
    }
};

export const createUser = async (userData: any): Promise<any> => {
    try {
        const response = await axios.post(`${BASE_URL}/api/users`, userData, {
            withCredentials: true // Add this line to include credentials
        });
        return response.data;
    } catch (error) {
        console.error('Error creating user:', error);
        throw error;
    }
};

export const logout = (): void => {
    localStorage.removeItem('userId');
    localStorage.removeItem('username');
    console.log('User logged out and session cleared');
};
