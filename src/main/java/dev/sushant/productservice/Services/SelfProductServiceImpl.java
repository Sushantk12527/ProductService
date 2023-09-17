package dev.sushant.productservice.Services;

import dev.sushant.productservice.dtos.GenericProductDTO;
import dev.sushant.productservice.models.Product;
import org.springframework.stereotype.Service;

@Service("SelfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{

    @Override
    public GenericProductDTO getProductyId(Long id) {
        return null;
    }
}
