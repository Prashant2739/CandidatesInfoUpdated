package com.candidates.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class CheckUnicityRequest {
	
	@NotNull
	@Email
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CheckUnicityRequest [email=" + email + "]";
	}
	
	

}
