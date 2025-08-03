package CJV.movie_management.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor //Generates a no-args constructor
@AllArgsConstructor //Generates a constructor with all fields
@Document(collection = "Media")
public class Media {

    @Id
    private String id;

    private String title;
    private int year;
    private String synopsis;
    private String poster;
    private String largePoster;
    private double rentPrice;
    private double buyPrice;
    private String category;
    private String genre;
    private String duration;
    private String release;
    private String language;
    private boolean featured;
}
