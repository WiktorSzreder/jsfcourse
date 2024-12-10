/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsf.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "actors")
@NamedQueries({
    @NamedQuery(name = "Actor.findAll", query = "SELECT a FROM Actor a"),
    @NamedQuery(name = "Actor.findByIdactors", query = "SELECT a FROM Actor a WHERE a.idactors = :idactors"),
    @NamedQuery(name = "Actor.findByFirstName", query = "SELECT a FROM Actor a WHERE a.firstName = :firstName"),
    @NamedQuery(name = "Actor.findByLastName", query = "SELECT a FROM Actor a WHERE a.lastName = :lastName"),
    @NamedQuery(name = "Actor.findByBirthDate", query = "SELECT a FROM Actor a WHERE a.birthDate = :birthDate")})
public class Actor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idactors")
    private Integer idactors;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @JoinTable(name = "movies_has_actors", joinColumns = {
        @JoinColumn(name = "actors_idactors", referencedColumnName = "idactors")}, inverseJoinColumns = {
        @JoinColumn(name = "movies_idmovies", referencedColumnName = "idmovies")})
    @ManyToMany
    private Collection<Movie> movieCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actorsIdactors")
    private Collection<Award> awardCollection;

    public Actor() {
    }

    public Actor(Integer idactors) {
        this.idactors = idactors;
    }

    public Actor(Integer idactors, String firstName, String lastName) {
        this.idactors = idactors;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getIdactors() {
        return idactors;
    }

    public void setIdactors(Integer idactors) {
        this.idactors = idactors;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Collection<Movie> getMovieCollection() {
        return movieCollection;
    }

    public void setMovieCollection(Collection<Movie> movieCollection) {
        this.movieCollection = movieCollection;
    }

    public Collection<Award> getAwardCollection() {
        return awardCollection;
    }

    public void setAwardCollection(Collection<Award> awardCollection) {
        this.awardCollection = awardCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactors != null ? idactors.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actor)) {
            return false;
        }
        Actor other = (Actor) object;
        if ((this.idactors == null && other.idactors != null) || (this.idactors != null && !this.idactors.equals(other.idactors))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsf.entities.Actor[ idactors=" + idactors + " ]";
    }
    
}
