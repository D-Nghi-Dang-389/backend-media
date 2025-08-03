package CJV.movie_management.controller;

import CJV.movie_management.model.User;
import CJV.movie_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

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
        // Validate incoming fields
        if (loginData.getEmail() == null || loginData.getPassword() == null) {
            return ResponseEntity.badRequest().body("Email and password are required.");
        }

        boolean authenticated = userService.authenticate(loginData.getEmail(), loginData.getPassword());

        if (authenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
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
