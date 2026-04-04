package com.tapash.entity.product;

import com.tapash.entity.BaseModel;
import com.tapash.entity.category.CakeCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "product_cake")
public class ProductCake extends BaseModel {
    private String cakeName;
    private BigDecimal price;
    private int stock;
    @CurrentTimestamp
    private LocalDateTime updatedAt;
    @Enumerated(EnumType.STRING)
    private CakeAvailableEnum isAvailable;
    private String description;
    private int preparationTimeMinutes;
    private int shelfLifeHours;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> imageUrls;
    @Enumerated(EnumType.STRING)
    private CakeType cakeType;

    @Embedded
    private Dietary dietary;


    @OneToMany(mappedBy = "productCake",cascade = CascadeType.ALL)
    private Set<CakeVariant> variants=new HashSet<>() ;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_cake_category",
            joinColumns = @JoinColumn(name = "product_cake_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CakeCategory> categories = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_cake_ingredients",
            joinColumns = @JoinColumn(name = "product_cake_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<Ingredient> ingredients = new HashSet<>();



    @OneToMany(mappedBy = "productCake",cascade = CascadeType.ALL)
   private Set<Rating>ratings=new HashSet<>() ;



    @ElementCollection
    @CollectionTable(name = "product_tags", joinColumns = @JoinColumn(name = "product_cake_id"))
    @Column(name = "tag")
    private Set<String> tags=new HashSet<>() ;//Example:"best seller""new""kids favorite"

}
