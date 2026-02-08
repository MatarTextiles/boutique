package com.matartextiles.service;

import com.matartextiles.dto.CategoryDto;
import com.matartextiles.entity.Category;
import com.matartextiles.exception.ResourceNotFoundException;
import com.matartextiles.mapper.CategoryMapper;
import com.matartextiles.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> findAll() {
        return categoryRepository.findAllByOrderByDisplayOrderAsc()
                .stream().map(categoryMapper::toDto).collect(Collectors.toList());
    }

    public CategoryDto findBySlug(String slug) {
        Category category = categoryRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + slug));
        return categoryMapper.toDto(category);
    }

    public CategoryDto create(CategoryDto dto) {
        Category category = categoryMapper.toEntity(dto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    public CategoryDto update(Long id, CategoryDto dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + id));
        categoryMapper.updateEntity(category, dto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
