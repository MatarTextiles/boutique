package com.matartextiles.mapper;

import com.matartextiles.dto.CategoryDto;
import com.matartextiles.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDto toDto(Category entity) {
        return CategoryDto.builder()
                .id(entity.getId())
                .nameFr(entity.getNameFr())
                .nameEn(entity.getNameEn())
                .slug(entity.getSlug())
                .descriptionFr(entity.getDescriptionFr())
                .descriptionEn(entity.getDescriptionEn())
                .imageUrl(entity.getImageUrl())
                .displayOrder(entity.getDisplayOrder())
                .build();
    }

    public Category toEntity(CategoryDto dto) {
        return Category.builder()
                .nameFr(dto.getNameFr())
                .nameEn(dto.getNameEn())
                .slug(dto.getSlug())
                .descriptionFr(dto.getDescriptionFr())
                .descriptionEn(dto.getDescriptionEn())
                .imageUrl(dto.getImageUrl())
                .displayOrder(dto.getDisplayOrder() != null ? dto.getDisplayOrder() : 0)
                .build();
    }

    public void updateEntity(Category entity, CategoryDto dto) {
        entity.setNameFr(dto.getNameFr());
        entity.setNameEn(dto.getNameEn());
        entity.setSlug(dto.getSlug());
        entity.setDescriptionFr(dto.getDescriptionFr());
        entity.setDescriptionEn(dto.getDescriptionEn());
        entity.setImageUrl(dto.getImageUrl());
        if (dto.getDisplayOrder() != null) entity.setDisplayOrder(dto.getDisplayOrder());
    }
}
