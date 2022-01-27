package com.msa.product.handler;

import com.msa.product.controller.IndexController;
import com.msa.product.handler.exception.PurchaseFailureException;
import com.msa.product.handler.message.ConstraintViolationMessageBuilder;
import com.msa.product.handler.message.ExceptionResultMessageBuilder;
import com.msa.product.handler.message.MethodArgumentNotValidMessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    /*
    Validation Exception 두 메소드에 Strategy 패턴 적용해서 출력 방식 통일했음
    ExceptionMessageProvider 인터페이스로 확장한 두 클래스를 ConstraintViolationMessageBuilder에서 주입받아 사용
     */

    // Service단에서의 Vaildation 에러 처리
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e) {

        ExceptionResultMessageBuilder builder = new ExceptionResultMessageBuilder(
                new ConstraintViolationMessageBuilder(e.getConstraintViolations().iterator()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorHolder(ErrorResponse.InsertConstraintViolation,
                        builder.getResultMessage()));
    }

    // Controller단에서의 Validation 처리
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        ExceptionResultMessageBuilder builder = new ExceptionResultMessageBuilder(
                new MethodArgumentNotValidMessageBuilder(e.getAllErrors().iterator()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorHolder(ErrorResponse.InsertConstraintViolation,
                        builder.getResultMessage()));
    }

    // 구매 실패 에러 처리, 제품 link 포함
    @ExceptionHandler(value = PurchaseFailureException.class)
    public ResponseEntity<EntityModel<ErrorHolder>> handlePurchaseFailureException(PurchaseFailureException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(EntityModel.of(e.getErrorHolder()
                        , linkTo(methodOn(IndexController.class).productDetail(e.getId())).withSelfRel()));
    }
}
