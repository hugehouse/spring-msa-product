package com.msa.product.dto;

import com.msa.product.domain.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private String title;
    private int price;
    private int category;
    private int stock;

    public ProductResponseDto(Product product) {
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.stock = product.getStock();
    }
}
