package com.food_app.food.services;

import com.food_app.food.dto.DishRequestDTO;
import com.food_app.food.dto.DishResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface IDishService {
    List<DishResponseDTO> getAll();
    DishResponseDTO getById(UUID id);
    DishResponseDTO create(DishRequestDTO dish);
    DishResponseDTO update(UUID id, DishRequestDTO dish);
    boolean delete(UUID id);
    DishResponseDTO uploadImage(MultipartFile image, UUID id) throws IOException;
}
