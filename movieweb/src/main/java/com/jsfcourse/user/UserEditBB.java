package com.jsfcourse.user;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.ArrayList;


import com.jsf.entities.Roles;
import com.jsf.dao.RolesDAO;

import com.jsf.dao.UserDAO;
import com.jsf.entities.User;
import java.util.Collections;
import java.util.Date;

@Named
@ViewScoped
public class UserEditBB implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String PAGE_USER_LIST = "/pages/manager/userList?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private User user = new User();
    private User loaded = null;
    
    private List<Roles> availableRoles; // Dostępne role w systemie
    private List<Integer> selectedRoleIds; // Id ról wybranych dla użytkownika

    @EJB
    UserDAO userDAO;
    
    @EJB
    private RolesDAO rolesDAO;

    @Inject
    FacesContext context;

    @Inject
    Flash flash;

    public User getUser() {
        return user;
    }
    @PostConstruct
    public void init() {
        // Wczytaj dostępne role z bazy danych
        availableRoles = rolesDAO.findAll();
        selectedRoleIds = new ArrayList<>();

        if (user != null && user.getRolesCollection() != null) {
            for (Roles role : user.getRolesCollection()) {
                selectedRoleIds.add(role.getIdroles());
            }
        }
    }

    public List<Roles> getAvailableRoles() {
        return availableRoles;
    }

    public List<Integer> getSelectedRoleIds() {
        return selectedRoleIds;
    }

    public void setSelectedRoleIds(List<Integer> selectedRoleIds) {
        this.selectedRoleIds = selectedRoleIds;
    }

    public void onLoad() throws IOException {
        // Load user passed through flash
        loaded = (User) flash.get("user");

        if (loaded != null) {
            user = loaded;
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid system usage", null));
        }
    }

public String saveData() {
    // No User object passed
    if (loaded == null) {
        return PAGE_STAY_AT_THE_SAME;
    }

    try {
        // Sprawdzamy, czy istnieje użytkownik o takim samym e-mailu
        User existingUser = userDAO.findByEmail(user.getEmail());

        // Jeśli użytkownik istnieje i nie jest to użytkownik aktualnie edytowany
        if (existingUser != null && !existingUser.getIdregisteredUser().equals(user.getIdregisteredUser())) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email już istnieje w systemie", null));
            return PAGE_STAY_AT_THE_SAME;
        }

        // Jeśli nie znaleziono użytkownika z takim e-mailem, zapisz dane
        if (user.getIdregisteredUser() == null) {
            // New record
            userDAO.create(user);
        } else {
            // Existing record
            userDAO.merge(user);
        }
    } catch (Exception e) {
        e.printStackTrace();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error occurred while saving", null));
        return PAGE_STAY_AT_THE_SAME;
    }

           try {
            // Zaktualizuj role użytkownika
            List<Roles> selectedRoles = rolesDAO.findByIds(selectedRoleIds); // Znajdź wybrane role w bazie
            user.setRolesCollection(selectedRoles);

            if (user.getIdregisteredUser() == null) {
                // Nowy użytkownik
                userDAO.create(user);
            } else {
                // Aktualizacja istniejącego użytkownika
                userDAO.merge(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error occurred while saving roles", null));
            return PAGE_STAY_AT_THE_SAME;
        }

        return PAGE_USER_LIST;
}
public String saveNew() {
    // No User object passed
    if (loaded == null) {
        return PAGE_STAY_AT_THE_SAME;
    }

    try {
        // Sprawdzamy, czy istnieje użytkownik o takim samym e-mailu
        User existingUser = userDAO.findByEmail(user.getEmail());

        // Jeśli użytkownik istnieje i nie jest to użytkownik aktualnie edytowany
        if (existingUser != null && !existingUser.getIdregisteredUser().equals(user.getIdregisteredUser())) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email już istnieje w systemie", null));
            return PAGE_STAY_AT_THE_SAME;
        }

        // Dodajemy rolę "User" do nowego użytkownika i ustawiamy czas utworzenia
        if (user.getIdregisteredUser() == null) {
            Roles userRole = rolesDAO.findByName("User"); // Zakładając, że metoda findByName zwraca rolę po nazwie
            if (userRole != null) {
                user.setRolesCollection(Collections.singletonList(userRole)); // Ustawiamy rolę "User"
            }
            // Ustawiamy bieżący czas jako czas utworzenia
            user.setCreatedAt(new Date()); // Bieżąca data i godzina
            // Nowy rekord
            userDAO.create(user);
        } else {
            // Istniejący rekord
            userDAO.merge(user);
        }
    } catch (Exception e) {
        e.printStackTrace();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error occurred while saving", null));
        return PAGE_STAY_AT_THE_SAME;
    }

    return PAGE_USER_LIST;
}

}
