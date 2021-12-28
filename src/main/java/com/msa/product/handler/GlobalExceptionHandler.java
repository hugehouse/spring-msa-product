package com.msa.product.handler;

import com.msa.product.controller.converter.EntityToModelConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final EntityToModelConverter entityToModelConverter;

    // 존재하지 않는 페이지 접근
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(entityToModelConverter.getListLink(ErrorResponse.NotFoundContent));
    }

    // Vaildation 에러 처리
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorHolder(ErrorResponse.InsertConstraintViolation,
                        getResultMessage(e.getConstraintViolations().iterator())));
    }

    protected String getResultMessage(final Iterator<ConstraintViolation<?>> violationIterator) {
        final StringBuilder resultMessageBuilder = new StringBuilder();
        while (violationIterator.hasNext()) {
            final ConstraintViolation<?> constraintViolation = violationIterator.next();
            resultMessageBuilder
                    .append("['")
                    .append(getPopertyName(constraintViolation.getPropertyPath().toString())) // 유효성 검사가 실패한 속성
                    .append("' is '")
                    .append(constraintViolation.getInvalidValue()) // 유효하지 않은 값
                    .append("'. ")
                    .append(constraintViolation.getMessage()) // 유효성 검사 실패 시 메시지
                    .append("]");

            if (violationIterator.hasNext()) {
                resultMessageBuilder.append(", ");
            }
        }

        return resultMessageBuilder.toString();
    }

    private String getPopertyName(String propertyPath) {
        return propertyPath.substring(propertyPath.lastIndexOf('.') + 1); // 전체 속성 경로에서 속성 이름만 가져온다.
    }
}
