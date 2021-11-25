package com.msa.product.controller;

import com.msa.product.domain.Product;
import com.msa.product.dto.ProductListResponseDto;
import com.msa.product.dto.ProductResponseDto;
import com.msa.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class IndexController {
    private final ProductService productService;
    @GetMapping(path = "/products/item")
    public ProductResponseDto productDetail(@RequestParam Long itemId) {
        return productService.findProduct(itemId);
    }

    @GetMapping(path = "/products/list")
    public List<ProductListResponseDto> pagingProducts(@RequestParam(defaultValue = "0") int offset,
                                                       @RequestParam(defaultValue = "10") int limit) {
        return productService.findPagingProducts(offset, limit);
    }
}
