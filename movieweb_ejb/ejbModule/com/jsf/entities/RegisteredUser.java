package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the registered_user database table.
 * 
 */
@Entity
@Table(name="registered_user")
@NamedQuery(name="RegisteredUser.findAll", query="SELECT r FROM RegisteredUser r")
public class RegisteredUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idregistered_user")
	private int idregisteredUser;

	@Column(name="created_at")
	private Timestamp createdAt;

	private String email;

	private String password;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	private String username;

	//bi-directional many-to-one association to Rating
	@OneToMany(mappedBy="registeredUser")
	private List<Rating> ratings;

	//bi-directional many-to-many association to Role
	@ManyToMany(mappedBy="registeredUsers")
	private List<Role> roles;

	public RegisteredUser() {
	}

	public int getIdregisteredUser() {
		return this.idregisteredUser;
	}

	public void setIdregisteredUser(int idregisteredUser) {
		this.idregisteredUser = idregisteredUser;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Rating> getRatings() {
		return this.ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public Rating addRating(Rating rating) {
		getRatings().add(rating);
		rating.setRegisteredUser(this);

		return rating;
	}

	public Rating removeRating(Rating rating) {
		getRatings().remove(rating);
		rating.setRegisteredUser(null);

		return rating;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}