package com.food_app.food.services;

import com.food_app.food.dto.*;
import com.food_app.food.entities.Dish;
import com.food_app.food.entities.Sale;
import com.food_app.food.entities.SaleDetail;
import com.food_app.food.repositories.DishRepository;
import com.food_app.food.repositories.SaleDetailRepository;
import com.food_app.food.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SaleServiceImpl implements ISaleService{
    @Autowired
    SaleRepository saleRepository;

    @Autowired
    SaleDetailRepository saleDetailRepository;

    @Autowired
    DishRepository dishRepository;

    @Override
    public List<SaleResponseDTO> getAll() {
        return saleRepository.findAll()
                .stream()
                .map(sale -> SaleResponseDTO.builder()
                        .id(sale.getId())
                        .date(sale.getDate())
                        .saleItems(
                                saleDetailRepository.findBySaleId(sale.getId())
                                        .stream()
                                        .map(item -> SaleDetailResponseDTO.convertToDTO(item))
                                        .toList()
                        )
                        .build()
                ).toList();

    }

    @Override
    public SaleResponseDTO getById(UUID id) {
        Sale sale = saleRepository.findById(id).orElse(null);
        if(sale != null) SaleResponseDTO.builder()
                .id(sale.getId())
                .date(sale.getDate())
                .saleItems(
                        saleDetailRepository.findBySaleId(sale.getId())
                                .stream()
                                .map(item -> SaleDetailResponseDTO.convertToDTO(item))
                                .toList()
                )
                .build();

        return null;
    }

    @Override
    public SaleResponseDTO create(SaleRequestDTO saleRequestDTO) {
        Sale sale = Sale.builder()
                .date(saleRequestDTO.getDate())
                .build();
        Sale newSale = saleRepository.save(sale);
        List<SaleDetailResponseDTO> saleDetailResponseDTOList = new ArrayList<>();
        List<SaleDetailRequestDTO> saleDetailRequestDTOList = saleRequestDTO.getSaleItems().stream().toList();
        saleDetailRequestDTOList.stream().forEach(saleDetailRequestDTO -> {
            Dish currentDish = dishRepository.findById(saleDetailRequestDTO.getDish()).orElse(null);
            SaleDetail newSaleDetail = SaleDetail.builder()
                    .dish(currentDish)
                    .sale(newSale)
                    .quantity(saleDetailRequestDTO.getQuantity())
                    .build();
            SaleDetail saleDetailSaved = saleDetailRepository.save(newSaleDetail);
            saleDetailResponseDTOList.add(
                    SaleDetailResponseDTO.builder()
                            .id(saleDetailSaved.getId())
                            .dish(DishResponseDTO.convertToDTO(saleDetailSaved.getDish()))
                            .quantity(saleDetailSaved.getQuantity())
                            .build()
            );
        });

        return SaleResponseDTO.builder()
                .id(newSale.getId())
                .date(newSale.getDate())
                .saleItems(saleDetailResponseDTOList)
                .build();
    }

    @Override
    public SaleResponseDTO update(UUID id, SaleRequestDTO saleRequestDTO) {
        List<SaleDetail> saleDetailsPrevious = saleDetailRepository.findBySaleId(id);
        saleDetailsPrevious.forEach(saleDetailPrevious -> saleDetailRepository.deleteById(saleDetailPrevious.getId()));

        Sale salePrevious = saleRepository.findById(id).orElse(null);
        salePrevious.setDate(saleRequestDTO.getDate());
        Sale updatedSale = saleRepository.save(salePrevious);

        List<SaleDetailResponseDTO> saleDetailResponseDTOList = new ArrayList<>();
        List<SaleDetailRequestDTO> saleDetailRequestDTOList = saleRequestDTO.getSaleItems().stream().toList();
        saleDetailRequestDTOList.stream().forEach(saleDetailRequestDTO -> {
            Dish currentDish = dishRepository.findById(saleDetailRequestDTO.getDish()).orElse(null);
            SaleDetail newSaleDetail = SaleDetail.builder()
                    .dish(currentDish)
                    .sale(updatedSale)
                    .quantity(saleDetailRequestDTO.getQuantity())
                    .build();
            SaleDetail saleDetailSaved = saleDetailRepository.save(newSaleDetail);
            saleDetailResponseDTOList.add(
                    SaleDetailResponseDTO.builder()
                            .id(saleDetailSaved.getId())
                            .dish(DishResponseDTO.convertToDTO(saleDetailSaved.getDish()))
                            .quantity(saleDetailSaved.getQuantity())
                            .build()
            );
        });

        return SaleResponseDTO.builder()
                .id(updatedSale.getId())
                .date(updatedSale.getDate())
                .saleItems(saleDetailResponseDTOList)
                .build();
    }

    @Override
    public boolean delete(UUID id) {
        Sale sale = saleRepository.findById(id).orElse(null);
        if(sale != null){
            List<SaleDetail> saleDetailRepositoryList = saleDetailRepository.findBySaleId(sale.getId());
            saleDetailRepositoryList.stream().forEach(saleDetail -> saleDetailRepository.deleteById(saleDetail.getId()));
            saleRepository.delete(sale);
            return true;
        }
        return false;
    }
}
