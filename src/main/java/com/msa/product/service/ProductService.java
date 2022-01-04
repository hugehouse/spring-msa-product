package com.msa.product.service;

import com.msa.product.domain.Product;
import com.msa.product.dto.ProductAddRequestDto;
import com.msa.product.dto.ProductUpdateRequestDto;
import com.msa.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@RequiredArgsConstructor
@Validated
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Product findProduct(Long itemId) {
        return productRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 제품을 찾을 수 없습니다."));
    }

    // Limit이 정해진 개수를 넘지 않도록 정규화 과정을 거침
    @Transactional(readOnly = true)
    public Page<Product> findPagingProducts(int offset, int limit) {
        return productRepository.findAll(PageRequest.of(
                offset,
                limit,
                Sort.Direction.DESC, "id"));
    }

    @Transactional
    public Product addProduct(@Valid ProductAddRequestDto product) {
        return productRepository.save(product.toEntity());
    }

    @Transactional
    public Product updateProduct(Long itemId, @Valid ProductUpdateRequestDto updateData) {
        Product product = productRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 제품을 찾을 수 없습니다."));
        product.updateEntity(
                updateData.getTitle(),
                updateData.getPrice(),
                updateData.getCategory(),
                updateData.getStock());
        return product;
    }

    // 구입 요청 시
    @Transactional
    public Product updateProduct(Long itemId, int amount) {
        Product product = productRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 제품을 찾을 수 없습니다."));
        product.reduceStock(amount);
        return product;
    }

    @Transactional
    public void deleteProduct(Long itemId) {
        Product product = productRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 제품을 찾을 수 없습니다."));
        productRepository.delete(product);
    }
}
