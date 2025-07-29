package CJV.movie_management.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Media")
public class Media {
    @Id
    private String id;
    private String title;
    private String synopsis;
    private String poster;
    private String largePoster;
    private String rentPrice;
    private String buyPrice;
    private String category;
    private Boolean featured;
}
