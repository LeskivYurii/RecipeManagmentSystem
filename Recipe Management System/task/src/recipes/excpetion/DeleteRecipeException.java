package recipes.excpetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "You can delete only your recipes!")
public class DeleteRecipeException extends RuntimeException{

}
