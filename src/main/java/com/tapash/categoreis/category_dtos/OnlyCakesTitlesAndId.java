package com.tapash.categoreis.category_dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OnlyCakesTitlesAndId {
    private int numberOfCake;
    private UUID id;
    private String title;
}
