package dev.sushant.productservice.Services;

import dev.sushant.productservice.dtos.GenericProductDTO;
import dev.sushant.productservice.models.Product;

public interface ProductService {

    GenericProductDTO getProductyId(Long id);
//    List<Product> getAllProducts();

}
