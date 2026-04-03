package com.tapash.entity.product;

import com.tapash.entity.BaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rating")
public class Rating extends BaseModel {
    private int stars;
    private String comment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_cake_id")
    private ProductCake productCake;
}
