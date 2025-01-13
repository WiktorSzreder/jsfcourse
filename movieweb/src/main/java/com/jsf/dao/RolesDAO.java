package com.jsf.dao;

import com.jsf.entities.Roles;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
public class RolesDAO {

    @PersistenceContext
    private EntityManager em;

    // Pobierz wszystkie role
    public List<Roles> findAll() {
        TypedQuery<Roles> query = em.createNamedQuery("Roles.findAll", Roles.class);
        return query.getResultList();
    }

    // Znajdź role na podstawie ich identyfikatorów
    public List<Roles> findByIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of(); // Zwróć pustą listę, jeśli brak ID
        }
        TypedQuery<Roles> query = em.createQuery(
            "SELECT r FROM Roles r WHERE r.idroles IN :ids", Roles.class
        );
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    // Znajdź rolę na podstawie jej nazwy
    public Roles findByName(String name) {
        TypedQuery<Roles> query = em.createNamedQuery("Roles.findByName", Roles.class);
        query.setParameter("name", name);
        return query.getResultStream().findFirst().orElse(null); // Zwróć null, jeśli brak wyników
    }

    // Utwórz nową rolę
    public void create(Roles role) {
        em.persist(role);
    }

    // Zaktualizuj istniejącą rolę
    public void update(Roles role) {
        em.merge(role);
    }

    // Usuń rolę
    public void delete(Roles role) {
        if (!em.contains(role)) {
            role = em.merge(role);
        }
        em.remove(role);
    }
}
