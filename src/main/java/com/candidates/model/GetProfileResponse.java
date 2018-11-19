package com.candidates.model;

public class GetProfileResponse {
	
	private DocumentResponse document; 
	
	private PersonalDetails personalDetail;
	
	private WorkExperience workExperience;
	
	private Education education;
	
	private Skills skills;

	public DocumentResponse getDocument() {
		return document;
	}

	public void setDocument(DocumentResponse document) {
		this.document = document;
	}

	public PersonalDetails getPersonalDetail() {
		return personalDetail;
	}

	public void setPersonalDetail(PersonalDetails personalDetail) {
		this.personalDetail = personalDetail;
	}

	public WorkExperience getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(WorkExperience workExperience) {
		this.workExperience = workExperience;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public Skills getSkills() {
		return skills;
	}

	public void setSkills(Skills skills) {
		this.skills = skills;
	}

	
	
	
}
