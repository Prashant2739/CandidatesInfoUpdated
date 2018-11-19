package com.candidates.dao;
/**
* @author riddhi.dilip.vyas
* @date 10/12/2018
*
* @group Accounts
* @group-content 
*
* @description: CandidatesDao is an interface 
*/
import java.util.List;

import com.candidates.model.ConsentResponse;

public interface CandidatesDao {

	public List<ConsentResponse> getConditions(String country,String brand,String language);
	
	public boolean postCheckUnicity(String email,String brand);
}
