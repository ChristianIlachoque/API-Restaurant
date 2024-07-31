package com.food_app.food.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
@Builder
@Table(name = "sales")
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;
    private LocalDateTime date;
}
