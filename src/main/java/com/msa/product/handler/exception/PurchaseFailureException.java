package com.msa.product.handler.exception;

import com.msa.product.handler.ErrorHolder;

public class PurchaseFailureException extends RuntimeException {
    private Long id;
    private final ErrorHolder errorHolder;

    public PurchaseFailureException(ErrorHolder errorHolder, Long id) {
        super();
        this.errorHolder = errorHolder;
        this.id = id;
    }

    public PurchaseFailureException(ErrorHolder errorHolder) {
        super();
        this.errorHolder = errorHolder;
    }

    public ErrorHolder getErrorHolder() {
        return errorHolder;
    }
    public Long getId() {
        return id;
    }
}
