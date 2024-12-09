package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Award;

public class AwardDAO {
    private final static String UNIT_NAME = "jsfcourse-simplePU"; // Upewnij się, że nazwa jednostki pasuje do persistence.xml

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(AwardDAO award) {
        em.persist(award);
    }

    public AwardDAO merge(AwardDAO award) {
        return em.merge(award);
    }

    public void remove(AwardDAO award) {
        em.remove(em.merge(award));
    }

    public AwardDAO find(Object id) {
        return em.find(AwardDAO.class, id);
    }

    public List<Award> getFullList() {
        List<Award> list = null;

        Query query = em.createQuery("select ru from awards ru");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}

