
package MovieDTO;

import entities.Movie;


public class MovieDTO {
    
    private Long id;
    private int year;
    private String title;
    private String[] actors;
    private double averageRating; 
    private String genre;

    public MovieDTO(Movie movieDTO) {
        this.id = movieDTO.getId();
        this.year = movieDTO.getYear();
        this.title = movieDTO.getTitle();
        this.actors = movieDTO.getActors();
        this.averageRating = movieDTO.getAverageRating();
        this.genre = movieDTO.getGenre();
    }

    public Long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public String[] getActors() {
        return actors;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "MovieDTO{" + "id=" + id + ", year=" + year + ", title=" + title + ", actors=" + actors + ", averageRating=" + averageRating + ", genre=" + genre + '}';
    }

}
