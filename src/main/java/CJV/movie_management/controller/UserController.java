package CJV.movie_management.controller;

import CJV.movie_management.model.User;
import CJV.movie_management.repository.RepositoryUser;
import CJV.movie_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private RepositoryUser repositoryUser;

    @Autowired
    private UserService userService;

    // 1. Register a new user (/users/register)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            if (user.getFirstName() == null || user.getEmail() == null || user.getPassword() == null) {
                return ResponseEntity.badRequest().body("Missing required fields");
            }
            User saved = userService.register(user);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 2. Login a user (/users/login)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginData) {
        if (loginData.getEmail() == null || loginData.getPassword() == null) {
            return ResponseEntity.badRequest().body("Email and password are required.");
        }

        Optional<User> userOpt = repositoryUser.findByEmail(loginData.getEmail());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            boolean authenticated = userService.authenticate(user.getEmail(), loginData.getPassword());

            if (authenticated) {
                user.setPassword(null);
                return ResponseEntity.ok(user);
            }
        }

        return ResponseEntity.status(401).body("Invalid email or password");
    }

    // 3. Get user by ID (/users/{id})
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.badRequest().body("Invalid user ID");
        }
    }

}
