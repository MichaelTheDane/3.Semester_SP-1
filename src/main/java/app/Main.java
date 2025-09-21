package app;

import app.DAO.MovieDAO;
import app.config.HibernateConfig;
import app.entities.Movie;
import jakarta.persistence.EntityManagerFactory;

public class Main
{

    public static void main(String[] args)
    {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        MovieDAO movieDAO = new MovieDAO(emf);

        // Create
        Movie newMovie = Movie.builder()
                .adult(true)
                .video(true)
                .originalTitle("Title")
                .popularity(2.0)
                .build();

        // TODO: Set parameters for the created sample movie
        Movie createdMovie = movieDAO.save(newMovie);

        // Read
        // TODO: Remember to make the getters for this to work
        Movie foundMovie = movieDAO.findById(createdMovie.getId());
        System.out.println("Found Movie: " + foundMovie.getOriginalTitle());

        // Update
        // TODO: Need the setters made and then sample changes
        Movie updatedMovie = movieDAO.update(foundMovie);
        System.out.println("Updated Movie Age: " + updatedMovie.getAdult());

        // Delete
        // TODO: Eventually make the deletion ability
        //movieDAO.delete(createdMovie.getId());

        movieDAO.close();
    }
}