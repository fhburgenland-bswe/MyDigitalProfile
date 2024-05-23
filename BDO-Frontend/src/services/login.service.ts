import axios from 'axios';

const BASE_URL = 'http://localhost:8080'; // URL of the Spring Boot Backend

export const login = async (email: string, password: string): Promise<{
    success: boolean;
    message: string;
    username: string;
}> => {
    try {
        const response = await axios.post(`${BASE_URL}/login`, { username: email, password }, {
            headers: {
                'Content-Type': 'application/json'
            },
            withCredentials: true
        });

        console.log('Response from backend:', response);

        if (response.status === 200) {
            const username = email;
            return { success: true, message: "Login successful", username: username };
        } else {
            console.error('Login failed: Invalid response status');
            return { success: false, message: "Login failed", username: '' };
        }
    } catch (error) {
        console.error('Error during login:', error);
        return { success: false, message: "Login failed", username: '' };
    }
};
