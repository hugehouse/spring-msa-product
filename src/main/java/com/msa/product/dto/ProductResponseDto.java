package com.msa.product.dto;

import com.msa.product.domain.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private final String title;
    private final int price;
    private final int category;
    private final int stock;

    public ProductResponseDto(Product product) {
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.stock = product.getStock();
    }
}
