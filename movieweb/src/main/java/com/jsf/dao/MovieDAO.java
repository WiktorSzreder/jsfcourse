package com.jsf.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Movie;

@Stateless
public class MovieDAO {
    private final static String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    // Create a new movie
    public void create(Movie movie) {
        em.persist(movie);
    }

    // Update an existing movie
    public Movie merge(Movie movie) {
        return em.merge(movie);
    }

    // Remove a movie
    public void remove(Movie movie) {
        em.remove(em.merge(movie));
    }

    // Find a movie by ID
    public Movie find(Object id) {
        return em.find(Movie.class, id);
    }

    // Retrieve a full list of movies
    public List<Movie> getFullList() {
        List<Movie> list = null;

        Query query = em.createQuery("SELECT m FROM Movie m");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Retrieve a filtered list of movies based on search parameters
    public List<Movie> getList(Map<String, Object> searchParams) {
        List<Movie> list = null;

        // 1. Build query string with parameters
        String select = "SELECT m ";
        String from = "FROM Movie m ";
        String where = "";
        String orderby = "ORDER BY m.title ASC";

        // Search by title
        String title = (String) searchParams.get("title");
        if (title != null) {
            if (where.isEmpty()) {
                where = "WHERE ";
            } else {
                where += "AND ";
            }
            where += "m.title LIKE :title ";
        }

        // Search by genre
        Integer genreId = (Integer) searchParams.get("genreId");
        if (genreId != null) {
            if (where.isEmpty()) {
                where = "WHERE ";
            } else {
                where += "AND ";
            }
            where += "m.genreIdgenre.idgenre = :genreId ";
        }

        // Search by release date range
        Date releaseDateFrom = (Date) searchParams.get("releaseDateFrom");
        Date releaseDateTo = (Date) searchParams.get("releaseDateTo");
        if (releaseDateFrom != null) {
            if (where.isEmpty()) {
                where = "WHERE ";
            } else {
                where += "AND ";
            }
            where += "m.releaseDate >= :releaseDateFrom ";
        }
        if (releaseDateTo != null) {
            if (where.isEmpty()) {
                where = "WHERE ";
            } else {
                where += "AND ";
            }
            where += "m.releaseDate <= :releaseDateTo ";
        }

        // 2. Create query object
        Query query = em.createQuery(select + from + where + orderby);

        // 3. Set parameters for the query
        if (title != null) {
            query.setParameter("title", title + "%");
        }
        if (genreId != null) {
            query.setParameter("genreId", genreId);
        }
        if (releaseDateFrom != null) {
            query.setParameter("releaseDateFrom", releaseDateFrom);
        }
        if (releaseDateTo != null) {
            query.setParameter("releaseDateTo", releaseDateTo);
        }

        // 4. Execute query and retrieve list of Movie objects
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
