import {http} from './http-common';

export class BookHttp {
    
    async add_new_book(payload, token) {
        const url = `/books`;
        const headers ={
              Authorization: `Bearer ${token}`
            };
        headers['Content-Type'] = 'multipart/form-data';
        const response = await http.post(url, payload, {headers});
        return response;
    };
    async get_last_added_books() {
        const url = `/books`;
        const response = await http.get(url);
        return response;
    };
    async get_book_details(book_id) {
        const url = `/books/${book_id}/detail`;
        const response = await http.get(url);
        return response;
    };
    async delete_book(book_id, token) {
        const url = `/books/${book_id}`;
        const headers= {
              Authorization: `Bearer ${token}`
            };
        const response = await http.delete(url, {headers});
        return response;
    };

}
