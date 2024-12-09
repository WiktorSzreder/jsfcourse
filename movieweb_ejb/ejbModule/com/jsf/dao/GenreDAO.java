 package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Genre;

public class GenreDAO {
    private final static String UNIT_NAME = "jsfcourse-simplePU"; // Upewnij się, że nazwa jednostki pasuje do persistence.xml

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(GenreDAO genre) {
        em.persist(genre);
    }

    public GenreDAO merge(GenreDAO genre) {
        return em.merge(genre);
    }

    public void remove(GenreDAO genre) {
        em.remove(em.merge(genre));
    }

    public GenreDAO find(Object id) {
        return em.find(GenreDAO.class, id);
    }

    public List<Genre> getFullList() {
        List<Genre> list = null;

        Query query = em.createQuery("select ru from genre ru");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}

