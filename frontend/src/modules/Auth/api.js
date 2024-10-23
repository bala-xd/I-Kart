import axios from 'axios';
import auth from '.';

const api = axios.create({
    baseURL: '',
});

api.interceptors.request.use(
    (config) => {
        const token = auth.getToken();
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => Promise.reject(error)
);

export default api;