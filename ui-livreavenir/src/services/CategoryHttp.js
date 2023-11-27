import {http} from './axios';
export class CategoryHttp {
    
    async get_list_categories() {
        const url = `/categories`;
        const response = await http.get(url);
        return response;
    };

}
