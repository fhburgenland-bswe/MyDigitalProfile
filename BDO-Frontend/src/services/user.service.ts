import axios from 'axios';

const BASE_URL = 'http://localhost:3001'; // URL of the JSON Server. start the server with "json-server --watch db.json --port 3001"

export const getUserData = async (): Promise<any> => {
    const userId = localStorage.getItem('userId'); // Get user ID from storage
    console.log('Attempting to fetch data for user ID:', userId);
    try {
        const response = await axios.get(`${BASE_URL}/users/${userId}`);

        return response.data;
    } catch (error) {
        console.error('Error fetching user data:', error);
        throw error;
    }
};
