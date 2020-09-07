package facades;

import MovieDTO.MovieDTO;
import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
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
    
    public List<MovieDTO> getMovieByTitle(String title) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Movie> query = em.createQuery("SELECT p from Movie p WHERE p.title = :title", Movie.class);
            query.setParameter("title", title);
            List<Movie> es = query.getResultList();
            List<MovieDTO> result = new ArrayList();
            for(Movie e : es) {
                result.add(new MovieDTO(e));
            }
            return result;
        } finally {
            em.close();
        }
    }
    
    public List<MovieDTO> getAllMovies() {
      EntityManager em = emf.createEntityManager();
        try {
            List<MovieDTO> listen = new ArrayList<>();
            TypedQuery<Movie> query = em.createQuery("SELECT e FROM Movie e", Movie.class);
            List<Movie> list = query.getResultList();
            for (Movie employee : list) {
                listen.add(new MovieDTO(employee));
            }
            
            return listen;
        } finally {
            em.close();
        }
    }
        public Movie addMovie(Movie mov) {
        Movie movie = new Movie(mov.getYear(), mov.getTitle(), mov.getActors(), 
                mov.getAverageRating(),  mov.getGenre(), mov.getInternalRating());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
            return mov;
          //  return result;
        } finally {
            em.close();
        }

 /*   public List<Movie> getAllMovies() {
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
    */
    }
}
