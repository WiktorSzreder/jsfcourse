package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Movy;
import com.jsf.entities.Role; 

public class MovyDAO {
    private final static String UNIT_NAME = "jsfcourse-simplePU"; // Upewnij się, że nazwa jednostki pasuje do persistence.xml

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(MovyDAO movy) {
        em.persist(movy);
    }

    public MovyDAO merge(MovyDAO movy) {
        return em.merge(movy);
    }

    public void remove(MovyDAO movy) {
        em.remove(em.merge(movy));
    }

    public MovyDAO find(Object id) {
        return em.find(MovyDAO.class, id);
    }

    public List<Movy> getFullList() {
        List<Movy> list = null;

        Query query = em.createQuery("select ru from movies ru");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}

