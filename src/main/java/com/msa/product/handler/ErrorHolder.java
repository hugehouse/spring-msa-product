package com.msa.product.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorHolder {
    private int code;
    private String description;
    private String details;

    public ErrorHolder(ErrorResponse error) {
        code = error.getCode();
        description = error.getDescription();
    }

    public ErrorHolder(ErrorResponse error, String details) {
        code = error.getCode();
        description = error.getDescription();
        this.details = details;
    }
}
