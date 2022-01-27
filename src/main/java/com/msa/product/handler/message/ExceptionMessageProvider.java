package com.msa.product.handler.message;

public interface ExceptionMessageProvider {
    public abstract String getFieldName();
    public abstract Object getInput();
    public abstract String getErrorMessage();
    public abstract boolean hasNext();
    public abstract void next();
}
