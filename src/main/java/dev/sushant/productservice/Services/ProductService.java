package dev.sushant.productservice.Services;

import dev.sushant.productservice.ThirdPartyClient.ProductServiceClient.FakeStoreClient.FakeStoreProductDTO;
import dev.sushant.productservice.dtos.GenericProductDTO;
import dev.sushant.productservice.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public interface ProductService {

    GenericProductDTO createProduct(GenericProductDTO product);

    GenericProductDTO getProductyId(Long id) throws Exception;
    List<GenericProductDTO> getAllProducts();

    GenericProductDTO updateProductById(Long id,GenericProductDTO product);

    GenericProductDTO deteleProduct(Long id);

    List<String> getAllCategories();

    List<GenericProductDTO> getByCategory(String category);
}
