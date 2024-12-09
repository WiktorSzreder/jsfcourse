package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the movies database table.
 * 
 */
@Entity
@Table(name="movies")
@NamedQuery(name="Movy.findAll", query="SELECT m FROM Movy m")
public class Movy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idmovies;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="release_date")
	private Date releaseDate;

	private String title;

	//bi-directional many-to-one association to Genre
	@ManyToOne
	private Genre genre;

	//bi-directional many-to-many association to Actor
	@ManyToMany
	@JoinTable(
		name="movies_has_actors"
		, joinColumns={
			@JoinColumn(name="movies_idmovies")
			}
		, inverseJoinColumns={
			@JoinColumn(name="actors_idactors")
			}
		)
	private List<Actor> actors;

	//bi-directional many-to-one association to Rating
	@OneToMany(mappedBy="movy")
	private List<Rating> ratings;

	public Movy() {
	}

	public int getIdmovies() {
		return this.idmovies;
	}

	public void setIdmovies(int idmovies) {
		this.idmovies = idmovies;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List<Actor> getActors() {
		return this.actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public List<Rating> getRatings() {
		return this.ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public Rating addRating(Rating rating) {
		getRatings().add(rating);
		rating.setMovy(this);

		return rating;
	}

	public Rating removeRating(Rating rating) {
		getRatings().remove(rating);
		rating.setMovy(null);

		return rating;
	}

}