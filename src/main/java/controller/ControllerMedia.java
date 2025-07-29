package controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CJV.movie_management.model.Media;
import CJV.movie_management.service.ServiceMedia;

@RestController
@RequestMapping("/media")
public class ControllerMedia {
    private final ServiceMedia serviceMedia;

    public ControllerMedia(ServiceMedia serviceMedia) {
        this.serviceMedia = serviceMedia;
    }

    // 1- create movies/tv shows to be added to the database
    @PostMapping
    public Media addMedia(@RequestBody Media media) {
        return serviceMedia.addMedia(media);
    }

    // retrieves all the movies and tv shows in the database
    // @GetMapping
    // public ResponseEntity<List<Movie>> getAllMoviesTvs() {
    // List<Movie> movies = movieService.getAllMoviesTvs();
    // return ResponseEntity.ok(movies != null ? movies : List.of());
    // }

        // 2- retrieves all the movies in the database
    @GetMapping("/movie")
    public ResponseEntity<List<Media>> getAllMovies() {
        return ResponseEntity.ok(serviceMedia.getAllMovies());
    }

}
