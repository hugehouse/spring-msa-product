package com.msa.product.dto;

import com.msa.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductAddRequestDto {
    private String name;

    @Builder
    public ProductAddRequestDto(String name) {
        this.name = name;
    }

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .build();
    }
}
