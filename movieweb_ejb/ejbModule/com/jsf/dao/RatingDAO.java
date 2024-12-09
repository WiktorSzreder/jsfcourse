package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Rating;

public class RatingDAO {
    private final static String UNIT_NAME = "jsfcourse-simplePU"; 

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(RatingDAO rating) {
        em.persist(rating);
    }

    public RatingDAO merge(RatingDAO rating) {
        return em.merge(rating);
    }

    public void remove(RatingDAO rating) {
        em.remove(em.merge(rating));
    }

    public RatingDAO find(Object id) {
        return em.find(RatingDAO.class, id);
    }

    public List<Rating> getFullList() {
        List<Rating> list = null;

        Query query = em.createQuery("select ru from rating ru");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}

