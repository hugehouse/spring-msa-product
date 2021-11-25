package com.msa.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class ProductUpdateRequestDto {

    @NotBlank(message = "title을 입력해주세요.")
    @Size(max = 30)
    private String title;

    @NotNull(message = "price를 입력해주세요.")
    private int price;

    @NotNull(message = "category를 입력해주세요.")
    private int category;

    @NotNull(message = "stock을 입력해주세요.")
    private int stock;
}
