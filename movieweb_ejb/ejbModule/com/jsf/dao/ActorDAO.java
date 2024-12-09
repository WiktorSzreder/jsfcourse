package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Actor;

public class ActorDAO {
    private final static String UNIT_NAME = "jsfcourse-simplePU"; // Upewnij się, że nazwa jednostki pasuje do persistence.xml

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(ActorDAO actor) {
        em.persist(actor);
    }

    public ActorDAO merge(ActorDAO actor) {
        return em.merge(actor);
    }

    public void remove(ActorDAO actor) {
        em.remove(em.merge(actor));
    }

    public ActorDAO find(Object id) {
        return em.find(ActorDAO.class, id);
    }

    public List<Actor> getFullList() {
        List<Actor> list = null;

        Query query = em.createQuery("select ru from actors ru");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}

