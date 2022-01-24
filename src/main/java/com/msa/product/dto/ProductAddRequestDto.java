package com.msa.product.dto;

import com.msa.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

// !필드를 초기화하는 코드를 작성하지 않아도 RequestBody에 의해 초기화가 됨.
@Getter
@NoArgsConstructor
public class ProductAddRequestDto {

    @NotBlank
    @Size(max = 30)
    private String title;

    @PositiveOrZero
    private int price;

    @PositiveOrZero
    private int category;

    @PositiveOrZero
    private int stock;

    public Product toEntity() {
        return Product.builder()
                .title(title)
                .price(price)
                .category(category)
                .stock(stock)
                .build();
    }
}
