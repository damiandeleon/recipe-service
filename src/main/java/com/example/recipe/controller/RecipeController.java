package com.example.recipe.controller;

import com.example.recipe.model.Recipe;
import com.example.recipe.repository.RecipeRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
public class RecipeController {
    @Autowired
    RecipeRepository recipeRepo;


    //getAllRecipes
    @GetMapping("/recipes")
    @ResponseStatus(HttpStatus.OK)
    public List<Recipe> getAllRecipes(){
        return recipeRepo.findAll();
    }

    //updateRecipe
    @PutMapping("/recipes")
    public void updateRecipe(@RequestBody Recipe recipe){
        recipeRepo.save(recipe);
    }

    //addRecipe
    @PostMapping("/recipes")
    @ResponseStatus(HttpStatus.CREATED)
    public Recipe addRecipe(@RequestBody Recipe recipe){
        return recipeRepo.save(recipe);
    }

    //getRecipesByCategory
    @GetMapping("/recipes/category/{category}")
    public List<Recipe> getRecipesByCategory(@PathVariable String category){
       return recipeRepo.findAllRecipesByCategory(category);
    }

    //getRecipe
    @GetMapping("/recipes/{id}")
    public Recipe getRecipe(@PathVariable Integer id){
        Optional<Recipe> returnVal = recipeRepo.findById(id);
        if (returnVal.isPresent()){
            return returnVal.get();
        } else {
            return null;
        }
    }

    //deleteRecipe
    @DeleteMapping("/recipes/{id}")
    public void deleteRecipe(@PathVariable Integer id){
        recipeRepo.deleteById(id);
    }
}
