package com.msa.product.controller;

import com.msa.product.controller.converter.EntityToModelConverter;
import com.msa.product.domain.Product;
import com.msa.product.handler.ErrorHolder;
import com.msa.product.handler.ErrorResponse;
import com.msa.product.handler.exception.PurchaseFailureException;
import com.msa.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.msa.product.util.PageLimitNormalizer.normalize;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class IndexController {
    private final ProductService productService;
    private final EntityToModelConverter entityToModelConverter;

    // 단순 detail 링크(리스트 rel 표시 없음)
    @GetMapping("/{id}")
    public EntityModel<Product> productDetail(@PathVariable Long id) {
        return entityToModelConverter.toModel(productService.findProduct(id));
    }

    // 리스트를 통해 접근하는 detail 링크
    @GetMapping("/items/{id}")
    public EntityModel<Product> productDetailWithPageInfo(@PathVariable Long id,
                                              @RequestParam(defaultValue = "0") int offset,
                                              @RequestParam(defaultValue = "10") int limit) {
        return entityToModelConverter.toModel(productService.findProduct(id), offset, normalize(limit));
    }

    @GetMapping
    public CollectionModel<EntityModel<Product>> pagingProducts(@RequestParam(defaultValue = "0") int offset,
                                                                @RequestParam(defaultValue = "10") int limit) {
        int normalizedLimit = normalize(limit);
        Page<Product> pagedProducts = productService.findPagingProducts(offset, normalizedLimit);
        List<EntityModel<Product>> products = pagedProducts.stream()
                .map(entity -> entityToModelConverter.toModelWithPage(entity, offset, normalizedLimit)).collect(Collectors.toList());
        // Iterable로 확장된 클래스가 필요하기 때문에 List로 넘긴다. 이후 CollectionModel 내의 필드에 ArrayList 형식으로 저장한다.
        return entityToModelConverter.toCollectionModel(products, pagedProducts, offset, normalizedLimit);
    }
}