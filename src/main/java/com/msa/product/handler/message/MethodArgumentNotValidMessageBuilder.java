package com.msa.product.handler.message;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Iterator;

public class MethodArgumentNotValidMessageBuilder implements ExceptionMessageProvider {
    Iterator<ObjectError> iterator;
    FieldError message;

    public MethodArgumentNotValidMessageBuilder(Iterator<ObjectError> iterator) {
        this.iterator = iterator;
    }

    @Override
    public String getFieldName() {
        return message.getField();
    }

    @Override
    public Object getInput() {
        return (Object) message.getRejectedValue();
    }

    @Override
    public String getErrorMessage() {
        return message.getDefaultMessage();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public void next() {
        message = (FieldError) iterator.next();
    }
}
