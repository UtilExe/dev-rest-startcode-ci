package facades;

import MovieDTO.MovieDTO;
import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    //Private Constructor to ensure Singleton
    private MovieFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public MovieDTO getMovieByID(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Movie movie1 = em.find(Movie.class, id);
            return new MovieDTO(movie1);
        } finally {
            em.close();
        }
    }
    
    public MovieDTO getMovieByTitle(String title) {
        EntityManager em = emf.createEntityManager();
        try {
            Movie query = em.createQuery(
                    "SELECT p from Movie p WHERE p.title = :title", Movie.class).
                    setParameter("title", title).getSingleResult();
            // Consider changing to getResultList(), so it displays the list with all customers that has lastName X. 
            return new MovieDTO(query);
        } catch (NoResultException nre) {
            /* If the lastName doesn't exist, it will result in an Exception crash.
           Added an exception check to prevent it from crashing. persistence.NoResultException.
             */
            System.out.println("Could not find: " + title);
            return null;
        } finally {
            em.close();
        }
    }
    
    public Movie addMovie(Movie movie) {
        Movie movies = new Movie(movie.getYear(), movie.getTitle(), movie.getActors(), movie.getAverageRating(),
                movie.getGenre(), movie.getInternalRating());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
            return movies;
          //  return result;
        } finally {
            em.close();
        }
    }
    
    public List<Movie> getAllMovies() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Movie> query 
                    = em.createQuery("Select p from Movie p", Movie.class);
            List<Movie> result = query.getResultList();

            return result;
        } finally {
            em.close();
        }
    }
    
    //TODO Remove/Change this before use
    public long getRenameMeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
        
    }

}
