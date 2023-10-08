package dev.sushant.productservice.InheritanceDemo.MappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.EnableMBeanExport;

@Getter
@Setter
@Entity(name="ms_mentor")
public class Mentor extends User{
    private double averageRating;
}
