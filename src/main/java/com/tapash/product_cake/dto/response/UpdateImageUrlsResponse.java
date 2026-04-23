package com.tapash.product_cake.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;
@Getter
@Setter
public class UpdateImageUrlsResponse {
    private UUID id;
    private Set<String> imageUrl;
}
