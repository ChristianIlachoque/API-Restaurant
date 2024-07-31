package com.food_app.food.services;

import com.food_app.food.dto.CategoryRequestDTO;
import com.food_app.food.dto.CategoryResponseDTO;
import com.food_app.food.entities.Category;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    List<CategoryResponseDTO> getAll();
    CategoryResponseDTO getById(UUID id);
    CategoryResponseDTO create(CategoryRequestDTO category);
    CategoryResponseDTO update(UUID id, CategoryRequestDTO categoryRequestDTO);
    boolean delete(UUID id);
}
