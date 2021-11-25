package com.msa.product.dto;

import com.msa.product.domain.Product;
import lombok.Getter;

@Getter
public class ProductListResponseDto {
    private Long id;
    private String title;
    private int price;
    private int category;
    private int stock;

    public ProductListResponseDto(Product product) {
        id = product.getId();
        title = product.getTitle();
        price = product.getPrice();
        category = product.getCategory();
        stock = product.getStock();
    }
}
