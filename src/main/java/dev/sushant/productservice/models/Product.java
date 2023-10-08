package dev.sushant.productservice.models;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private double price;
    private String image;

    @ManyToOne
    private Category category;


}
