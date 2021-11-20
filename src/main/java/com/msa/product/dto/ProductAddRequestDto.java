package com.msa.product.dto;

import com.msa.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class ProductAddRequestDto { // !필드를 초기화하는 코드를 작성하지 않아도 초기화가 됨.

    @NotBlank(message = "title을 입력해주세요.")
    @Size(max = 30)
    private String title;

    @NotNull(message = "price를 입력해주세요.")
    private int price;

    @NotNull(message = "category를 입력해주세요.")
    private int category;

    @NotNull(message = "stock을 입력해주세요.")
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
