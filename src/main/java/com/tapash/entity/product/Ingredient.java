package com.tapash.entity.product;

import com.tapash.entity.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ingredient")
public class Ingredient extends BaseModel {
    private String name;
    private String type;
    private double priceOfIngredient;
}
