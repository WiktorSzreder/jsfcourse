/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsf.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author wikto
 */
@Entity
@Table(name = "movies")
@NamedQueries({
    @NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m"),
    @NamedQuery(name = "Movie.findByIdmovies", query = "SELECT m FROM Movie m WHERE m.idmovies = :idmovies"),
    @NamedQuery(name = "Movie.findByTitle", query = "SELECT m FROM Movie m WHERE m.title = :title"),
    @NamedQuery(name = "Movie.findByDescription", query = "SELECT m FROM Movie m WHERE m.description = :description"),
    @NamedQuery(name = "Movie.findByReleaseDate", query = "SELECT m FROM Movie m WHERE m.releaseDate = :releaseDate")})
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmovies")
    private Integer idmovies;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "title")
    private String title;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @ManyToMany(mappedBy = "movieCollection")
    private Collection<Actor> actorCollection;
    @JoinColumn(name = "genre_idgenre", referencedColumnName = "idgenre")
    @ManyToOne
    private Genre genreIdgenre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "moviesIdmovies")
    private Collection<Rating> ratingCollection;

    public Movie() {
    }

    public Movie(Integer idmovies) {
        this.idmovies = idmovies;
    }

    public Movie(Integer idmovies, String title, Date releaseDate) {
        this.idmovies = idmovies;
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public Integer getIdmovies() {
        return idmovies;
    }

    public void setIdmovies(Integer idmovies) {
        this.idmovies = idmovies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Collection<Actor> getActorCollection() {
        return actorCollection;
    }

    public void setActorCollection(Collection<Actor> actorCollection) {
        this.actorCollection = actorCollection;
    }

    public Genre getGenreIdgenre() {
        return genreIdgenre;
    }

    public void setGenreIdgenre(Genre genreIdgenre) {
        this.genreIdgenre = genreIdgenre;
    }

    public Collection<Rating> getRatingCollection() {
        return ratingCollection;
    }

    public void setRatingCollection(Collection<Rating> ratingCollection) {
        this.ratingCollection = ratingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmovies != null ? idmovies.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) object;
        if ((this.idmovies == null && other.idmovies != null) || (this.idmovies != null && !this.idmovies.equals(other.idmovies))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsf.entities.Movie[ idmovies=" + idmovies + " ]";
    }
    
}
