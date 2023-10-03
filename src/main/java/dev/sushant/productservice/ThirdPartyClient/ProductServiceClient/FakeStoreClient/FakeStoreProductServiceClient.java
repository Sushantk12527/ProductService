package dev.sushant.productservice.ThirdPartyClient.ProductServiceClient.FakeStoreClient;

import dev.sushant.productservice.dtos.GenericProductDTO;
import dev.sushant.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductServiceClient  {


    @Value("${fakestore.Api.Url}")
     String fakestoreApiUrl;
    @Value("${fakestore.Api.Paths.Product}")
     String fakeStoreProductsApiPath;

//    private String specificProductUrl=fakestoreApiUrl+fakeStoreProductsApiPath+"/{id}";
//    private String allProductsUrl=fakestoreApiUrl+fakeStoreProductsApiPath;

    //    private String allProductsUrl="https://fakestoreapi.com/products";
    //    private String specificProductUrl="https://fakestoreapi.com/products/{id}";

    private String allProductsUrl;
    private String specificProductUrl;

        private RestTemplateBuilder restTemplateBuilder;


    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder,@Value("${fakestore.Api.Url}") String allProductsUrl,@Value("${fakestore.Api.Paths.Product}") String specificProductUrl) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.allProductsUrl=allProductsUrl+specificProductUrl;
        this.specificProductUrl=allProductsUrl+specificProductUrl+"/{id}";
    }




    public FakeStoreProductDTO createProduct(GenericProductDTO product) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response=restTemplate.postForEntity(allProductsUrl,product,FakeStoreProductDTO.class);
        return response.getBody();
    }

    public FakeStoreProductDTO getProductyId(Long id) throws Exception {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response=restTemplate.getForEntity(specificProductUrl, FakeStoreProductDTO.class,id);

        FakeStoreProductDTO fakeStoreProductDTO=response.getBody();

        if(fakeStoreProductDTO==null){
            throw new NotFoundException("Product with id "+ id+" doesn't exists");
        }

        return fakeStoreProductDTO;

    }


    public List<FakeStoreProductDTO> getAllProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        // This can work as well.
        //        ResponseEntity<List> response=restTemplate.getForEntity(getAllProductsUrl,List.class);
//         List<GenericProductDTO> genericProductDTOS= response.getBody();

        ResponseEntity<FakeStoreProductDTO[]> response=
                restTemplate.getForEntity(allProductsUrl,FakeStoreProductDTO[].class);

        return Arrays.stream(response.getBody()).toList();
    }


    public FakeStoreProductDTO updateProductById(Long id, GenericProductDTO product) {
        RestTemplate restTemplate=restTemplateBuilder.build();


        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);

        ResponseEntity<FakeStoreProductDTO> response= restTemplate.execute(specificProductUrl, HttpMethod.PUT, requestCallback, responseExtractor, id,product);

//        FakeStoreProductDTO fakeStoreProductDTO=response.getBody();

        return response.getBody();
    }


    public FakeStoreProductDTO deteleProduct(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);

        ResponseEntity<FakeStoreProductDTO> response= restTemplate.execute(specificProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        FakeStoreProductDTO  fakeStoreProductDTO= response.getBody();



        return  fakeStoreProductDTO;
    }
}
