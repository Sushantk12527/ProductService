package dev.sushant.productservice.Services;

import dev.sushant.productservice.ThirdPartyClient.ProductServiceClient.FakeStoreClient.FakeStoreProductDTO;
import dev.sushant.productservice.ThirdPartyClient.ProductServiceClient.FakeStoreClient.FakeStoreProductServiceClient;
import dev.sushant.productservice.dtos.GenericProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{


    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    @Autowired
    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    private GenericProductDTO convertFakeStoreProductIntoGenericProduct(FakeStoreProductDTO fakeStoreProductDTO){

        GenericProductDTO product = new GenericProductDTO();
        product.setId(fakeStoreProductDTO.getId());
        product.setImage(fakeStoreProductDTO.getImage());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setCategory(fakeStoreProductDTO.getCategory());

        return product;
    }


    @Override
    public GenericProductDTO createProduct(GenericProductDTO product){
       return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.createProduct(product)) ;
    }

    @Override
    public GenericProductDTO getProductyId(Long id) throws Exception {

        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.getProductyId(id)) ;
    }


    @Override
    public List<GenericProductDTO> getAllProducts() {

        List<GenericProductDTO> genericProductDTOS=new ArrayList<>();

        for(FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProductServiceClient.getAllProducts()){

            genericProductDTOS.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProductDTO));
        }
       return genericProductDTOS;
    }

    @Override
    public GenericProductDTO updateProductById(Long id,GenericProductDTO product) {
       return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.updateProductById(id,product));
    }

    @Override
    public GenericProductDTO deteleProduct(Long id) {
       return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.deteleProduct(id)) ;
    }


}
