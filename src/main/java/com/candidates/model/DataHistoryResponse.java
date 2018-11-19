package com.candidates.model;

public class DataHistoryResponse {

	private boolean statusPortability= false;
	private boolean statusDataAccess = false;
	
	public boolean isStatusPortability() {
		return statusPortability;
	}
	public void setStatusPortability(boolean statusPortability) {
		this.statusPortability = statusPortability;
	}
	public boolean isStatusDataAccess() {
		return statusDataAccess;
	}
	public void setStatusDataAccess(boolean statusDataAccess) {
		this.statusDataAccess = statusDataAccess;
	}
	
	
	
}
