package com.food_app.food.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishRequestDTO {
    private String name;
    private String description;
    private String ingredientsDescription;
    private double price;
    private double discount;
    private String image;
    private boolean availability;
    private UUID category;
}
