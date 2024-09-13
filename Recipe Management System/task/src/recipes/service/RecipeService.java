package recipes.service;

import org.springframework.stereotype.Service;
import recipes.domain.Recipe;
import recipes.domain.UserDetailsAdapter;
import recipes.excpetion.DeleteRecipeException;
import recipes.excpetion.RecipeNotFoundException;
import recipes.excpetion.UpdateRecipeException;
import recipes.excpetion.SearchRecipesException;
import recipes.repository.RecipeRepository;

import java.util.*;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe findById(int id) {
        return Optional
                .of(id)
                .flatMap(recipeRepository::findById)
                .orElseThrow(RecipeNotFoundException::new);
    }

    public Map<String, Integer> create(UserDetailsAdapter userDetailsAdapter, Recipe recipe) {
        recipe.setUser(userDetailsAdapter.getUser());
        Recipe newRecipe = recipeRepository.save(recipe);
        return Map.of("id", newRecipe.getId());
    }

    public void delete(UserDetailsAdapter userDetailsAdapter, int id) {
        Optional
                .of(id)
                .flatMap(recipeRepository::findById)
                .map(recipe -> {
                    if(recipe.getUser().getId() != userDetailsAdapter.getUser().getId()) {
                        throw new DeleteRecipeException();
                    }
                    recipeRepository.delete(recipe);
                    return recipe;
                })
                .orElseThrow(RecipeNotFoundException::new);
    }

    public Recipe update(UserDetailsAdapter userDetailsAdapter, int id, Recipe recipe) {
        Recipe recipe1 = recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
        if(recipe1.getUser().getId() == userDetailsAdapter.getUser().getId()) {
            recipe.setId(id);
            recipe.setUser(userDetailsAdapter.getUser());
            return recipeRepository.save(recipe);
        }
        throw new UpdateRecipeException();
    }

    public List<Recipe> search(Map<String, String> params) {
        if(params.size() == 1 && (params.containsKey("name") || params.containsKey("category"))) {
            return params.containsKey("name")
                    ? recipeRepository.findByNameIgnoreCaseContainsOrderByDateDesc(params.get("name"))
                    : recipeRepository.findAllByCategoryIgnoreCaseOrderByDateDesc(params.get("category"));
        }

        throw new SearchRecipesException();
    }
}
