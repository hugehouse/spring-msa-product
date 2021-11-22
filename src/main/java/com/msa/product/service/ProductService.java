package com.msa.product.service;

import com.msa.product.domain.Product;
import com.msa.product.dto.ProductAddRequestDto;
import com.msa.product.dto.ProductRequestDto;
import com.msa.product.dto.ProductUpdateRequestDto;
import com.msa.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductRequestDto findProduct(Long itemId) {
        Product product = productRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return new ProductRequestDto(product);
    }

    @Transactional
    public Product addProduct(ProductAddRequestDto product) {
        return productRepository.save(product.toEntity());
    }

    @Transactional
    public void updateProduct(Long itemId, ProductUpdateRequestDto updateData) {
        Product product = productRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        product.updateEntity(
                updateData.getTitle(),
                updateData.getPrice(),
                updateData.getCategory(),
                updateData.getStock());
    }

    @Transactional
    public void deleteProduct(Long itemId) {
        Product product = productRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        productRepository.delete(product);
    }
}
