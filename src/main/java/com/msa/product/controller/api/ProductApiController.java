package com.msa.product.controller.api;

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

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductApiController {
    private final ProductService productService;
    private final EntityToModelConverter entityToModelConverter;

    // ResponseEntity는 필드로 Object를 사용하기 때문에 어떤 클래스를 넣어도 됨.
    @PostMapping
    public ResponseEntity<EntityModel<Product>> addProduct(@RequestBody ProductAddRequestDto entity) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(entityToModelConverter.toModel(productService.addProduct(entity)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Product>> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequestDto entity) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityToModelConverter.toModel(productService.updateProduct(id, entity)));
    }

    @PutMapping("/purchase/{id}")
    public ResponseEntity<EntityModel<Product>> updateProduct(@PathVariable Long id, @RequestParam int amount) {
        productService.updateProduct(id, amount); // 실패 시 exception 발생
        return ResponseEntity.noContent().build(); // 성공 시 응답 코드 204 전달
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityToModelConverter.getListLink());
    }
}
