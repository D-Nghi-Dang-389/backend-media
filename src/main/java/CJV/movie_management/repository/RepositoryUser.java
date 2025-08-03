package CJV.movie_management.repository;

import CJV.movie_management.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RepositoryUser extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
