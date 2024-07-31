package com.food_app.food.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequestDTO {
    private LocalDateTime date;
    private List<SaleDetailRequestDTO> saleItems;
}
