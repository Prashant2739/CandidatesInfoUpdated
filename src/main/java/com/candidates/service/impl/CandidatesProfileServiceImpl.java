package com.candidates.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.candidates.dao.CandidatesProfileDao;
import com.candidates.model.CandidateWorkExpResponse;
import com.candidates.model.CandidatesEducationResponse;
import com.candidates.model.CandidatesSkillResponse;
import com.candidates.model.DataHistoryResponse;
import com.candidates.model.GetProfileResponse;
import com.candidates.model.UnreadNotificationResponse;
import com.candidates.service.CandidatesProfileService;


@Service
public class CandidatesProfileServiceImpl implements CandidatesProfileService{
	
	@Autowired 
	CandidatesProfileDao candidatesProfileDao;
	
	public CandidatesSkillResponse getCandidateSkills(String email){
		return candidatesProfileDao.getCandidateSkills(email);
	}
	
	public List<CandidatesEducationResponse> getCandidateEducation(String email){
		return candidatesProfileDao.getCandidateEducation(email);
	}
	
	public List<CandidateWorkExpResponse> getCandidateWorkExperiences(String email){
		return candidatesProfileDao.getCandidateWorkExperiences(email);
	}
	
	public GetProfileResponse getProfile(String emailId){
		return candidatesProfileDao.getProfile(emailId);
	}
	
	public DataHistoryResponse getDataHistory(String email){
		return candidatesProfileDao.getDataHistory(email);
	}
	
	public UnreadNotificationResponse getUnreadNotificationsCount(String email){
		return candidatesProfileDao.getUnreadNotificationsCount(email);
	}

}
