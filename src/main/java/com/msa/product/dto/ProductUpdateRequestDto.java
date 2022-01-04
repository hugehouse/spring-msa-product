package com.msa.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class ProductUpdateRequestDto {

    @NotEmpty
    @Size(max = 30)
    private String title;

    @NotNull
    private int price;

    @NotNull
    private int category;

    @NotNull
    private int stock;
}
