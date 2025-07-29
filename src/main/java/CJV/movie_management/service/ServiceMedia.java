package CJV.movie_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import CJV.movie_management.model.Media;
import CJV.movie_management.repository.RepositoryMedia;

@Service

public class ServiceMedia {
    private final RepositoryMedia repositoryMedia;

    public ServiceMedia(RepositoryMedia repositoryMedia) {
        this.repositoryMedia = repositoryMedia;
    }

    public Media addMedia(Media media) {
        return repositoryMedia.save(media);

    }

    public List<Media> getAllMedia() {
        return repositoryMedia.findAll();
    }

     
}
