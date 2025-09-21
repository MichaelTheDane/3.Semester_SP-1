package app;

import app.DAO.MovieDAO;
import app.config.HibernateConfig;
import app.entities.Movie;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class MainAlternate
{

    public static void main(String[] args)
    {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        MovieDAO movieDAO = new MovieDAO(emf);

        // Insert URI here:
        String Uri = "https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1";

        // Fetch the JSON
        String JSON = movieDAO.GetJsonFile(Uri);
        if (JSON.length() > 10) {
            System.out.print("JSON has been found");
        }

        // Make entities list from it
        List<Movie> movies = movieDAO.mapJsonToList(JSON);
        if (!movies.isEmpty()) {
            System.out.println("Movies have been listed from JSON");
        }

        // Save entities to database
        movieDAO.saveAll(movies);

        movieDAO.close();
    }
}