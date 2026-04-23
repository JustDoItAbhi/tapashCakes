package com.tapash.product_cake.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class UpdateImages {
    private UUID id;
    private Set<String> imageUrl;
}
