package com.msa.product.handler;

import com.msa.product.controller.converter.EntityToModelConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final EntityToModelConverter entityToModelConverter;

    // 존재하지 않는 페이지 접근
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(entityToModelConverter.getListLink(ErrorResponse.NotFoundContent));// body에 enum으로 코드랑 내용 담고 링크도 포함시켜서 리턴
    }

    // Vaildation 에러 처리
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleConstraintViolationException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
}
