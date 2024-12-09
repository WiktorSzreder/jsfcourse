package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the actors database table.
 * 
 */
@Entity
@Table(name="actors")
@NamedQuery(name="Actor.findAll", query="SELECT a FROM Actor a")
public class Actor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idactors;

	@Temporal(TemporalType.DATE)
	@Column(name="birth_date")
	private Date birthDate;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	//bi-directional many-to-one association to Award
	@OneToMany(mappedBy="actor")
	private List<Award> awards;

	//bi-directional many-to-many association to Movy
	@ManyToMany(mappedBy="actors")
	private List<Movy> movies;

	public Actor() {
	}

	public int getIdactors() {
		return this.idactors;
	}

	public void setIdactors(int idactors) {
		this.idactors = idactors;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Award> getAwards() {
		return this.awards;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}

	public Award addAward(Award award) {
		getAwards().add(award);
		award.setActor(this);

		return award;
	}

	public Award removeAward(Award award) {
		getAwards().remove(award);
		award.setActor(null);

		return award;
	}

	public List<Movy> getMovies() {
		return this.movies;
	}

	public void setMovies(List<Movy> movies) {
		this.movies = movies;
	}

}