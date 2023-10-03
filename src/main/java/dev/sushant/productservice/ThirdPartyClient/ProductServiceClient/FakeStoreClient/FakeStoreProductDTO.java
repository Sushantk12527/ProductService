package dev.sushant.productservice.ThirdPartyClient.ProductServiceClient.FakeStoreClient;

import dev.sushant.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
