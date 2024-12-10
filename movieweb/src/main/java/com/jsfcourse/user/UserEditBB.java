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

import com.jsf.dao.UserDAO;
import com.jsf.entities.User;

@Named
@ViewScoped
public class UserEditBB implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String PAGE_USER_LIST = "userList?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private User user = new User();
    private User loaded = null;

    @EJB
    UserDAO userDAO;

    @Inject
    FacesContext context;

    @Inject
    Flash flash;

    public User getUser() {
        return user;
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

        return PAGE_USER_LIST;
    }
}
