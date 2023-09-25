package dev.sushant.productservice.Services;

import dev.sushant.productservice.dtos.FakeStoreProductDTO;
import dev.sushant.productservice.dtos.GenericProductDTO;
import dev.sushant.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    private String specificProductUrl="https://fakestoreapi.com/products/{id}";
    private String allProductsUrl="https://fakestoreapi.com/products";


    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
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
        RestTemplate restTemplate=restTemplateBuilder.build();
    ResponseEntity<GenericProductDTO> response=restTemplate.postForEntity(allProductsUrl,product,GenericProductDTO.class);
        return response.getBody();
    }

    @Override
    public GenericProductDTO getProductyId(Long id) throws Exception {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response=restTemplate.getForEntity(specificProductUrl, FakeStoreProductDTO.class,id);

        FakeStoreProductDTO fakeStoreProductDTO=response.getBody();

        if(fakeStoreProductDTO==null){
            throw new Exception("Product with id "+ id+" doesn't exists");
        }

//                response.getStatusCode();
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDTO);
//        return null;
    }


    @Override
    public List<GenericProductDTO> getAllProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        // This can work as well.
        //        ResponseEntity<List> response=restTemplate.getForEntity(getAllProductsUrl,List.class);
//         List<GenericProductDTO> genericProductDTOS= response.getBody();

        ResponseEntity<FakeStoreProductDTO[]> response=
                restTemplate.getForEntity(allProductsUrl,FakeStoreProductDTO[].class);

        List<GenericProductDTO> answer=new ArrayList<>();

        for(FakeStoreProductDTO fakeStoreProductDTO: Arrays.stream(response.getBody()).toList()){

            answer.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProductDTO));
        }
        return answer;
    }

    @Override
    public GenericProductDTO updateProductById(Long id,GenericProductDTO product) {
        RestTemplate restTemplate=restTemplateBuilder.build();


        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);

        ResponseEntity<FakeStoreProductDTO> response= restTemplate.execute(specificProductUrl, HttpMethod.PUT, requestCallback, responseExtractor, id,product);

        FakeStoreProductDTO fakeStoreProductDTO=response.getBody();

        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDTO);
    }

    @Override
    public GenericProductDTO deteleProduct(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);

        ResponseEntity<FakeStoreProductDTO> response= restTemplate.execute(specificProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        FakeStoreProductDTO  fakeStoreProductDTO= response.getBody();



        return  convertFakeStoreProductIntoGenericProduct(fakeStoreProductDTO);
    }


}
