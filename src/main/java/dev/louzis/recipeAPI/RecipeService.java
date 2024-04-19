package dev.louzis.recipeAPI;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;
    // auto construct the repository

    @Autowired
    private CategoryRepository categoryRepository;
    public List<Recipe> allRecipes() { return recipeRepository.findAll();}
    //function witch returns all the entries from db in a List with the Recipe Schema

    public Optional<Recipe> singleRecipe(String title) {
        return recipeRepository.findRecipeByTitle(title);
    }
    // function to return recipe by the name given using the repositories function "findRecipeByTitle(x)"

    public Recipe addRecipe(Recipe recipe){
        return recipeRepository.save(recipe);
    }
    //function to add recipe in db

    /*delete the object by its id,
    * no need to return cause deleteById() its type of void  */
    public void deleteRecipe(String id){
        recipeRepository.deleteById(id);

    }

    public Optional<Recipe> findRecipeById(String id){
        return recipeRepository.findById(id);
    }

    public List<Recipe> matchRecipes(String title){
        //regular expression query with a ^ (caret) to indicate the start of the string
        return recipeRepository.findRecipeByQuery("^"+ title);
    }
    public Recipe updateOne(Recipe recipe){
        System.out.println(recipe);
        Recipe selected;
        selected = recipeRepository.findRecipeById(recipe.getId());
        selected.setServings(recipe.getServings());
        selected.setInstructions(recipe.getInstructions());
        selected.setTitle(recipe.getTitle());
        selected.setPrep_time(recipe.getPrep_time());
        selected.setIngredients(recipe.getIngredients());
        selected.setCategory(recipe.getCategory());
        System.out.println(selected);
//        return null;
        return recipeRepository.save(selected);
    }



    public List<Recipe> getByCatrgory(String id){
        return recipeRepository.findByCategoryQuery(id);
    }
}


