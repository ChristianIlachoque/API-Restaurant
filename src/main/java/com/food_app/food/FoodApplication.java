package com.food_app.food;

import com.food_app.food.entities.Category;
import com.food_app.food.entities.Dish;
import com.food_app.food.repositories.CategoryRepository;
import com.food_app.food.repositories.DishRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodApplication.class, args);
	}

	@Bean
	CommandLineRunner init(CategoryRepository categoryRepository, DishRepository dishRepository){
		return args -> {
			Category category1 = Category.builder()
					.name("Desayunos")
					.build();

			Category category2 = Category.builder()
					.name("Almuerzos")
					.build();

			Category category3 = Category.builder()
					.name("Cenas")
					.build();
			categoryRepository.saveAll(List.of(category1, category2, category3));

			Dish dish1 = Dish.builder()
					.name("Pollo Broadster")
					.description("Pollo Broadster Típico")
					.ingredientsDescription("Pollo, papas, ensalada")
					.price(70)
					.discount(15)
					.availability(true)
					.category(category1)
					.build();

			Dish dish2 = Dish.builder()
					.name("Pollo Broadster Mixto")
					.description("Pollo Broadster Mixto")
					.ingredientsDescription("Pollo, papas, ensalada, chaufa")
					.price(90)
					.discount(10)
					.availability(true)
					.category(category1)
					.build();
			dishRepository.saveAll(List.of(dish1, dish2));
		};
	}
}
