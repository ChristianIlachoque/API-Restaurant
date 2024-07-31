package com.food_app.food.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Setter
@Getter
@Entity
@Builder
@Table(name = "sale_details")
@AllArgsConstructor
@NoArgsConstructor
public class SaleDetail {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "id_dish")
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "id_sale")
    private Sale sale;
}
