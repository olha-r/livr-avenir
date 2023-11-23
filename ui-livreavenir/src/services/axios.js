import axios from 'axios';
import { useGlobalStore } from '../stores/global-errors-store';

const BASE_URL = import.meta.env.VITE_API_BASE_URL;
// const HEADERS = [{
//             'Accept': 'application/json',
//             'Authorization': 'Bearer {token}'
//           }];
const ACCEPTED_STATUS = [200, 201, 202, 204, 400];

const http = axios.create({
        baseURL: BASE_URL,
        validateStatus: (status) => {
            return ACCEPTED_STATUS.includes(status);
        },
        // HEADERS
    });

    http.interceptors.request.use(
    (config) => {
      const token = "";
      if (token) {
        config.headers.Authorization = `Bearer ${token}`;
      }
      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
  );

    http.interceptors.response.use((response) => {
        const status = response.status;
        const data = response.data;
        const body = data != '' ? data : null;
        return { status: status, body: body };
        },
        (error) => {
          // Handle response error
          const store = useGlobalStore();
          if (error.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
            store.setError(error.response.data.message || 'An error occurred');
          } else if (error.request) {
            // The request was made but no response was received
            store.setError('No response from the server');
          } else {
            // Something happened in setting up the request that triggered an Error
            store.setError('An error occurred');
          }
          return Promise.reject(error);
        }
      );
   
      export {http};

    