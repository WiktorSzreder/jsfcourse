package com.jsfcourse.person;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsf.dao.RegisteredUserDAO;
import com.jsf.entities.RegisteredUser;

@Named
@ViewScoped
public class PersonEditGETBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_PERSON_LIST = "personList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private RegisteredUser registeredUser = new RegisteredUser();
	private RegisteredUser loaded = null;

	@Inject
	FacesContext context;

	@EJB
	RegisteredUserDAO personDAO;

	public RegisteredUser getPerson() {
		return registeredUser;
	}

	public void onLoad() throws IOException {
		if (!context.isPostback()) {
			if (!context.isValidationFailed() && registeredUser.getIdperson() != null) {
				loaded = personDAO.find(registeredUser.getIdperson());
			}
			if (loaded != null) {
				registeredUser = loaded;
			} else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
				// if (!context.isPostback()) { // possible redirect
				// context.getExternalContext().redirect("personList.xhtml");
				// context.responseComplete();
				// }
			}
		}

	}

	public String saveData() {
		// no Person object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (registeredUser.getIdperson() == null) {
				// new record
				personDAO.create(registeredUser);
			} else {
				// existing record
				personDAO.merge(registeredUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_PERSON_LIST;
	}
}
