package dev.sushant.productservice.Controllers;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public void getAllProducts(){

    }

    @GetMapping("{id}")
    public String getProductById(@PathVariable("id") Long id){
        return "Here is product id: " +id;
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
