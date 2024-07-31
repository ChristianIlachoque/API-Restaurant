package com.food_app.food.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Builder
@Setter
@Getter
@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;

    @Column(nullable = false)
    private String name;
}
