package com.tapash.entity.category;

import com.tapash.entity.BaseModel;
import com.tapash.entity.product.ProductCake;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class CakeCategory extends BaseModel {
    @Column(name = "category_name",nullable = false,length = 100)
    private String categoryName;
    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private CategoryStatus status;
    @ManyToMany(mappedBy = "categories")
    private Set<ProductCake> products = new HashSet<>();
}
