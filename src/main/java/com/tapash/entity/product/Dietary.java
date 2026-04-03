package com.tapash.entity.product;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Dietary {
    private boolean isEggless;
    private boolean isVegan;
    private boolean isGlutenFree;
}
