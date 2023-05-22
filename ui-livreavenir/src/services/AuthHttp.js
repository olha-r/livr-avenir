import {http} from './common-http';

export class AuthHttp {
    
    async register(payload) {
        const url = `/auth/sign-up`;
        const response = await http.post(url, payload);
        return response;
    };

    async login (payload){
        const url = `/auth/sign-in`;
        const response = await http.post(url, payload);
        return response;
    }

}
