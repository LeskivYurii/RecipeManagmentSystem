package recipes.excpetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "You can update only your recipe!")
public class UpdateRecipeException extends RuntimeException{
}
