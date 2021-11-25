package com.msa.product.service;

import com.msa.product.domain.Product;
import com.msa.product.dto.ProductAddRequestDto;
import com.msa.product.dto.ProductListResponseDto;
import com.msa.product.dto.ProductResponseDto;
import com.msa.product.dto.ProductUpdateRequestDto;
import com.msa.product.repository.ProductRepository;
import com.msa.product.util.PageLimitNormalizer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductResponseDto findProduct(Long itemId) {
        Product product = productRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return new ProductResponseDto(product);
    }

    // Limit이 정해진 개수를 넘지 않도록 정규화 과정을 거침
    @Transactional(readOnly = true)
    public List<ProductListResponseDto> findPagingProducts(int offset, int limit) {
        return productRepository.findAll(PageRequest.of(
                offset,
                PageLimitNormalizer.normalize(limit),
                Sort.Direction.DESC, "id")).stream().map(ProductListResponseDto::new).collect(Collectors.toList());
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
