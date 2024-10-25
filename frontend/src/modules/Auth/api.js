import axios from 'axios';
import auth from '.';

const api = axios.create({
    baseURL: '',
});

api.interceptors.request.use(
    (config) => {
        const token = auth.getToken();
        if (token.length>0) {
            config.headers.Authorization = `Bearer ${JSON.parse(token)}`;
        }
        return config;
    },
    (error) => {
        Promise.reject(error)
    }
);

export default api;