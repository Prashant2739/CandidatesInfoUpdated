package com.candidates.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candidates.dao.CandidatesDao;
import com.candidates.model.ConsentResponse;
import com.candidates.service.CandidatesService;

@Service
public class CandidatesServiceImpl implements CandidatesService{

	@Autowired 
	CandidatesDao candidatesDao;
	
	public List<ConsentResponse> getConditions(String country,String brand,String language) {
		
		return candidatesDao.getConditions( country, brand, language);
	}
	
	//@Retryable(value = {SQLException.class}, maxAttempts = 3)
	//@Retryable(include=SQLException.class,  maxAttempts = 3)
	public boolean postCheckUnicity(String email,String brand){
		return candidatesDao.postCheckUnicity(email, brand);
	}
	
	/*@Recover
    public boolean getBackendResponseFallback(CannotGetJdbcConnectionException e) throws SQLException{
		System.out.println("------ recoverablemethod called---");
		throw new SQLException(e);
	}*/

}
