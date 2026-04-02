package com.tapash.categoreis.category_dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class ChangeStatusToNotAvailable {
    private UUID id;
    private String status;
}
