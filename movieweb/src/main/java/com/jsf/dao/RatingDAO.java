package com.jsf.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Rating;

@Stateless
public class RatingDAO {
    private final static String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    // Create a new rating
    public void create(Rating rating) {
        em.persist(rating);
    }

    // Update an existing rating
    public Rating merge(Rating rating) {
        return em.merge(rating);
    }

    // Remove a rating
    public void remove(Rating rating) {
        em.remove(rating);
    }
    public void delete(int r) {
        Query query = em.createQuery("DELETE FROM Rating r WHERE r.id = :id");
        query.setParameter("id", r);
        query.executeUpdate();
    }
    

    // Find a rating by ID
    public Rating find(Object id) {
        return em.find(Rating.class, id);
    }

    // Retrieve a full list of ratings
    public List<Rating> getFullList() {
        List<Rating> list = null;

        Query query = em.createQuery("SELECT r FROM Rating r");

        try {   
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Retrieve a filtered list of ratings based on search parameters
    public List<Rating> getList(Map<String, Object> searchParams) {
        List<Rating> list = null;

        // 1. Build query string with parameters
        String select = "SELECT r ";
        String from = "FROM Rating r ";
        String where = "";
        String orderby = "ORDER BY r.watchDate DESC";

        // Search by watch date range
        Date watchDateFrom = (Date) searchParams.get("watchDateFrom");
        Date watchDateTo = (Date) searchParams.get("watchDateTo");
        if (watchDateFrom != null) {
            if (where.isEmpty()) {
                where = "WHERE ";
            } else {
                where += "AND ";
            }
            where += "r.watchDate >= :watchDateFrom ";
        }
        if (watchDateTo != null) {
            if (where.isEmpty()) {
                where = "WHERE ";
            } else {
                where += "AND ";
            }
            where += "r.watchDate <= :watchDateTo ";
        }

        // Search by user ID
        Integer userId = (Integer) searchParams.get("userId");
        if (userId != null) {
            if (where.isEmpty()) {
                where = "WHERE ";
            } else {
                where += "AND ";
            }
            where += "r.registeredUserIdregisteredUser.idregisteredUser = :userId ";
        }

        // Search by movie ID
        Integer movieId = (Integer) searchParams.get("movieId");
        if (movieId != null) {
            if (where.isEmpty()) {
                where = "WHERE ";
            } else {
                where += "AND ";
            }
            where += "r.moviesIdmovies.idmovies = :movieId ";
        }

        // 2. Create query object
        Query query = em.createQuery(select + from + where + orderby);

        // 3. Set parameters for the query
        if (watchDateFrom != null) {
            query.setParameter("watchDateFrom", watchDateFrom);
        }
        if (watchDateTo != null) {
            query.setParameter("watchDateTo", watchDateTo);
        }
        if (userId != null) {
            query.setParameter("userId", userId);
        }
        if (movieId != null) {
            query.setParameter("movieId", movieId);
        }

        // 4. Execute query and retrieve list of Rating objects
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
