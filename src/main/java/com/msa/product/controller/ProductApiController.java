package com.msa.product.controller;

import com.msa.product.domain.Product;
import com.msa.product.dto.ProductAddRequestDto;
import com.msa.product.dto.ProductUpdateRequestDto;
import com.msa.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class ProductApiController {
    private final ProductService productService;

    @PostMapping(path = "/products")
    public Product addProduct(@RequestBody @Valid ProductAddRequestDto product) {
        return productService.addProduct(product);
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
