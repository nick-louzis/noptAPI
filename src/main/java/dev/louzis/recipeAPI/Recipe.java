package dev.louzis.recipeAPI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "recipes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    @Id
    private String id;
//    private String identifier = UUID.randomUUID().toString().split("-")[0];
    private String title;
    private String prep_time;
    @DocumentReference
    private List<Category> category;
    private List<String> ingredients;
    private List<Object> instructions;
    private String difficulty;
    private int servings;
    private String main_image;
}
