package com.msa.product.dto;

import com.msa.product.domain.Product;
import lombok.Getter;

@Getter
public class ProductListResponseDto {
    private final Long id;
    private final String title;
    private final int price;
    private final int category;
    private final int stock;

    public ProductListResponseDto(Product product) {
        id = product.getId();
        title = product.getTitle();
        price = product.getPrice();
        category = product.getCategory();
        stock = product.getStock();
    }
}
