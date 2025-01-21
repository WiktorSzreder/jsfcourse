package com.jsfcourse.movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;

import com.jsf.dao.MovieDAO;
import com.jsf.entities.Movie;

@Named
@RequestScoped
public class MovieListBB {
    private static final String PAGE_MOVIE_EDIT = "/pages/manager/movieEdit?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private String title;

    @Inject
    ExternalContext extcontext;

    @Inject
    Flash flash;

    @EJB
    MovieDAO movieDAO;

    // Getter and Setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Retrieve the full list of movies
    public List<Movie> getFullList() {
        return movieDAO.getFullList();
    }

    // Retrieve a filtered list of movies based on search parameters
    public List<Movie> getList() {
        List<Movie> list = null;

        // 1. Prepare search params
        Map<String, Object> searchParams = new HashMap<>();

        if (title != null && title.length() > 0) {
            searchParams.put("title", title);
        }

        // 2. Get list
        list = movieDAO.getList(searchParams);

        return list;
    }

    // Create a new movie and navigate to the edit page
    public String newMovie() {
        Movie movie = new Movie();

        // Pass the new Movie object through flash
        flash.put("movie", movie);

        return PAGE_MOVIE_EDIT;
    }

    // Edit an existing movie and navigate to the edit page
    public String editMovie(Movie movie) {
        // Pass the existing Movie object through flash
        flash.put("movie", movie);

        return PAGE_MOVIE_EDIT;
    }

    // Delete a movie
    public String deleteMovie(Movie movie) {
        movieDAO.remove(movie);
        return PAGE_STAY_AT_THE_SAME;
    }
    
    

}
