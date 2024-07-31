package com.food_app.food.dto;

import com.food_app.food.entities.Dish;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishResponseDTO {
    private UUID id;
    private String name;
    private String description;
    private String ingredientsDescription;
    private double price;
    private double discount;
    private String image;
    private boolean availability;
    private CategoryResponseDTO category;

    public static DishResponseDTO convertToDTO(Dish dish){
        return DishResponseDTO.builder()
                .id(dish.getId())
                .name(dish.getName())
                .description(dish.getDescription())
                .ingredientsDescription(dish.getIngredientsDescription())
                .price(dish.getPrice())
                .discount(dish.getDiscount())
                .image(dish.getImage())
                .availability(dish.isAvailability())
                .category(CategoryResponseDTO.convertToDTO(dish.getCategory()))
                .build();
    }
}
