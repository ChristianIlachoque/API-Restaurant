package com.food_app.food.controllers;

import com.food_app.food.dto.DishRequestDTO;
import com.food_app.food.dto.DishResponseDTO;
import com.food_app.food.services.DishServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/dish")
public class DishController {

    @Autowired
    DishServiceImpl dishService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(dishService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        return ResponseEntity.ok(dishService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DishRequestDTO dish){
        return ResponseEntity.ok(dishService.create(dish));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> update(@PathVariable UUID id, @RequestBody DishRequestDTO dishRequestDTO){
        DishResponseDTO dishResponseDTO = dishService.getById(id);
        if(dishResponseDTO == null) return ResponseEntity.notFound().build();
        dishResponseDTO = dishService.update(id, dishRequestDTO);
        return ResponseEntity.ok(dishResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        boolean wasDeleted = dishService.delete(id);
        if(wasDeleted) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/upload-image/{id}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable("id") UUID id) throws IOException {
        return ResponseEntity.ok(dishService.uploadImage(image, id));
    }
}
