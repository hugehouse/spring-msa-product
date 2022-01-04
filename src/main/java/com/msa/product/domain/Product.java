package com.msa.product.domain;

import com.msa.product.handler.ErrorHolder;
import com.msa.product.handler.ErrorResponse;
import com.msa.product.handler.exception.PurchaseFailureException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Jpa의 작업 중 Entity를 Proxy로 감싸는 작업을 위해 기본 생성자 구현, 무분별한 생성을 막기 위해 protected로 설정
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

    public void updateEntity(String title, int price, int category, int stock) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }

    public void reduceStock(int amount) {
        if(amount < 0) {
            throw new PurchaseFailureException(new ErrorHolder(ErrorResponse.negativeAmount), id);
        }

        if((this.stock - amount) >= 0) {
            this.stock -= amount;
        }
        else {
            throw new PurchaseFailureException(new ErrorHolder(ErrorResponse.StockLack), id);
        }
    }
}
