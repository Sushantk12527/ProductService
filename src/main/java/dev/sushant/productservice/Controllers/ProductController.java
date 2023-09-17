package dev.sushant.productservice.Controllers;

import dev.sushant.productservice.Services.ProductService;
import dev.sushant.productservice.dtos.GenericProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {


    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier("FakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public void getAllProducts(){

    }

    @GetMapping("{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id){

        return productService.getProductyId((id));
    }

    @PostMapping
    public String createProduct(){
            return "Created a random product with Id: "+ UUID.randomUUID();

    }

    @PutMapping("{id}")
    public void updateProductById(){

    }

    @DeleteMapping("{id}")
    public void deleteProductById(){

    }
}
