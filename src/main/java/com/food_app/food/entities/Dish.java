package com.food_app.food.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Setter
@Getter
@Entity
@Builder
@Table(name = "dishes")
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;

    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "ingredients_description")
    private String ingredientsDescription;

    private double price;

    private double discount;

    private String image;

    private boolean availability;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
}
