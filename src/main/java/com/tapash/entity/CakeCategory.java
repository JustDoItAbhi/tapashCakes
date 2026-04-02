package com.tapash.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CakeCategory extends BaseModel{
    @Column(name = "category_name",nullable = false,length = 100)
    private String categoryName;
    @Lob
    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private CategoryStatus status;
}
