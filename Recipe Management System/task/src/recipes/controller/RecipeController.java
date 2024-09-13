package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import recipes.domain.Recipe;
import recipes.domain.UserDetailsAdapter;
import recipes.service.RecipeService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;


    @GetMapping("/{id}")
    public Recipe get(@PathVariable int id) {
        return recipeService.findById(id);
    }

    @PostMapping("/new")
    public Map<String, Integer> add(@AuthenticationPrincipal UserDetailsAdapter userDetailsAdapter, @Valid @RequestBody Recipe recipe) {
        return recipeService.create(userDetailsAdapter, recipe);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal UserDetailsAdapter userDetailsAdapter, @PathVariable int id) {
        recipeService.delete(userDetailsAdapter, id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Recipe update(@AuthenticationPrincipal UserDetailsAdapter userDetailsAdapter, @PathVariable int id, @Valid @RequestBody Recipe recipe) {
        return recipeService.update(userDetailsAdapter, id, recipe);
    }

    @GetMapping(value = "/search")
    public List<Recipe> search(@RequestParam Map<String, String> params) {
        return recipeService.search(params);
    }

}
