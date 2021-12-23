package com.msa.product.controller;

import com.msa.product.controller.converter.EntityToModelConverter;
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
import java.net.URI;

@RequiredArgsConstructor
@RestController
public class ProductApiController {
    private final ProductService productService;
    private final EntityToModelConverter entityToModelConverter;

    // ResponseEntity는 필드로 Object를 사용하기 때문에 어떤 클래스를 넣어도 됨.
    @PostMapping(path = "/products")
    public ResponseEntity<EntityModel<Product>> addProduct(@RequestBody @Valid ProductAddRequestDto product) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(entityToModelConverter.toModel(productService.addProduct(product)));
    }

    @PutMapping(path = "/products")
    public ResponseEntity<EntityModel<Product>> updateProduct(@RequestParam Long itemId, @RequestBody @Valid ProductUpdateRequestDto product) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityToModelConverter.toModel(productService.updateProduct(itemId, product)));
    }

    @DeleteMapping(path = "/products")
    public ResponseEntity deleteProduct(@RequestParam Long itemId) {
        productService.deleteProduct(itemId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityToModelConverter.getListLink());
    }
}
