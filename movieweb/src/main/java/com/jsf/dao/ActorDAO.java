package com.jsf.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Actor;

@Stateless
public class ActorDAO {
    private final static String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    // Create a new actor
    public void create(Actor actor) {
        em.persist(actor);
    }

    // Update an existing actor
    public Actor merge(Actor actor) {
        return em.merge(actor);
    }

    // Remove a actor
    public void remove(Actor actor) {
        em.remove(em.merge(actor));
    }

    // Retrieve a full list of actors
    public List<Actor> getFullList() {
        List<Actor> list = null;

        Query query = em.createQuery("SELECT a FROM Actor a");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    
}
