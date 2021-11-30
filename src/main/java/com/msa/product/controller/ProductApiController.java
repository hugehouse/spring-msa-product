package com.msa.product.controller;

import com.msa.product.domain.Product;
import com.msa.product.dto.ProductAddRequestDto;
import com.msa.product.dto.ProductUpdateRequestDto;
import com.msa.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class ProductApiController {
    private final ProductService productService;

    // status -> static, 같은 파일 내 builder 클래스 리턴
    // header -> builder 클래스 내의 클래스(setter)
    // body -> set된 내용을 토대로 ResponseEntity 리턴
    @PostMapping(path = "/products")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid ProductAddRequestDto product) {
        EntityModel<Product>
        return ResponseEntity
                .created()
                .body(productService.addProduct(product));
    }

    @PutMapping(path = "/products")
    public void updateProduct(@RequestParam Long itemId, @RequestBody @Valid ProductUpdateRequestDto product) {
        productService.updateProduct(itemId, product);
    }

    @DeleteMapping(path = "/products")
    public void deleteProduct(@RequestParam Long itemId) {
        productService.deleteProduct(itemId);
    }
}
