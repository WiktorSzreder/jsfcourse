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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author wikto
 */
@Entity
@Table(name = "awards")
@NamedQueries({
    @NamedQuery(name = "Award.findAll", query = "SELECT a FROM Award a"),
    @NamedQuery(name = "Award.findByIdawards", query = "SELECT a FROM Award a WHERE a.idawards = :idawards"),
    @NamedQuery(name = "Award.findByName", query = "SELECT a FROM Award a WHERE a.name = :name")})
public class Award implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idawards")
    private Integer idawards;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "actors_idactors", referencedColumnName = "idactors")
    @ManyToOne(optional = false)
    private Actor actorsIdactors;

    public Award() {
    }

    public Award(Integer idawards) {
        this.idawards = idawards;
    }

    public Award(Integer idawards, String name) {
        this.idawards = idawards;
        this.name = name;
    }

    public Integer getIdawards() {
        return idawards;
    }

    public void setIdawards(Integer idawards) {
        this.idawards = idawards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Actor getActorsIdactors() {
        return actorsIdactors;
    }

    public void setActorsIdactors(Actor actorsIdactors) {
        this.actorsIdactors = actorsIdactors;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idawards != null ? idawards.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Award)) {
            return false;
        }
        Award other = (Award) object;
        if ((this.idawards == null && other.idawards != null) || (this.idawards != null && !this.idawards.equals(other.idawards))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsf.entities.Award[ idawards=" + idawards + " ]";
    }
    
}
