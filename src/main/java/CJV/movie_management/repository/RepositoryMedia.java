package CJV.movie_management.repository;

import CJV.movie_management.model.Media;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryMedia extends MongoRepository<Media, String> {
    List<Media> findByCategory(String category);

    List<Media> findByTitleContainingIgnoreCase(String title);

    List<Media> findByFeaturedTrueAndCategory(String category);
}
