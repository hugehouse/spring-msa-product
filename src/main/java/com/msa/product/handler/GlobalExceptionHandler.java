package com.msa.product.handler;

import com.msa.product.controller.IndexController;
import com.msa.product.handler.exception.PurchaseFailureException;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;

import static com.msa.product.util.PageLimitNormalizer.normalize;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 존재하지 않는 페이지 접근, 링크를 함께 포함시키기 위해 EntityModel로 생성했음
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<EntityModel<ErrorHolder>> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(EntityModel.of(new ErrorHolder(ErrorResponse.NotFoundContent, e.getLocalizedMessage())
                        , linkTo(methodOn(IndexController.class).pagingProducts(0, normalize(0))).withRel("list")));
    }

    // Vaildation 에러 처리
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorHolder(ErrorResponse.InsertConstraintViolation,
                        getResultMessage(e.getConstraintViolations().iterator())));
    }

    // 구매 실패 에러 처리, 제품 link 포함
    @ExceptionHandler(value = PurchaseFailureException.class)
    public ResponseEntity<EntityModel<ErrorHolder>> handlePurchaseFailureException(PurchaseFailureException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(EntityModel.of(e.getErrorHolder()
                        , linkTo(methodOn(IndexController.class).productDetail(e.getId())).withSelfRel()));
    }

    private String getResultMessage(Iterator<ConstraintViolation<?>> violationIterator) {
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
