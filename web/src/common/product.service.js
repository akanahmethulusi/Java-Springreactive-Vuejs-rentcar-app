import { get } from './api.service';
import { post } from './api.service';


export function getProductDetailById(id){
    return get(`products/` + id)
}


export function getAll(){
    return get('products')
}

export const searchProducts =(searchRequest) => {
    //return post('/products/search', searchRequest)
    return post('http://localhost:8080/products/search', searchRequest)
}

/*
export function searchProducts(searchRequest){
    return post('/products/search', searchRequest)
}
*/