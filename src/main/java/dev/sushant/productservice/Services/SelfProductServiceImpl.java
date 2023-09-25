package dev.sushant.productservice.Services;

import dev.sushant.productservice.dtos.GenericProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{

    @Override
    public GenericProductDTO createProduct(GenericProductDTO product) {
        return null;
    }

    @Override
    public GenericProductDTO getProductyId(Long id) {
        return null;
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDTO updateProductById(Long id,GenericProductDTO product) {
        return null;
    }

    @Override
    public GenericProductDTO deteleProduct(Long id) {
        return null;
    }
}
