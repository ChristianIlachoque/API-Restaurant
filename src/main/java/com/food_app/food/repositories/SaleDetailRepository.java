package com.food_app.food.repositories;

import com.food_app.food.entities.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, UUID> {
    List<SaleDetail> findBySaleId(UUID id);
}
