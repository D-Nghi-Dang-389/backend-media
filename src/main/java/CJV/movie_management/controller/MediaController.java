package CJV.movie_management.controller;

import CJV.movie_management.model.Media;
import CJV.movie_management.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    // Get all movies and Tv shows
    @GetMapping
    public ResponseEntity<?> getAllMedia() {
        return ResponseEntity.ok(mediaService.getAllMedia());
    }

    // 1. Create a movie or TV show (/media)
    @PostMapping
    public ResponseEntity<?> createMedia(@RequestBody Media media) {
        if (media.getTitle() == null || media.getCategory() == null) {
            return ResponseEntity.badRequest().body("Title and category are required.");
        }
        return ResponseEntity.ok(mediaService.addMedia(media));
    }

    // 2. Retrieves all the movies (/media/movies)
    @GetMapping("/movies")
    public ResponseEntity<?> getAllMovies() {
        return ResponseEntity.ok(mediaService.getByCategory("movie"));
    }

    // 3. Retrives all the TV shows (media/tvshows)
    @GetMapping("/tvshows")
    public ResponseEntity<?> getAllTVShows() {
        return ResponseEntity.ok(mediaService.getByCategory("tv"));
    }

    // 4. Search by title (/media/serch?title=keyword)
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String title) {
        return ResponseEntity.ok(mediaService.searchByTitle(title));
    }

    // 5 & 6. Retrieves featured movies (/media/featured?category=....)
    @GetMapping("/featured")
    public ResponseEntity<?> getFeatured(@RequestParam String category) {
        return ResponseEntity.ok(mediaService.getFeatured(category));
    }

    // 7. Get specific movie/tv by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        Optional<Media> media = mediaService.getMediaById(id);
        if (media.isPresent()) {
            return ResponseEntity.ok(media.get());
        } else {
            return ResponseEntity.badRequest().body("Invalid ID");
        }
    }

    // 8. Update an existing media
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Media updated) {
        Media media = mediaService.updateMedia(id, updated);
        if (media == null) {
            return ResponseEntity.badRequest().body("Update failed: invalid ID or missing data.");
        }
        return ResponseEntity.ok(media);
    }

    // 9. Delete a media by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        mediaService.deleteMedia(id);
        return ResponseEntity.ok("Deleted successfully.");
    }
}
