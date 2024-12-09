package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rating database table.
 * 
 */
@Entity
@NamedQuery(name="Rating.findAll", query="SELECT r FROM Rating r")
public class Rating implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idrating;

	@Column(name="watch_date")
	private Timestamp watchDate;

	//bi-directional many-to-one association to Movy
	@ManyToOne
	@JoinColumn(name="movies_idmovies")
	private Movy movy;

	//bi-directional many-to-one association to RegisteredUser
	@ManyToOne
	@JoinColumn(name="registered_user_idregistered_user")
	private RegisteredUser registeredUser;

	public Rating() {
	}

	public int getIdrating() {
		return this.idrating;
	}

	public void setIdrating(int idrating) {
		this.idrating = idrating;
	}

	public Timestamp getWatchDate() {
		return this.watchDate;
	}

	public void setWatchDate(Timestamp watchDate) {
		this.watchDate = watchDate;
	}

	public Movy getMovy() {
		return this.movy;
	}

	public void setMovy(Movy movy) {
		this.movy = movy;
	}

	public RegisteredUser getRegisteredUser() {
		return this.registeredUser;
	}

	public void setRegisteredUser(RegisteredUser registeredUser) {
		this.registeredUser = registeredUser;
	}

}