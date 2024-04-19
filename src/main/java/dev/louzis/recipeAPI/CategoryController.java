package dev.louzis.recipeAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/food/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @PostMapping("/add")
    public String addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
        return  "New Category Added :"+ category;
    }

    @GetMapping("/all")
    public List<Category> getCat(){
        return categoryService.getCat();
    }

    @PutMapping("/update")
    public String updateCategory(@RequestBody Category category){
        return categoryService.updateCategory(category);
    }

}
