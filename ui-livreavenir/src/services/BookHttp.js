import {http} from './http-common';

export class BookHttp {
    
    async add_new_book(payload, token) {
        const url = `/books`;
        const config = {
            headers: {
              'Content-Type': 'multipart/form-data',
              Authorization: `Bearer ${token}`
            }
          };
        const response = await http.post(url, payload, config);
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

}
