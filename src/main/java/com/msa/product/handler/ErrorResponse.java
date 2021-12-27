package com.msa.product.handler;

import lombok.Getter;

public enum ErrorResponse {
    NotFoundContent(4001, "Not found content");


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
