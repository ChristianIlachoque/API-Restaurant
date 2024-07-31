package com.food_app.food.services;


import com.food_app.food.dto.SaleRequestDTO;
import com.food_app.food.dto.SaleResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ISaleService {
    List<SaleResponseDTO> getAll();
    SaleResponseDTO getById(UUID id);
    SaleResponseDTO create(SaleRequestDTO saleRequestDTO);
    SaleResponseDTO update(UUID id, SaleRequestDTO saleRequestDTO);
    boolean delete(UUID id);
}
