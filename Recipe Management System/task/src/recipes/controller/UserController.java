package recipes.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import recipes.domain.UserRegisterRequest;
import recipes.service.UserService;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/register")
    public void create(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        userService.create(userRegisterRequest);
    }
}
