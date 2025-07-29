package CJV.movie_management.repository;

import CJV.movie_management.model.Media;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryMedia extends MongoRepository<Media, String> {




    
}
