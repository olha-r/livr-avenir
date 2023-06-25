import {http} from './http-common';

export class BookHttp {
    
    async add_new_book(payload, token) {
        const url = `/books`;
        const headers = {
            'Accept': 'application/json',
            'Authorization': `Bearer ${token}`,
            'Content-Type': `multipart/form-data`
          };
        console.log("HEADERS",headers);
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

}
