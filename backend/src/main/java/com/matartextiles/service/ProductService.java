package com.matartextiles.service;

import com.matartextiles.dto.ProductDto;
import com.matartextiles.entity.Category;
import com.matartextiles.entity.Product;
import com.matartextiles.exception.ResourceNotFoundException;
import com.matartextiles.mapper.ProductMapper;
import com.matartextiles.repository.CategoryRepository;
import com.matartextiles.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public Page<ProductDto> findAll(String categorySlug, String search, Pageable pageable) {
        Page<Product> products;
        if (categorySlug != null && search != null) {
            products = productRepository.searchProductsByCategory(categorySlug, search, pageable);
        } else if (categorySlug != null) {
            products = productRepository.findByCategorySlugAndActiveTrue(categorySlug, pageable);
        } else if (search != null) {
            products = productRepository.searchProducts(search, pageable);
        } else {
            products = productRepository.findByActiveTrue(pageable);
        }
        return products.map(productMapper::toDto);
    }

    public ProductDto findBySlug(String slug) {
        Product product = productRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + slug));
        return productMapper.toDto(product);
    }

    public ProductDto create(ProductDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + dto.getCategoryId()));
        Product product = Product.builder()
                .nameFr(dto.getNameFr())
                .nameEn(dto.getNameEn())
                .slug(dto.getSlug())
                .descriptionFr(dto.getDescriptionFr())
                .descriptionEn(dto.getDescriptionEn())
                .price(dto.getPrice())
                .imageUrl(dto.getImageUrl())
                .sku(dto.getSku())
                .stockQuantity(dto.getStockQuantity() != null ? dto.getStockQuantity() : 0)
                .active(dto.getActive() != null ? dto.getActive() : true)
                .category(category)
                .build();
        return productMapper.toDto(productRepository.save(product));
    }

    public ProductDto update(Long id, ProductDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
        productMapper.updateEntity(product, dto);
        if (dto.getCategoryId() != null && !dto.getCategoryId().equals(product.getCategory().getId())) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + dto.getCategoryId()));
            product.setCategory(category);
        }
        return productMapper.toDto(productRepository.save(product));
    }

    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found: " + id);
        }
        productRepository.deleteById(id);
    }
}
