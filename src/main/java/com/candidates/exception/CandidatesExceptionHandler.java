package com.candidates.exception;
/**
* @author riddhi.dilip.vyas
* @date 10/12/2018
*
* @group Accounts
* @group-content 
*
* @description: CandidatesExceptionHandler is a @ControllerAdvice class which handles all types of Exception
* 
* Change History
* 10/19/2018     Riddhi 		Added Class and method level Comments  
*/
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import org.slf4j.Logger;

import com.candidates.util.CandidatesConstants;

@ControllerAdvice
public class CandidatesExceptionHandler {
	
	@Autowired
	 Logger logger;
	
		/* @description This method searches if the candidate's email id is already present
	    * @param String email email  Email Id of the candidate
	    * @param String brand
	    * @return boolean  true is the Email Id doesn't exists 
	    */
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleResourceNotFound(final ResourceNotFoundException exception,
			final HttpServletRequest request) {
		logger.error("Exception occured  - "+exception.getMessage());
		ExceptionResponse error = new ExceptionResponse();
		error.setError_message(exception.getMessage());
		error.setError_code("Not Found");

		return error;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleException(final Exception exception,
			final HttpServletRequest request) {
        logger.info("Exception occured  - "+exception.getMessage());
		ExceptionResponse error = new ExceptionResponse();
		error.setError_message(CandidatesConstants.BAD_REQUEST_ERROR);
		error.setError_code(CandidatesConstants.BAD_REQUEST);

		return error;
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		   logger.info("Exception occured  handleMethodArgumentNotValid- "+ex.getMessage());
		ExceptionResponse error = new ExceptionResponse();
		error.setError_message(CandidatesConstants.BAD_REQUEST_ERROR);
		error.setError_code(status.BAD_REQUEST.name());
		logger.error("Exception occured  - "+ex.getMessage());
		return error;
	}


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handleException(MethodArgumentNotValidException exception) {

    	 logger.info("Exception occured  handleMethodArgumentNotValid- "+exception.getMessage());
        ExceptionResponse error = new ExceptionResponse();
		error.setError_message(CandidatesConstants.BAD_REQUEST_ERROR);
		error.setError_code(CandidatesConstants.BAD_REQUEST);

		return error;
    }
    
    @ExceptionHandler(ConsentTypeNotFoundException.class)
    public final ResponseEntity<ExceptionResponse>  handleConsentTypeNotFoundException(ConsentTypeNotFoundException ex) {
    	ExceptionResponse error = new ExceptionResponse();
		error.setError_message(ex.getMessage());
		error.setError_code(CandidatesConstants.GET_404_RESPONSE);
		logger.error("Exception occured  - "+ex.getMessage());
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);	
    }
    
    
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public final ResponseEntity<ExceptionResponse>  handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
    	logger.info("Email Already exists");
    	ExceptionResponse error = new ExceptionResponse();
		error.setError_message(ex.getMessage());
		error.setError_code(CandidatesConstants.EMAIL_EXISTS);

		return new ResponseEntity(error, HttpStatus.NOT_FOUND);	
    }
    
    @ExceptionHandler(AuthorizationException.class)
    public final ResponseEntity<ExceptionResponse>  handleAuthorizationException(AuthorizationException ex) {
    	logger.info("Authorization via Access Token Falied");
    	ExceptionResponse error = new ExceptionResponse();
		error.setError_message(ex.getMessage());
		error.setError_code(CandidatesConstants.AUTHORIZATION_FAILED);

		return new ResponseEntity(error, HttpStatus.UNAUTHORIZED);	
    }
    
    
    
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponse>  handleNoRecordsFoundExceptionException(NotFoundException ex) {
    	logger.info("No Records Found - handler method");
    	ExceptionResponse error = new ExceptionResponse();
		error.setError_message(CandidatesConstants.NO_RECORDS_FOUND);
		error.setError_code(ex.getMessage());

		return new ResponseEntity(error, HttpStatus.NOT_FOUND);	
    }

}
