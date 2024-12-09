package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the genre database table.
 * 
 */
@Entity
@NamedQuery(name="Genre.findAll", query="SELECT g FROM Genre g")
public class Genre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idgenre;

	private String description;

	private String name;

	//bi-directional many-to-one association to Movy
	@OneToMany(mappedBy="genre")
	private List<Movy> movies;

	public Genre() {
	}

	public int getIdgenre() {
		return this.idgenre;
	}

	public void setIdgenre(int idgenre) {
		this.idgenre = idgenre;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Movy> getMovies() {
		return this.movies;
	}

	public void setMovies(List<Movy> movies) {
		this.movies = movies;
	}

	public Movy addMovy(Movy movy) {
		getMovies().add(movy);
		movy.setGenre(this);

		return movy;
	}

	public Movy removeMovy(Movy movy) {
		getMovies().remove(movy);
		movy.setGenre(null);

		return movy;
	}

}