package com.food_app.food.dto;

import com.food_app.food.entities.SaleDetail;
import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SaleDetailResponseDTO {
    private UUID id;
    private DishResponseDTO dish;
    private int quantity;

    public static SaleDetailResponseDTO convertToDTO(SaleDetail saleDetail){
        return SaleDetailResponseDTO.builder()
                .id(saleDetail.getId())
                .dish(DishResponseDTO.convertToDTO(saleDetail.getDish()))
                .quantity(saleDetail.getQuantity())
                .build();
    }
}
