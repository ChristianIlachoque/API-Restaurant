package com.food_app.food.services;

import com.food_app.food.dto.SaleDetailRequestDTO;
import com.food_app.food.dto.SaleDetailResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ISaleDetailService {
    List<SaleDetailResponseDTO> getBySale(UUID id);
    SaleDetailResponseDTO update(UUID id, SaleDetailRequestDTO saleDetailResponseDTO);
}
