/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsf.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author wikto
 */
@Entity
@Table(name = "rating")
@NamedQueries({
    @NamedQuery(name = "Rating.findAll", query = "SELECT r FROM Rating r"),
    @NamedQuery(name = "Rating.findByIdrating", query = "SELECT r FROM Rating r WHERE r.idrating = :idrating"),
    @NamedQuery(name = "Rating.findByWatchDate", query = "SELECT r FROM Rating r WHERE r.watchDate = :watchDate")})
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrating")
    private Integer idrating;
    @Basic(optional = false)
    @NotNull
    @Column(name = "watch_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date watchDate;
    @JoinColumn(name = "movies_idmovies", referencedColumnName = "idmovies")
    @ManyToOne(optional = false)
    private Movie moviesIdmovies;
    @JoinColumn(name = "registered_user_idregistered_user", referencedColumnName = "idregistered_user")
    @ManyToOne(optional = false)
    private User registeredUserIdregisteredUser;

    public Rating() {
    }

    public Rating(Integer idrating) {
        this.idrating = idrating;
    }

    public Rating(Integer idrating, Date watchDate) {
        this.idrating = idrating;
        this.watchDate = watchDate;
    }

    public Integer getIdrating() {
        return idrating;
    }

    public void setIdrating(Integer idrating) {
        this.idrating = idrating;
    }

    public Date getWatchDate() {
        return watchDate;
    }

    public void setWatchDate(Date watchDate) {
        this.watchDate = watchDate;
    }

    public Movie getMoviesIdmovies() {
        return moviesIdmovies;
    }

    public void setMoviesIdmovies(Movie moviesIdmovies) {
        this.moviesIdmovies = moviesIdmovies;
    }

    public User getRegisteredUserIdregisteredUser() {
        return registeredUserIdregisteredUser;
    }

    public void setRegisteredUserIdregisteredUser(User registeredUserIdregisteredUser) {
        this.registeredUserIdregisteredUser = registeredUserIdregisteredUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrating != null ? idrating.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rating)) {
            return false;
        }
        Rating other = (Rating) object;
        if ((this.idrating == null && other.idrating != null) || (this.idrating != null && !this.idrating.equals(other.idrating))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsf.entities.Rating[ idrating=" + idrating + " ]";
    }
    
}
