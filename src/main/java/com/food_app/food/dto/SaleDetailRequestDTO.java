package com.food_app.food.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDetailRequestDTO {
    private UUID dish;
    private int quantity;
}
