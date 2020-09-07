package facades;

import MovieDTO.MovieDTO;
import utils.EMF_Creator;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class FacadeMovieTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    private static String[] arr1 = {"Steve"};
    private static Movie test1 = null;

    public FacadeMovieTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MovieFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        test1 = new Movie(2020, "Movie1", arr1, 7.0, "Comedy", 5.0);
        try {
            em.getTransaction().begin();

            // Giver compile fejl:
            //    em.createNamedQuery("BankCustomer.deleteAllRows").executeUpdate();
            // Lavet en midlertidig anden løsning med samme formål som ovenfor
            Query q3 = em.createQuery("DELETE FROM Movie");
            q3.executeUpdate();
            em.createNativeQuery("ALTER TABLE MOVIE AUTO_INCREMENT = 1").executeUpdate();
            em.persist(new Movie(2020, "Movie1", arr1, 7.0, "Comedy", 5.0));
            //em.persist(new Movie(2007, "Movie2", arr1, 4.0, "Action", 3.0));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void getMovieByID() {
        int id = 1;
        // The object test1 is initialized in the setUp() method.
        MovieDTO movDTO = new MovieDTO(test1);
        MovieDTO expected = movDTO;
        
        MovieDTO result = facade.getMovieByID(id);
        
        // ID is initialized in DB, and not core, so lets compare by something else.
        assertEquals(expected.getTitle(), result.getTitle());
    }
    
    @Test
    public void getMovieByTitle() {
        String title = "Movie1";
        
        List<MovieDTO> expected = new ArrayList();
        MovieDTO movDTO = new MovieDTO(test1);
        
        expected.add(movDTO);
        
        List<MovieDTO> result = facade.getMovieByTitle(title);
        
        assertEquals(expected.get(0).getTitle(), result.get(0).getTitle());
    }
    
    @Test
    public void getAllMovies() {
        List<MovieDTO> result = facade.getAllMovies();
        assertThat(result, hasSize(1));
    }
    
    @Test
    public void addMovie() {
        Movie movtest1 = new Movie(2020, "MovienewTest", arr1, 3.0, "Thriller", 4.0);
        facade.addMovie(movtest1);

        // Så henter vi kunderne og tjekker om den er blevet tilføjet.
        List<MovieDTO> movieList = facade.getAllMovies();

        assertThat(movieList, hasSize(2));
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    /*  @Test
    public void testAFacadeMethod() {
        assertEquals(2, facade.getRenameMeCount(), "Expects two rows in the database");
    }*/
}
