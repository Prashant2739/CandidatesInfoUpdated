package com.candidates.model;

public class CheckUnicityResponse {

	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "CheckUnicityResponse [success=" + success + "]";
	}
	
	
}
