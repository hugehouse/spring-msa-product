package com.msa.product.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.Link;

@Getter
public class ProductInfoInOrderDto {
    private Link link;
    private String title;

    @Builder
    ProductInfoInOrderDto(Link link, String title) {
        this.link = link;
        this.title = title;
    }
}
