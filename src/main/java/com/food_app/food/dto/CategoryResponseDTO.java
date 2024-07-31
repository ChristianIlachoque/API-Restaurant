package com.food_app.food.dto;

import com.food_app.food.entities.Category;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CategoryResponseDTO {
    private UUID id;
    private String name;

    public static CategoryResponseDTO convertToDTO(Category category){
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
