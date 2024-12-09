package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the awards database table.
 * 
 */
@Entity
@Table(name="awards")
@NamedQuery(name="Award.findAll", query="SELECT a FROM Award a")
public class Award implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idawards;

	private String name;

	//bi-directional many-to-one association to Actor
	@ManyToOne
	@JoinColumn(name="actors_idactors")
	private Actor actor;

	public Award() {
	}

	public int getIdawards() {
		return this.idawards;
	}

	public void setIdawards(int idawards) {
		this.idawards = idawards;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Actor getActor() {
		return this.actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

}