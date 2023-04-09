package com.example.recipeBook.services;

import com.example.recipeBook.exception.validationException;
import com.example.recipeBook.model.Ingredient;
import com.example.recipeBook.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service

public class RecipeServiceImpl implements RecipeService {
    private  static long idCount = 1;
    private final Map<Long, Recipe> recipes = new HashMap<>();
    private final ValidationService validationService;

    @Override
    public Recipe save(Recipe recipe) {
        return recipes.put(idCount++, recipe);
    }

    @Override
    public Recipe getById(Long id) {
        return recipes.get(id);
    }

    @Override
    public Recipe update(Long id, Recipe recipe) {
        if (!validationService.validate(Recipe recipe)) {
            throw new validationException(recipe.toString());
        }
        return recipes.replace(id, recipe);
    }

    @Override
    public Recipe delete(Long id) {
        return recipes.remove(id);
    }

    @Override
    public Map<Long, Recipe> getAll() {
        return recipes;
    }


}
