package com.msa.product.dto;

import com.msa.product.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class ProductRequestDto {
    private String title;
    private int price;
    private int category;
    private int stock;

    public ProductRequestDto(Product product) {
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.stock = product.getStock();
    }
}
