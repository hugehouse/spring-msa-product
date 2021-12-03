package com.msa.product.controller;

import com.msa.product.controller.converter.EntityToModelConverter;
import com.msa.product.domain.Product;
import com.msa.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static com.msa.product.util.PageLimitNormalizer.normalize;


@RequiredArgsConstructor
@RestController
public class IndexController {
    private final ProductService productService;
    private final EntityToModelConverter entityToModelConverter;

    @GetMapping(path = "/products/item/{id}")
    public EntityModel<Product> productDetail(@PathVariable Long id,
                                              @RequestParam(defaultValue = "0") int offset,
                                              @RequestParam(defaultValue = "10") int limit) {
        return entityToModelConverter.toModel(productService.findProduct(id))
                .add(linkTo(methodOn(IndexController.class).productDetail(id, offset, normalize(limit))).withSelfRel())
                .add(linkTo(methodOn(IndexController.class).pagingProducts(offset, normalize(limit))).withRel("list"));
    }

    @GetMapping(path = "/products")
    public CollectionModel<EntityModel<Product>> pagingProducts(@RequestParam(defaultValue = "0") int offset,
                                                                @RequestParam(defaultValue = "10") int limit) {
        int normalizedLimit = normalize(limit);

        List<EntityModel<Product>> products = productService.findPagingProducts(offset, normalizedLimit)
                .stream().map(entity -> entityToModelConverter.toModel(entity, offset, normalizedLimit)).collect(Collectors.toList());

        // Iterable로 확장된 클래스가 필요하기 때문에 List로 넘긴다. 이후 CollectionModel 내의 필드에 ArrayList 형식으로 저장한다.
        return CollectionModel.of(products, linkTo(methodOn(IndexController.class).pagingProducts(offset, limit)).withSelfRel());
    }
}