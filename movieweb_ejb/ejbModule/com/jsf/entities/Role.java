package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idroles;

	@Temporal(TemporalType.DATE)
	@Column(name="created_at")
	private Date createdAt;

	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-many association to RegisteredUser
	@ManyToMany
	@JoinTable(
		name="registered_user_has_roles"
		, joinColumns={
			@JoinColumn(name="roles_idroles")
			}
		, inverseJoinColumns={
			@JoinColumn(name="registered_user_idregistered_user")
			}
		)
	private List<RegisteredUser> registeredUsers;

	public Role() {
	}

	public int getIdroles() {
		return this.idroles;
	}

	public void setIdroles(int idroles) {
		this.idroles = idroles;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<RegisteredUser> getRegisteredUsers() {
		return this.registeredUsers;
	}

	public void setRegisteredUsers(List<RegisteredUser> registeredUsers) {
		this.registeredUsers = registeredUsers;
	}

}