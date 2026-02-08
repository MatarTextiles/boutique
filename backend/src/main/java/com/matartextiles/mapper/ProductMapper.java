package com.matartextiles.mapper;

import com.matartextiles.dto.ProductDto;
import com.matartextiles.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto toDto(Product entity) {
        return ProductDto.builder()
                .id(entity.getId())
                .nameFr(entity.getNameFr())
                .nameEn(entity.getNameEn())
                .slug(entity.getSlug())
                .descriptionFr(entity.getDescriptionFr())
                .descriptionEn(entity.getDescriptionEn())
                .price(entity.getPrice())
                .imageUrl(entity.getImageUrl())
                .sku(entity.getSku())
                .stockQuantity(entity.getStockQuantity())
                .active(entity.getActive())
                .categoryId(entity.getCategory().getId())
                .categorySlug(entity.getCategory().getSlug())
                .categoryNameFr(entity.getCategory().getNameFr())
                .categoryNameEn(entity.getCategory().getNameEn())
                .build();
    }

    public void updateEntity(Product entity, ProductDto dto) {
        entity.setNameFr(dto.getNameFr());
        entity.setNameEn(dto.getNameEn());
        entity.setSlug(dto.getSlug());
        entity.setDescriptionFr(dto.getDescriptionFr());
        entity.setDescriptionEn(dto.getDescriptionEn());
        entity.setPrice(dto.getPrice());
        entity.setImageUrl(dto.getImageUrl());
        entity.setSku(dto.getSku());
        if (dto.getStockQuantity() != null) entity.setStockQuantity(dto.getStockQuantity());
        if (dto.getActive() != null) entity.setActive(dto.getActive());
    }
}
