package com.msa.product.handler.exception;

import com.msa.product.handler.ErrorHolder;

public class PurchaseFailureException extends RuntimeException {
    private final ErrorHolder errorHolder;

    public PurchaseFailureException(ErrorHolder errorHolder) {
        super();
        this.errorHolder = errorHolder;
    }

    public ErrorHolder getErrorHolder() {
        return errorHolder;
    }
}
