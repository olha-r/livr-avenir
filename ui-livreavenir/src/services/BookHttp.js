import {http} from './common-http';

export class BookHttp {
    
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
