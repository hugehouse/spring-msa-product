package com.msa.product.handler.message;

import javax.validation.ConstraintViolation;
import java.util.Iterator;

public class ConstraintViolationMessageBuilder implements ExceptionMessageProvider{
    Iterator<ConstraintViolation<?>> iterator;
    ConstraintViolation<?> message;

    public ConstraintViolationMessageBuilder(Iterator<ConstraintViolation<?>> iterator) {
        this.iterator = iterator;
    }

    @Override
    public String getFieldName() {
        return message.getPropertyPath().toString();
    }

    @Override
    public Object getInput() {
        return (Object) message.getInvalidValue();
    }

    @Override
    public String getErrorMessage() {
        return message.getMessage();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public void next() {
        message = iterator.next();
    }
}
