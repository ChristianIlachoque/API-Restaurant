package com.food_app.food.controllers;

import com.food_app.food.dto.SaleRequestDTO;
import com.food_app.food.dto.SaleResponseDTO;
import com.food_app.food.entities.Sale;
import com.food_app.food.services.SaleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/sale")
public class SaleController {
    @Autowired
    SaleServiceImpl saleService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(saleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        SaleResponseDTO saleResponseDTO = saleService.getById(id);
        if(saleResponseDTO != null){
            return ResponseEntity.ok(saleResponseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SaleRequestDTO saleRequestDTO){
        return ResponseEntity.ok(saleService.create(saleRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody SaleRequestDTO saleRequestDTO){
        SaleResponseDTO saleResponseDTO = saleService.getById(id);
        if(saleResponseDTO == null ) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(saleService.update(id, saleRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        boolean wasDeleted = saleService.delete(id);
        if(wasDeleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
