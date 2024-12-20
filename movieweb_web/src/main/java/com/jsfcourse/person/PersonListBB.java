package com.jsfcourse.person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.servlet.http.HttpSession;

import com.jsf.dao.RegisteredUserDAO;
import com.jsf.entities.RegisteredUser;

@Named
@RequestScoped
public class PersonListBB {
	private static final String PAGE_PERSON_EDIT = "personEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String surname;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	RegisteredUserDAO RegisteredUserDAO;
		
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<RegisteredUser> getFullList(){
		return RegisteredUserDAO.getFullList();
	}

	public List<RegisteredUser> getList(){
		List<RegisteredUser> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (surname != null && surname.length() > 0){
			searchParams.put("surname", surname);
		}
		
		//2. Get list
		list = RegisteredUserDAO.getList(searchParams);
		
		return list;
	}

	public String newPerson(){
		RegisteredUser registeredUser = new RegisteredUser();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("person", registeredUser);
		
		return PAGE_PERSON_EDIT;
	}

	public String editPerson(RegisteredUser registeredUser){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("person", registeredUser);
		
		return PAGE_PERSON_EDIT;
	}

	public String deletePerson(RegisteredUser registeredUser){
		RegisteredUserDAO.remove(registeredUser);
		return PAGE_STAY_AT_THE_SAME;
	}
}
