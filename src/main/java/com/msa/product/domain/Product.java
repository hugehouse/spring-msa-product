package com.msa.product.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Entity
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int category;

    @Column(nullable = false)
    private int stock;

    @Builder
    public Product(String title, int price, int category, int stock) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }
}
