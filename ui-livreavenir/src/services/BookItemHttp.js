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

    async get_items_by_book_id(book_id) {
        const url = `/book_items/${book_id}`;
        const response = await http.get(url);
        return response;
    };

    async get_items_by_user() {
        const headers ={
            //  Authorization: `Bearer ${token}`
            };
        const url = `/book_items/user`;
        const response = await http.get(url, {headers});
        return response;
    };
    

}
