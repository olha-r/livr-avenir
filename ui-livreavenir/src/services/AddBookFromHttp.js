import {http} from './http-common';

export class AddBookFormHttp {
    
    async get_list_languages() {
        const url = `/languages`;
        const response = await http.get(url);
        return response;
    };
    async get_list_categories() {
        const url = `/categories`;
        const response = await http.get(url);
        return response;
    };


}
