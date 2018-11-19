package com.candidates.dao;

import java.util.List;

import com.candidates.model.CandidateWorkExpResponse;
import com.candidates.model.CandidatesEducationResponse;
import com.candidates.model.CandidatesSkillResponse;
import com.candidates.model.DataHistoryResponse;
import com.candidates.model.GetProfileResponse;
import com.candidates.model.UnreadNotificationResponse;

public interface CandidatesProfileDao {
	
	public CandidatesSkillResponse getCandidateSkills(String email);
	
	public List<CandidatesEducationResponse> getCandidateEducation(String email);
	
	public List<CandidateWorkExpResponse> getCandidateWorkExperiences(String email);
	
	public DataHistoryResponse getDataHistory(String email);
	
	public GetProfileResponse getProfile(String emailId);
	
	public UnreadNotificationResponse getUnreadNotificationsCount(String email);

}
