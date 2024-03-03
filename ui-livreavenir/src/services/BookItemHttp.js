import {http} from './axios';

export class BookItemHttp {
    
    async add_new_item(payload, token) {
        const url = `/book_items`;
        const headers ={
            //   Authorization: `Bearer ${token}`
            };
        const response = await http.post(url, payload, {headers});
        return response;
    };
    

}
