package com.candidates.service;

import java.util.List;
import com.candidates.model.ConsentResponse;

public interface CandidatesService {
	
	public List<ConsentResponse> getConditions(String country,String brand,String language);
	
	public boolean postCheckUnicity(String email,String brand);

}
