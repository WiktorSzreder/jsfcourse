package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Role;

public class RoleDAO {
    private final static String UNIT_NAME = "jsfcourse-simplePU"; // Upewnij się, że nazwa jednostki pasuje do persistence.xml

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(RoleDAO role) {
        em.persist(role);
    }

    public RoleDAO merge(RoleDAO role) {
        return em.merge(role);
    }

    public void remove(RoleDAO role) {
        em.remove(em.merge(role));
    }

    public RoleDAO find(Object id) {
        return em.find(RoleDAO.class, id);
    }

    public List<Role> getFullList() {
        List<Role> list = null;

        Query query = em.createQuery("select ru from actors ru");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}

