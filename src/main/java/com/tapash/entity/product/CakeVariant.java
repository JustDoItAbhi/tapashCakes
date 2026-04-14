package com.tapash.entity.product;

import com.tapash.entity.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Negative;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "variant")
public class CakeVariant extends BaseModel {
    private double weight;
    private double size;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "product_cake_id")
    private ProductCake productCake;

}
