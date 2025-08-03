package CJV.movie_management.service;

import CJV.movie_management.model.Media;
import CJV.movie_management.repository.RepositoryMedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaService {

    @Autowired
    private RepositoryMedia repositoryMedia;

    public Media addMedia(Media media) {
        return repositoryMedia.save(media);
    }

    public List<Media> getAllMedia() {
        return repositoryMedia.findAll();
    }

    public List<Media> getAllMovies() {
        return repositoryMedia.findByCategory("movie");
    }

    public List<Media> getAllTVShows() {
        return repositoryMedia.findByCategory("tv");
    }

    public List<Media> getByCategory(String category) {
        return repositoryMedia.findByCategory(category);
    }

    public List<Media> searchByTitle(String title) {
        return repositoryMedia.findByTitleContainingIgnoreCase(title);
    }

    public List<Media> getFeatured(String category) {
        return repositoryMedia.findByFeaturedTrueAndCategory(category);
    }

    public Optional<Media> getMediaById(String id) {
        return repositoryMedia.findById(id);
    }

    public Media updateMedia(String id, Media updated) {
        Optional<Media> optional = repositoryMedia.findById(id);
        if (optional.isPresent()) {
            Media media = optional.get();
            media.setTitle(updated.getTitle());
            media.setYear(updated.getYear());
            media.setSynopsis(updated.getSynopsis());
            media.setPoster(updated.getPoster());
            media.setLargePoster(updated.getLargePoster());
            media.setRentPrice(updated.getRentPrice());
            media.setBuyPrice(updated.getBuyPrice());
            media.setCategory(updated.getCategory());
            media.setGenre(updated.getGenre());
            media.setDuration(updated.getDuration());
            media.setRelease(updated.getRelease());
            media.setLanguage(updated.getLanguage());
            media.setFeatured(updated.isFeatured());
            return repositoryMedia.save(media);
        }
        return null;
    }

    public void deleteMedia(String id) {
        repositoryMedia.deleteById(id);
    }
}