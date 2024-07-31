package com.food_app.food.services;

import com.food_app.food.dto.DishResponseDTO;
import com.food_app.food.dto.SaleDetailRequestDTO;
import com.food_app.food.dto.SaleDetailResponseDTO;
import com.food_app.food.entities.Dish;
import com.food_app.food.entities.SaleDetail;
import com.food_app.food.repositories.DishRepository;
import com.food_app.food.repositories.SaleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class SaleDetailServiceImpl implements ISaleDetailService{
    @Autowired
    SaleDetailRepository saleDetailRepository;

    @Autowired
    DishRepository dishRepository;

    @Override
    public List<SaleDetailResponseDTO> getBySale(UUID id) {
        List<SaleDetail> saleDetailList = saleDetailRepository.findBySaleId(id);
        List<SaleDetailResponseDTO> saleDetailResponseDTOList = saleDetailList.stream().map(
                saleDetail ->
                        SaleDetailResponseDTO.builder()
                                .id(saleDetail.getId())
                                .dish(
                                        DishResponseDTO.builder()
                                                .id(saleDetail.getDish().getId())
                                                .name(saleDetail.getDish().getName())
                                                .description(saleDetail.getDish().getDescription())
                                                .price(saleDetail.getDish().getPrice())
                                                .ingredientsDescription(saleDetail.getDish().getIngredientsDescription())
                                                .image(saleDetail.getDish().getImage())
                                                .discount(saleDetail.getDish().getDiscount())
                                                .availability(saleDetail.getDish().isAvailability())
                                                .build()
                                )
                                .quantity(saleDetail.getQuantity())
                                .build()
        ).toList();
        return saleDetailResponseDTOList;
    }

    @Override
    public SaleDetailResponseDTO update(UUID id, SaleDetailRequestDTO saleDetailRequestDTO) {
        SaleDetail currentSaleDetail = saleDetailRepository.findById(id).orElse(null);
        currentSaleDetail.setQuantity(saleDetailRequestDTO.getQuantity());
        Dish currentDish = dishRepository.findById(saleDetailRequestDTO.getDish()).orElse(null);
        if(currentDish != null){
            currentSaleDetail.setDish(currentDish);
        }
        SaleDetail updatedSaleDetail = saleDetailRepository.save(currentSaleDetail);
        SaleDetailResponseDTO saleDetailResponseDTO = SaleDetailResponseDTO.builder()
                .id(updatedSaleDetail.getId())
                .dish(DishResponseDTO.builder()
                        .id(updatedSaleDetail.getDish().getId())
                        .name(updatedSaleDetail.getDish().getName())
                        .build())
                .quantity(updatedSaleDetail.getQuantity())
                .build();
        return saleDetailResponseDTO;
    }
}
