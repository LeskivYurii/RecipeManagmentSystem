package recipes.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import recipes.domain.User;
import recipes.domain.UserDetailsAdapter;
import recipes.excpetion.UserNotFoundException;
import recipes.repository.UserRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =

        return Optional
                .of(username)
                .flatMap(userRepository::findUserByEmailIgnoreCase)
                .orElseThrow(UserNotFoundException::new);;
    }

}
