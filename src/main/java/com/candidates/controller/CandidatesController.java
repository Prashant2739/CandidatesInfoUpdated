package com.candidates.controller;

/**
 * @author riddhi.dilip.vyas
 * @date 10/12/2018
 *
 * @group Accounts
 * @group-content 
 *
 * @description: CandidatesController is a controller class which maps the uri to the appropriate handler methods
 * 
 * Change History
 * 10/19/2018     Riddhi 		Added Class and method level Comments  
 */
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.candidates.exception.ConsentTypeNotFoundException;
import com.candidates.exception.EmailAlreadyExistsException;
import com.candidates.model.CheckUnicityRequest;
import com.candidates.model.CheckUnicityResponse;
import com.candidates.model.ConsentResponse;
import com.candidates.service.CandidatesService;
import com.candidates.util.CandidatesConstants;

@RestController
@RequestMapping("/v1.0")
public class CandidatesController {

	@Autowired
	CandidatesService candidatesService;

	@Autowired
	Logger logger;

	/*
	 * @description Returns Consent Type and Consent Email Body based on
	 * language provided
	 * 
	 * @param country
	 * 
	 * @param brand
	 * 
	 * @param language Based on the language Consent Type and Email body is
	 * fetched
	 * 
	 * @return ResponseEntity<?> based on the parameters selected
	 */
	@ApiOperation(value = CandidatesConstants.GET_CONDITIONS_DESC, response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = CandidatesConstants.GET_CONDITIONS_200_RESPONSE),
			@ApiResponse(code = 400, message = CandidatesConstants.GET_400_RESPONSE),
			@ApiResponse(code = 404, message = CandidatesConstants.GET_404_RESPONSE),
			@ApiResponse(code = 503, message = CandidatesConstants.GET_503_RESPONSE),
			@ApiResponse(code = 500, message = CandidatesConstants.GET_500_RESPONSE) })
	@RequestMapping(value = "/conditions/{country}/{brand}/{language}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getConsentType(
			@Valid @PathVariable(value = "country") String country,
			@PathVariable(value = "brand") String brand,
			@PathVariable(value = "language") String language) {

		logger.info("GetConsentType started");
		List<ConsentResponse> listConsentType = candidatesService
				.getConditions(country, brand, language);

		if (listConsentType == null || listConsentType.isEmpty()) {
			logger.info("No Records found");
			throw new ConsentTypeNotFoundException(
					CandidatesConstants.CONSENT_TYPE_NOT_FOUND);
		}

		return new ResponseEntity<List<ConsentResponse>>(listConsentType,
				HttpStatus.OK);
	}

	/*
	 * @description This method searches if the candidate's email id is already
	 * present
	 * 
	 * @param email String Email Id of the candidate
	 * 
	 * @param email String brand
	 * 
	 * @return suResponseEntity<?> true is the Email Id doesn't exists
	 */
	@ApiOperation(value = CandidatesConstants.CHECK_UNICITY_DESC, response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = CandidatesConstants.CHECK_UNICITY_200_RESPONSE),
			@ApiResponse(code = 400, message = CandidatesConstants.GET_400_RESPONSE),
			@ApiResponse(code = 404, message = CandidatesConstants.GET_404_RESPONSE),
			@ApiResponse(code = 503, message = CandidatesConstants.GET_503_RESPONSE),
			@ApiResponse(code = 500, message = CandidatesConstants.GET_500_RESPONSE) })
	@RequestMapping(value = "/email/checkUnicity/{brand}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity checkEmailUnicity(
			@Valid @RequestBody CheckUnicityRequest checkUnicityRequest,
			@PathVariable(value = "brand") String brand) {

		logger.info("CheckEmailUnicity Started");
		boolean emailUnicityFlag = candidatesService.postCheckUnicity(
				checkUnicityRequest.getEmail(), brand);
		logger.info("emailUnicityFlag in the controller.");

		if (!emailUnicityFlag) {
			logger.info("Email already exists.Throwing custom EmailAlreadyExistsException");
			throw new EmailAlreadyExistsException(
					CandidatesConstants.EMAIL_EXISTS_MESSAGE);
		}
		CheckUnicityResponse unicityResponseOnj = new CheckUnicityResponse();
		unicityResponseOnj.setSuccess(emailUnicityFlag);

		return new ResponseEntity<CheckUnicityResponse>(unicityResponseOnj,
				HttpStatus.OK);

	}

}
