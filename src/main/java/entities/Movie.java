package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity
//@NamedQuery(name = "RenameMe.deleteAllRows", query = "DELETE from RenameMe")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private String title;
    private String [] actors;
    private double averageRating;
    private String genre;
    private double internalRating;
    
    public Movie() {
    }

    public Movie(int year, String title, String[] actors, double averageRating, String genre, double internalRating) {
        this.year = year;
        this.title = title;
        this.actors = actors;
        this.averageRating = averageRating;
        this.genre = genre;
        this.internalRating = internalRating;
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getInternalRating() {
        return internalRating;
    }

    public void setInternalRating(double internalRating) {
        this.internalRating = internalRating;
    }
    
    
    
    

   
}
