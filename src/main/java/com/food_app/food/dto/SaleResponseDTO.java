package com.food_app.food.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SaleResponseDTO {
    private UUID id;
    private LocalDateTime date;
    private List<SaleDetailResponseDTO> saleItems;
}
