package com.msa.product.handler;

import lombok.Getter;

@Getter
public class ErrorHolder {
    private int code;
    private String description;

    public ErrorHolder(ErrorResponse error) {
        code = error.getCode();
        description = error.getDescription();
    }
}
