package com.msa.product.handler;

import lombok.Getter;

public enum ErrorResponse {
    NotFoundContent(4001, "Not found content"),
    InsertConstraintViolation(4002, "Violated constraint"),
    StockLack(4003, "Lack of stock"),
    negativeAmount(4004, "amount is negative");



    private int code;
    private String description;
    // enum의 생성자 형식
    private ErrorResponse(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
