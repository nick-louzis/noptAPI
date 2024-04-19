package dev.louzis.recipeAPI;

import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {
    Optional<Recipe> findRecipeByTitle(String title);

    Recipe findRecipeById(String id);
    @Query("{'category.id': ?0}")
    List<Recipe> findByCategoryQuery(String id);

    @Query("{'title': {'$regex': ?0, '$options': 'i'}}")
    List<Recipe> findRecipeByQuery(String title);
//    Recipe deleteRecipeById(String id);
//    Optional<Recipe> findRecipeByIdentifier(String identifier);
    //we need this method so we can later update a recipe
//    Optional<Recipe> findByRecipeById(ObjectId id);


    //optional function to findRecipeByTitle.
    //Spring is able to tell by its self what the function needs to do.
}
