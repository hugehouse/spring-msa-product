package com.msa.product.controller;

import com.msa.product.dto.ProductRequestDto;
import com.msa.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class IndexController {
    private final ProductService productService;

    @GetMapping(path = "/")
    public String index() {
        return "hello";
    }

    @GetMapping(path = "/products")
    public ProductRequestDto productDetail(@RequestParam Long itemId) {
        return productService.findProduct(itemId);
    }
}
