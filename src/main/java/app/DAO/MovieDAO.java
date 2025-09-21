package app.DAO;

import app.entities.Movie;
import jakarta.persistence.*;
import java.util.List;

import app.services.*;

public class MovieDAO {

    private EntityManagerFactory emf;
    private String JsonFile;

    public MovieDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public String GetJsonFile(String Uri) {
        ApiFetchers fetcher = new ApiFetchers();
        return fetcher.fetchFromApi(Uri);
    }

    public List<Movie> mapJsonToList(String json) {
        MovieMapperService mapper = new MovieMapperService();
        return mapper.mapJsonToMovies(json);
    }

    public List<Movie> GetJsonMapped(String Uri) {
        return mapJsonToList(GetJsonFile(Uri));
    }

    public Movie save(Movie Movie)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(Movie);
        em.getTransaction().commit();
        em.close();
        return Movie;
    }

    public List<Movie> saveAll(List<Movie> movies) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            for (Movie movie : movies) {
                em.persist(movie);
            }
        }
        catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }

        em.getTransaction().commit();
        em.close();

        return movies;
    }


    public Movie findById(int id)
    {
        EntityManager em = emf.createEntityManager();
        Movie foundMovie = em.find(Movie.class, id);
        em.close();
        return foundMovie;
    }

    public Movie update(Movie Movie)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Movie updatedMovie = em.merge(Movie);
        em.getTransaction().commit();
        em.close();
        return updatedMovie;
    }

    public void delete(int id)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Movie Movie = findById(id);
        if (Movie != null)
        {
            em.remove(Movie);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void close()
    {
        emf.close();
    }
}
