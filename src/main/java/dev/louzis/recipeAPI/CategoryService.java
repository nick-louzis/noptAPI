package dev.louzis.recipeAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;
    public String addCategory(Category category){
        return "Category"+ categoryRepository.save(category);
    }

    public List<Category> getCat(){
        return categoryRepository.findAll();
    }

    public String updateCategory(Category category){
        Category categoryToChange = categoryRepository.findCategoryById(category.getId());
        String oldName = categoryToChange.getName();
        String newName = category.getName();
        categoryToChange.setName(newName);
        categoryRepository.save(categoryToChange);
        System.out.println(categoryToChange);
        return "Updated Category: "+oldName + " to "+ newName;
    }


}
