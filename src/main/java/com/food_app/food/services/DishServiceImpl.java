package com.food_app.food.services;

import com.food_app.food.dto.CategoryResponseDTO;
import com.food_app.food.dto.DishRequestDTO;
import com.food_app.food.dto.DishResponseDTO;
import com.food_app.food.entities.Category;
import com.food_app.food.entities.Dish;
import com.food_app.food.repositories.CategoryRepository;
import com.food_app.food.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DishServiceImpl implements IDishService {

    private final Path rootFolder = Paths.get("uploads");

    @Autowired
    DishRepository dishRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public DishResponseDTO convertToDTO(Dish dish){
        return DishResponseDTO.builder()
                .id(dish.getId())
                .name(dish.getName())
                .description(dish.getDescription())
                .ingredientsDescription(dish.getIngredientsDescription())
                .price(dish.getPrice())
                .discount(dish.getDiscount())
                .image(dish.getImage())
                .availability(dish.isAvailability())
                .category(CategoryResponseDTO.builder()
                        .id(dish.getCategory().getId())
                        .name(dish.getCategory().getName())
                        .build()
                )
                .build();
    }

    @Override
    public List<DishResponseDTO> getAll() {
        List<Dish> dishes = dishRepository.findAll();
        List<DishResponseDTO> dishesDTO= new ArrayList<>();
        for(Dish dish: dishes) dishesDTO.add(convertToDTO(dish));
        return dishesDTO;
    }

    @Override
    public DishResponseDTO getById(UUID id) {
        Dish dish = dishRepository.findById(id).orElse(null);
        if(dish != null){
            return convertToDTO(dish);
        }
        return null;
    }

    @Override
    public DishResponseDTO create(DishRequestDTO dish) {
        Category category = categoryRepository.getById(dish.getCategory());

        Dish newDish = dishRepository.save(
                Dish.builder()
                        .name(dish.getName())
                        .description(dish.getDescription())
                        .ingredientsDescription(dish.getIngredientsDescription())
                        .price(dish.getPrice())
                        .discount(dish.getDiscount())
                        .image(dish.getImage())
                        .availability(dish.isAvailability())
                        .category(category)
                        .build()
        );

        return convertToDTO(newDish);
    }

    @Override
    public DishResponseDTO update(UUID id, DishRequestDTO dishRequestDTO) {
        Dish currentDish = dishRepository.findById(id).orElse(null);
        currentDish.setName(dishRequestDTO.getName());
        currentDish.setDescription(dishRequestDTO.getDescription());
        currentDish.setIngredientsDescription(dishRequestDTO.getIngredientsDescription());
        currentDish.setPrice(dishRequestDTO.getPrice());
        currentDish.setDiscount(dishRequestDTO.getDiscount());
        currentDish.setAvailability(dishRequestDTO.isAvailability());
        Category currentCategory = categoryRepository.findById(dishRequestDTO.getCategory()).orElse(null);
        if(currentCategory != null) currentDish.setCategory(currentCategory);
        Dish updatedDish = dishRepository.save(currentDish);
        return convertToDTO(updatedDish);
    }

    @Override
    public boolean delete(UUID id) {
        Dish dish = dishRepository.findById(id).orElse(null);
        if(dish != null) {
            dishRepository.delete(dish);
            return true;
        }
        return false;
    }

    @Override
    public DishResponseDTO uploadImage(MultipartFile image, UUID id) throws IOException {
        String newPath = "/uploads/" + image.getOriginalFilename();
        Files.copy(image.getInputStream(), this.rootFolder.resolve(image.getOriginalFilename()));

        Dish currentDish = dishRepository.findById(id).orElse(null);
        currentDish.setImage(newPath);

        return convertToDTO(dishRepository.save(currentDish));
    }
}
