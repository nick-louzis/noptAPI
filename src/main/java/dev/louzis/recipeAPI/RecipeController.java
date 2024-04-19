package dev.louzis.recipeAPI;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/food")
public class RecipeController {
    @Autowired // auto construct the Recipe Service
    private RecipeService recipeService;

    //Main route to send all data http://localhost:8080/food/api
    @GetMapping("/api")
    public ResponseEntity<List<Recipe>> allRecipes(){
        return new ResponseEntity<List<Recipe>>(recipeService.allRecipes(), HttpStatus.OK);
    }

    //Route to send a specific recipe http://localhost:8080/food/api/{title}
    @GetMapping("/api/{title}")
    //@PathVariable String title : from the Recipe, take the title and put it the url
    //this allows to search for a specific Recipe using only the name
    public ResponseEntity<Optional<Recipe>> singleRecipe(@PathVariable String title){
        return new ResponseEntity<Optional<Recipe>>(recipeService.singleRecipe(title),HttpStatus.OK);
        //return a new response entity with is of type Recipe (class) and its optional
        //through the service running the singleRecipe function with the title of the recipe given plus the http status
    }

    /*@PostMapping : In this url(http://localhost:8080/food/api/addRecipe) we are able to post data to our API
    * we request from Body the object of type Recipe, send it to the API and finally  */
    @PostMapping("/api/addRecipe")
    @ResponseStatus(HttpStatus.CREATED) //Send Status that the Obj is created
    public String addRecipe(@RequestBody Recipe recipe){
        Recipe newRecipe = recipeService.addRecipe(recipe); // save the new recipe in the DB
        return "Recipe Saved : " + newRecipe.getTitle(); // return a message with the name of the new recipe that's being saved
    }

    @GetMapping("/api/v1")
    public List<Recipe> match(@RequestParam("title") String title){
        String sanitizedInput = title.replaceAll("[^a-zA-Z0-9]", "");
        System.out.println(sanitizedInput);
        System.out.println(recipeService.matchRecipes(sanitizedInput));
        return recipeService.matchRecipes(sanitizedInput);
    }


    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<Optional<Recipe>> recipeById(@PathVariable String id){
        return new ResponseEntity<Optional<Recipe>>(recipeService.findRecipeById(id), HttpStatus.OK);
    }

    /*Delete the recipe by id and return the corresponding message
    * to do so wee need to get the id from the url (@PathVariable id) */
    @DeleteMapping("/api/{id}")
    public String deleteRecipe(@PathVariable String id){
        recipeService.deleteRecipe(id);
        return "Deleted the recipe with id : " + id ;
    }

    @PutMapping
    public String updateRecipe(@RequestBody Recipe recipe){

        return "Updated" + recipeService.updateOne(recipe);
    }

    @RequestMapping(value = "/**")
    @ResponseStatus(HttpStatus.NOT_FOUND) // Optional, if you want to explicitly set HTTP 404 status
    public ResponseEntity<Object> fallbackMethod() {
        // You can return any response entity here. For example, a simple error message:
        return new ResponseEntity<>("<div id=error>Request may not be fulfilled  .</div>", HttpStatus.NOT_FOUND);
    }



    @GetMapping("/api/categories/single")
    public List<Recipe> getByCategory(@RequestParam("id") String id ){
        return recipeService.getByCatrgory(id);
    }


}
