import axios from 'axios';

const BASE_URL = `http://localhost:8080`;
const HEADERS = [{
            'Accept': 'application/json',
            // 'Authorization': 'Bearer {token}'
          }];
const ACCEPTED_STATUS = [200, 201, 202, 204, 400];

export const http = axios.create({
        baseURL: BASE_URL,
        validateStatus: (status) => {
            return ACCEPTED_STATUS.includes(status);
        }
    });
    http.interceptors.response.use((response) => {
        const status = response.status;
        const data = response.data;
        const body = data != '' ? data : null;
        return { status: status, body: body };
    }, (error) => {
        return Promise.reject(error);
    });

