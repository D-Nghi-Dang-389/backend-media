package CJV.movie_management.service;

import CJV.movie_management.model.User;
import CJV.movie_management.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private RepositoryUser repositoryUser;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User register(User user) {
        if (repositoryUser.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return repositoryUser.save(user);
    }

    public Optional<User> findById(String id) {
        return repositoryUser.findById(id);
    }

    public boolean authenticate(String email, String password) {
        Optional<User> userOpt = repositoryUser.findByEmail(email);
        return userOpt.isPresent() && encoder.matches(password, userOpt.get().getPassword());
    }

}
