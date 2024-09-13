package recipes.excpetion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Objects;

@ControllerAdvice
public class GeneralRecipeHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest webRequest) throws URISyntaxException {
        return ResponseEntity
                .status(400)
                .body(new ErrorMessage(LocalDate.now(), 400, "Bad Request",
                        new URI(((ServletWebRequest) webRequest).getRequest().getRequestURI()).getPath(),
                        Objects.requireNonNull(ex.getFieldError()).getDefaultMessage()));
    }}
