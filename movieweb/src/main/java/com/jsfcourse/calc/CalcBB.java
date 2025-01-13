package com.jsfcourse.calc;

import java.io.Serializable;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class CalcBB implements Serializable {
	
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private Double x;
	private Double y;
	
	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public String calc() {
		FacesContext ctx = FacesContext.getCurrentInstance();		
		double result = x + y;
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result, null));
		return PAGE_STAY_AT_THE_SAME; 
	}
}
