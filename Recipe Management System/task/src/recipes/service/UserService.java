package recipes.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import recipes.domain.User;
import recipes.domain.UserRegisterRequest;
import recipes.excpetion.UserAlreadyExistException;
import recipes.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void create(UserRegisterRequest userRegisterRequest) {
        Optional
                .of(userRegisterRequest)
                .filter(userRegisterRequest1 -> !userRepository.existsByEmailIgnoreCase(userRegisterRequest1.getEmail()))
                .map(userRegisterRequest1 -> new User(userRegisterRequest1.getEmail(), passwordEncoder.encode(
                        userRegisterRequest1.getPassword())))
                .map(userRepository::save)
                .orElseThrow(UserAlreadyExistException::new);

    }
}
