package com.example.recipe.controller;

import com.example.recipe.model.Rating;
import com.example.recipe.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@RestController
public class RatingController {
    @Autowired
    RatingRepository repo;

    //updateRating
    @PutMapping("/ratings")
    public void updateRating(@RequestBody Rating rating){
        repo.save(rating);
    }

    //addRating
    @PostMapping("/ratings")
    @ResponseStatus(HttpStatus.CREATED)
    public Rating addRating(@RequestBody Rating rating){
        return repo.save(rating);
    }

    //getRatingsByRecipe
    @GetMapping("/ratings/recipe/{id}")
    public List<Rating> getRatingsByRecipe(@PathVariable Integer id) {
        return repo.findAllRatingsByRecipeId(id);
    }

    //getRating
    @GetMapping("/ratings/{id}")
    public Rating getRating(@PathVariable Integer id) {
        Optional<Rating> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    //deleteRating
    @DeleteMapping("/ratings/{id}")
    public void deleteRating(@PathVariable Integer id){
        repo.deleteById(id);
    }

}
