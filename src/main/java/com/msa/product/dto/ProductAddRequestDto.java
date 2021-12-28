package com.msa.product.dto;

import com.msa.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// !필드를 초기화하는 코드를 작성하지 않아도 RequestBody에 의해 초기화가 됨.
@Getter
@NoArgsConstructor
public class ProductAddRequestDto {

    @NotEmpty
    @Size(max = 30)
    private String title;

    @NotNull
    private int price;

    @NotNull
    private int category;

    @NotNull
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
