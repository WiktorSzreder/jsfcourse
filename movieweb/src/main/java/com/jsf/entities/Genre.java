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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author wikto
 */
@Entity
@Table(name = "genre")
@NamedQueries({
    @NamedQuery(name = "Genre.findAll", query = "SELECT g FROM Genre g"),
    @NamedQuery(name = "Genre.findByIdgenre", query = "SELECT g FROM Genre g WHERE g.idgenre = :idgenre"),
    @NamedQuery(name = "Genre.findByName", query = "SELECT g FROM Genre g WHERE g.name = :name"),
    @NamedQuery(name = "Genre.findByDescription", query = "SELECT g FROM Genre g WHERE g.description = :description")})
public class Genre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgenre")
    private Integer idgenre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "genreIdgenre")
    private Collection<Movie> movieCollection;

    public Genre() {
    }

    public Genre(Integer idgenre) {
        this.idgenre = idgenre;
    }

    public Genre(Integer idgenre, String name) {
        this.idgenre = idgenre;
        this.name = name;
    }

    public Integer getIdgenre() {
        return idgenre;
    }

    public void setIdgenre(Integer idgenre) {
        this.idgenre = idgenre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Movie> getMovieCollection() {
        return movieCollection;
    }

    public void setMovieCollection(Collection<Movie> movieCollection) {
        this.movieCollection = movieCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgenre != null ? idgenre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genre)) {
            return false;
        }
        Genre other = (Genre) object;
        if ((this.idgenre == null && other.idgenre != null) || (this.idgenre != null && !this.idgenre.equals(other.idgenre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsf.entities.Genre[ idgenre=" + idgenre + " ]";
    }
    
}
