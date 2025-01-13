package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.User;  // Import the User entity
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;

@Stateless
public class UserDAO {
    private final static String UNIT_NAME = "jsfcourse-simplePU";

    // Dependency injection of EntityManager
    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    // Create method for User
    public void create(User user) {
        em.persist(user);
    }

    // Merge method for User (used to update an existing entity)
    public User merge(User user) {
        return em.merge(user);
    }

    // Remove method for User
    public void remove(User user) {
        em.remove(em.merge(user));
    }

    // Find method to retrieve User by ID
    public User find(Object id) {
        return em.find(User.class, id);
    }

    // Get all Users
    public List<User> getFullList() {
        List<User> list = null;

        Query query = em.createQuery("select u from User u");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
// Znajdź użytkownika na podstawie emaila czy nie istnieje juz w bazie danych
public User findByEmail(String email) {
    User user = null;

    try {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :email");
        query.setParameter("email", email);
        user = (User) query.getSingleResult();
    } catch (jakarta.persistence.NoResultException e) {
        // Brak użytkownika o podanym e-mailu
        user = null;
    } catch (Exception e) {
        e.printStackTrace();
    }

    return user;
}

    // Get a list of Users based on search parameters (like username, email, etc.)
    public List<User> getList(Map<String, Object> searchParams) {
        List<User> list = null;

        // 1. Build query string with parameters
        String select = "select u ";
        String from = "from User u ";
        String where = "";
        String orderby = "order by u.username asc";  // Sorting by username as an example

        // search for username
        String username = (String) searchParams.get("username");
        if (username != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "u.username like :username ";
        }

        // search for email
        String email = (String) searchParams.get("email");
        if (email != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "u.email like :email ";
        }

        // 2. Create query object
        Query query = em.createQuery(select + from + where + orderby);

        // 3. Set parameters if provided
        if (username != null) {
            query.setParameter("username", username + "%");
        }
        if (email != null) {
            query.setParameter("email", email + "%");
        }

        // 4. Execute query and retrieve list of User objects
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
       public User getUserFromDatabase(String email, String password) {
        try {
            TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.email = :email AND u.password = :password", 
                User.class
            );
            query.setParameter("email", email);
            query.setParameter("password", password); // Zmień na haszowane hasło, jeśli je haszujesz
            return query.getSingleResult();
        } catch (Exception e) {
            return null; // Jeśli nie znaleziono użytkownika
        }
    }
public List<String> getUserRolesFromDatabase(User user) {
    // Zakładając, że User ma id
    Integer userId = user.getIdregisteredUser();

    // Zapytanie JPQL, które pobiera role użytkownika na podstawie jego id
    TypedQuery<String> query = em.createQuery(
        "SELECT r.name FROM Roles r JOIN r.userCollection u WHERE u.idregisteredUser = :userId", 
        String.class
    );
    query.setParameter("userId", userId);

    return query.getResultList();
}

}


