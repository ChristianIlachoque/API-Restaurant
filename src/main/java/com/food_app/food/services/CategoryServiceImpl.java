package com.food_app.food.services;

import com.food_app.food.dto.CategoryRequestDTO;
import com.food_app.food.dto.CategoryResponseDTO;
import com.food_app.food.entities.Category;
import com.food_app.food.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements ICategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDTO> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> CategoryResponseDTO.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build()
                ).toList();
    }

    @Override
    public CategoryResponseDTO getById(UUID id) {
        Category category = categoryRepository.findById(id).orElse(null);

        if(category != null){
            CategoryResponseDTO categoryDTO = CategoryResponseDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
            return categoryDTO;
        }
        return null;
    }

    @Override
    public CategoryResponseDTO create(CategoryRequestDTO categoryRequest) {
        Category newCategory = categoryRepository.save(
                Category.builder()
                        .name(categoryRequest.getName())
                        .build()
        );

        return CategoryResponseDTO.builder()
                .id(newCategory.getId())
                .name(newCategory.getName())
                .build();
    }

    @Override
    public CategoryResponseDTO update(UUID id, CategoryRequestDTO categoryRequestDTO) {
        Category currentCategory = categoryRepository.findById(id).orElse(null);
        currentCategory.setName(categoryRequestDTO.getName());
        Category updatedeCategory = categoryRepository.save(currentCategory);
        return CategoryResponseDTO.builder()
                .id(updatedeCategory.getId())
                .name(updatedeCategory.getName())
                .build();
    }

    @Override
    public boolean delete(UUID id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category != null) {
            categoryRepository.delete(category);
            return true;
        }
        return false;
    }
}
