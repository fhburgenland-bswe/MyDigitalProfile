import axios from 'axios';

const BASE_URL = 'http://localhost:3001';// URL of the JSON Server. start the server with "json-server --watch db.json --port 3001"


export const login = async (email: string, password: string): Promise<{
    success: boolean;
    message: string;
    userId: any
}> => {
    try {
        const response = await axios.get(`${BASE_URL}/users?email=${email}`);


        if (Array.isArray(response.data) && response.data.length > 0) {
            const user = response.data[0];

            if (user.password === password) {
                return { success: true, message: "Login successful", userId: user.id };

            } else {
                return { success: false, message: "Login failed", userId: null};
            }
        } else {
            return { success: false, message: "Login failed", userId: null};
        }
    } catch (error) {
        console.error('Error during login:', error);
        throw error;
    }
};



