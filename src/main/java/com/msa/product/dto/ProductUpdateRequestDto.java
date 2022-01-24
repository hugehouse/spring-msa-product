package com.msa.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class ProductUpdateRequestDto {

    @NotBlank
    @Size(max = 30)
    private String title;

    @PositiveOrZero
    private int price;

    @PositiveOrZero
    private int category;

    @PositiveOrZero
    private int stock;
}
