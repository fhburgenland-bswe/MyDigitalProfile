import axios from 'axios';

const BASE_URL = 'http://localhost:8080'; // URL of the Spring Boot Backend

export const login = async (email: string, password: string): Promise<{
    success: boolean;
    message: string;
    userId: any;
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
            // Mocking userId and username for the sake of this example
            const userId = 1; // Replace this with actual logic to obtain userId
            const username = email; // Assuming username is the email used for login
            return { success: true, message: "Login successful", userId: userId, username: username };
        } else {
            console.error('Login failed: Invalid response status');
            return { success: false, message: "Login failed", userId: null, username: '' };
        }
    } catch (error) {
        console.error('Error during login:', error);
        return { success: false, message: "Login failed", userId: null, username: '' };
    }
};
