package com.jsfcourse.rating;

import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;

import com.jsf.dao.RatingDAO;
import com.jsf.entities.Movie;
import com.jsf.entities.Rating;
import com.jsf.entities.User;
import java.util.ArrayList;
import java.util.Date;

@Named
@RequestScoped
public class RatingListBB {
    private static final String PAGE_STAY_AT_THE_SAME = null;
    private static final String PAGE_RATING_LIST = "/pages/ratingList?faces-redirect=true";
  
    @Inject
    ExternalContext extcontext;

    @Inject
    Flash flash;

    @EJB
    RatingDAO ratingDAO;

    // Retrieve the full list of ratings
    public List<Rating> getFullList() {
        List<Rating> list = null;
        list = ratingDAO.getFullList();
        return list;
    }

    // Delete a rating
public String deleteRating(Rating rating) {
        ratingDAO.remove(rating); // Usuń rekord z bazy danych
        return PAGE_STAY_AT_THE_SAME; // Pozostań na tej samej stronie

}
public String deleteForce(int r) {
        ratingDAO.delete(r);
        return PAGE_STAY_AT_THE_SAME; // Pozostań na tej samej stronie

}

    public String addToRating(Movie movie){

        User user = new User(); // Przypisz istniejącego użytkownika (np. pobranego z bazy danych)
        
        Integer userId = (Integer) extcontext.getSessionMap().get("userId");
        user.setIdregisteredUser(userId); // Ustaw ID użytkownika (np. 1)

        // Utwórz nowy obiekt Rating
        Rating rating = new Rating();
        rating.setWatchDate(new Date()); // Ustaw aktualną datę oglądania
        rating.setMoviesIdmovies(movie); // Przypisz film
        rating.setRegisteredUserIdregisteredUser(user); // Przypisz użytkownika

        // Wywołaj metodę create
        
        ratingDAO.merge(rating);
        
        return PAGE_STAY_AT_THE_SAME;
    }


     public List<Rating> getUserRatings() {
        // Get the user ID from the session
        Integer userId = (Integer) extcontext.getSessionMap().get("userId");
        
        // If userId is null, it means the user is not logged in
        if (userId == null) {
            return new ArrayList<>(); // Return an empty list if not logged in
        }

        // Get ratings for the specific user
        return ratingDAO.getList(Map.of("userId", userId));
    }

}
