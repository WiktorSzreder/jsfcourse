package com.jsfcourse.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;

import com.jsf.dao.UserDAO;
import com.jsf.entities.User;

@Named
@RequestScoped
public class UserListBB {
    private static final String PAGE_USER_EDIT = "/pages/admin/userEdit?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private String username;
    private String email;
    
    @Inject
    ExternalContext extcontext;
    
    @Inject
    Flash flash;
    
    @EJB
    UserDAO userDAO;
    
    // Getters and Setters for username and email search parameters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Get full list of users
    public List<User> getFullList(){
        return userDAO.getFullList();
    }

    // Get list of users based on search parameters (username, email)
    public List<User> getList(){
        List<User> list = null;
        
        // 1. Prepare search parameters
        Map<String,Object> searchParams = new HashMap<String, Object>();
        
        if (username != null && username.length() > 0){
            searchParams.put("username", username);
        }
        
        if (email != null && email.length() > 0){
            searchParams.put("email", email);
        }
        
        // 2. Get the list from DAO
        list = userDAO.getList(searchParams);
        
        return list;
    }

    // Create a new user, passing the object via Flash scope
    public String newUser(){
        User user = new User();
        
        // Pass object through Flash scope
        flash.put("user", user);
        
        return PAGE_USER_EDIT;
    }

    // Edit an existing user, passing the object via Flash scope
    public String editUser(User user){
        // Pass object through Flash scope
        flash.put("user", user);
        
        return PAGE_USER_EDIT;
    }

    // Delete an existing user
    public String deleteUser(User user){
        userDAO.remove(user);
        return PAGE_STAY_AT_THE_SAME;
    }
}
