package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.RegisteredUser;
import com.jsf.entities.Role; 

// DAO - Data Access Object dla encji RegisteredUser
@Stateless
public class RegisteredUserDAO {
    private final static String UNIT_NAME = "jsfcourse-simplePU"; // Upewnij się, że nazwa jednostki pasuje do persistence.xml

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(RegisteredUser registeredUser) {
        em.persist(registeredUser);
    }

    public RegisteredUser merge(RegisteredUser registeredUser) {
        return em.merge(registeredUser);
    }

    public void remove(RegisteredUser registeredUser) {
        em.remove(em.merge(registeredUser));
    }

    public RegisteredUser find(Object id) {
        return em.find(RegisteredUser.class, id);
    }

    public List<RegisteredUser> getFullList() {
        List<RegisteredUser> list = null;

        Query query = em.createQuery("select ru from RegisteredUser ru");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<RegisteredUser> getList(Map<String, Object> searchParams) {
        List<RegisteredUser> list = null;

        String select = "select ru ";
        String from = "from RegisteredUser ru ";
        String where = "";
        String orderby = "order by ru.username asc";

        // Dodaj warunki wyszukiwania
        String username = (String) searchParams.get("username");
        if (username != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "ru.username like :username ";
        }

        String email = (String) searchParams.get("email");
        if (email != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "ru.email like :email ";
        }

        Query query = em.createQuery(select + from + where + orderby);

        // Ustaw parametry
        if (username != null) {
            query.setParameter("username", username + "%");
        }
        if (email != null) {
            query.setParameter("email", email + "%");
        }

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
