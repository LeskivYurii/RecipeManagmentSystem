package recipes.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegisterRequest {

    @Pattern(regexp = "\\w+@\\w+\\.\\w+")
    private String email;
    @Size(min = 8)
    @NotBlank
    private String password;

    public UserRegisterRequest() {
    }

    public UserRegisterRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
