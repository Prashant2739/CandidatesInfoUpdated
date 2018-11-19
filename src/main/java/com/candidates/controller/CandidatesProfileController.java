package com.candidates.controller;
/**
* @author riddhi.dilip.vyas
* @date 10/12/2018
*
* @group Accounts
* @group-content 
*
* @description: CandidatesProfileController is a controller class which maps the uri to the appropriate handler methods
* 
* 
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.candidates.exception.AuthorizationException;
import com.candidates.exception.NotFoundException;
import com.candidates.model.CandidateWorkExpResponse;
import com.candidates.model.CandidatesEducationResponse;
import com.candidates.model.CandidatesSkillResponse;
import com.candidates.model.DataHistoryResponse;
import com.candidates.model.GetProfileResponse;
import com.candidates.model.UnreadNotificationResponse;
import com.candidates.service.CandidatesProfileService;
import com.candidates.util.CandidatesConstants;
import com.candidates.util.CandidatesUtil;

@RestController
@RequestMapping("/v2.0")
public class CandidatesProfileController {
	

	@Autowired
	CandidatesProfileService candidatesProfileService;
	
	 @Autowired
	 Logger logger;
	 
	 @Autowired
	 CandidatesUtil candidatesUtil;
	 
	 
	   /*@description The candidate gets his profile.
	    * @param brand 
	    * @return ResponseEntity<?>  based on the parameters selected
	     */
	    
		@ApiOperation(value = CandidatesConstants.GET_PROFILE_DESC, response = ResponseEntity.class)
		@ApiResponses(value = {
				@ApiResponse(code = 200, message = CandidatesConstants.GET_PROFILE_200_RESPONSE),
		        @ApiResponse(code = 400, message = CandidatesConstants.GET_400_RESPONSE),
		        @ApiResponse(code = 404, message = CandidatesConstants.GET_404_RESPONSE),
		        @ApiResponse(code = 503, message = CandidatesConstants.GET_503_RESPONSE),
		        @ApiResponse(code = 500, message = CandidatesConstants.GET_500_RESPONSE)
		}
		)
		@RequestMapping(value="/profiles/",method=RequestMethod.GET, produces = "application/json")
		public ResponseEntity getProfile(@Valid @RequestHeader(value="Authorization") String authorization ,@RequestHeader(value="brand") String brand){
			
			logger.info("getProfile started");
			if(authorization==null || authorization.equals("")){
				throw new AuthorizationException(CandidatesConstants.AUTHORIZATION_FAILED_ERROR); 
			}
			String  emailId = candidatesUtil.authenticateAccessToken(authorization);
			//String  emailId = "test@gmail.com";
			GetProfileResponse getProfileResponse;
			if(null != emailId && !emailId.equalsIgnoreCase("")){
				getProfileResponse =  candidatesProfileService.getProfile(emailId);
			
				if(null==getProfileResponse){
					logger.info("No Profile Found");
					throw new NotFoundException(CandidatesConstants.GET_404_RESPONSE);
				}
			}else{
				logger.info("Inside getProfile Method.Authorization Failed");
				throw new AuthorizationException(CandidatesConstants.AUTHORIZATION_FAILED_ERROR); 
			}
			
			return new ResponseEntity<GetProfileResponse>(getProfileResponse, HttpStatus.OK);
		}
		
		
		    /*@description The candidate retrieves his skills.
		    * @param brand 
		    * @return ResponseEntity<?>  based on the parameters selected
		     */
		    
			@ApiOperation(value = CandidatesConstants.GET_SKILLS_DESC, response = ResponseEntity.class)
			@ApiResponses(value = {
					@ApiResponse(code = 200, message = CandidatesConstants.GET_SKILLS_200_RESPONSE),
			        @ApiResponse(code = 400, message = CandidatesConstants.GET_400_RESPONSE),
			        @ApiResponse(code = 404, message = CandidatesConstants.GET_404_RESPONSE),
			        @ApiResponse(code = 503, message = CandidatesConstants.GET_503_RESPONSE),
			        @ApiResponse(code = 500, message = CandidatesConstants.GET_500_RESPONSE)
			}
			)
			@RequestMapping(value="/skills/",method=RequestMethod.GET, produces = "application/json")
			public ResponseEntity getSkills(@Valid @RequestHeader(value="Authorization") String authorization,@RequestHeader(value="brand") String brand){
				
				logger.info("getSkills started");
				if(authorization==null || authorization.equals("")){
					throw new AuthorizationException(CandidatesConstants.AUTHORIZATION_FAILED_ERROR); 
				}
				CandidatesSkillResponse candidatesSkillResponse;
				String  emailId = candidatesUtil.authenticateAccessToken(authorization);
				if(null != emailId && !emailId.equalsIgnoreCase("")){
					candidatesSkillResponse = candidatesProfileService.getCandidateSkills(emailId);
					
					if(null==candidatesSkillResponse || null == candidatesSkillResponse.getLanguage() || candidatesSkillResponse.getLanguage().equals("")){
						logger.info("No Skills Found");
						throw new NotFoundException("Not found");
					}
					
				}else{
					logger.info("Inside getSkills Method.Authorization Failed");
					throw new AuthorizationException(CandidatesConstants.AUTHORIZATION_FAILED_ERROR); 
				}
				
				
				return new ResponseEntity<CandidatesSkillResponse>(candidatesSkillResponse, HttpStatus.OK);
			}
			
			   /*@description The candidate retrieves his education details.
			    * @param brand 
			    * @return ResponseEntity<?>  based on the parameters selected
			     */
			    
				@ApiOperation(value = CandidatesConstants.GET_EDUCATION_DESC, response = ResponseEntity.class)
				@ApiResponses(value = {
						@ApiResponse(code = 200, message = CandidatesConstants.GET_EDUCATION_200_RESPONSE),
				        @ApiResponse(code = 400, message = CandidatesConstants.GET_400_RESPONSE),
				        @ApiResponse(code = 404, message = CandidatesConstants.GET_404_RESPONSE),
				        @ApiResponse(code = 503, message = CandidatesConstants.GET_503_RESPONSE),
				        @ApiResponse(code = 500, message = CandidatesConstants.GET_500_RESPONSE)
				}
				)
				@RequestMapping(value="/educations/",method=RequestMethod.GET, produces = "application/json")
				public ResponseEntity getCandidateEduction(@Valid @RequestHeader(value="Authorization") String authorization,@RequestHeader(value="brand") String brand){
					
					logger.info("getCandidateEduction started");
					if(authorization==null || authorization.equals("")){
						throw new AuthorizationException(CandidatesConstants.AUTHORIZATION_FAILED_ERROR);  
					}
					List<CandidatesEducationResponse> candidatesEducationResponseList;
					String emailId = candidatesUtil.authenticateAccessToken(authorization);
					
					logger.info("Email id of the user- >"+emailId);
					if(null != emailId && !emailId.equalsIgnoreCase("")){
						candidatesEducationResponseList = candidatesProfileService.getCandidateEducation(emailId);
						
						if(null==candidatesEducationResponseList || candidatesEducationResponseList.isEmpty()){
							logger.info("No Education Found");
							throw new NotFoundException("Not found");
						}
						
					}else{
						logger.info("Inside getProfile Method.Authorization Failed");
						throw new AuthorizationException(CandidatesConstants.AUTHORIZATION_FAILED_ERROR); 
					}
					
					
					return new ResponseEntity<List<CandidatesEducationResponse>>(candidatesEducationResponseList, HttpStatus.OK);
				}
				
			    	/*@description The candidate requests access to his personal data history with Adecco.
				    * @param brand 
				    * @return ResponseEntity<?>  based on the parameters selected
				    */
				    
					@ApiOperation(value = CandidatesConstants.GET_DATA_HISTORY_DESC, response = ResponseEntity.class)
					@ApiResponses(value = {
							@ApiResponse(code = 200, message = CandidatesConstants.GET_DATA_HISTORY_200_RESPONSE),
					        @ApiResponse(code = 400, message = CandidatesConstants.GET_400_RESPONSE),
					        @ApiResponse(code = 404, message = CandidatesConstants.GET_404_RESPONSE),
					        @ApiResponse(code = 503, message = CandidatesConstants.GET_503_RESPONSE),
					        @ApiResponse(code = 500, message = CandidatesConstants.GET_500_RESPONSE)
					}
					)
					@RequestMapping(value="/dataHistory/",method=RequestMethod.GET, produces = "application/json")
					public ResponseEntity getDataHistory(@Valid @RequestHeader(value="Authorization") String authorization,@RequestHeader(value="brand") String brand){
						
						logger.info("getDataHistory started");
						if(authorization==null || authorization.equals("")){
							throw new AuthorizationException(CandidatesConstants.AUTHORIZATION_FAILED_ERROR); 
						}
						DataHistoryResponse dataHistoryResponse;
						String  emailId = candidatesUtil.authenticateAccessToken(authorization);
						if(null != emailId && !emailId.equalsIgnoreCase("")){
							dataHistoryResponse = candidatesProfileService.getDataHistory(emailId);
							
							if(null == dataHistoryResponse ){
								logger.info("No Data History Found");
								throw new NotFoundException(CandidatesConstants.NO_RECORDS_FOUND);
							}
							
						}else{
							logger.info("Inside getDataHistory Method.Authorization Failed");
							throw new AuthorizationException(CandidatesConstants.AUTHORIZATION_FAILED_ERROR); 
						}
						
						
						return new ResponseEntity<DataHistoryResponse>(dataHistoryResponse, HttpStatus.OK);
					}

					
					 /*@description The candidate retrieves his work experiences.
					    * @param brand 
					    * @return ResponseEntity<?>  based on the parameters selected
					     */
					    
						@ApiOperation(value = CandidatesConstants.GET_WORK_EXP_DESC, response = ResponseEntity.class)
						@ApiResponses(value = {
								@ApiResponse(code = 200, message = CandidatesConstants.GET_WORK_EXP_200_RESPONSE),
						        @ApiResponse(code = 400, message = CandidatesConstants.GET_400_RESPONSE),
						        @ApiResponse(code = 404, message = CandidatesConstants.GET_404_RESPONSE),
						        @ApiResponse(code = 503, message = CandidatesConstants.GET_503_RESPONSE),
						        @ApiResponse(code = 500, message = CandidatesConstants.GET_500_RESPONSE)
						}
						)
						@RequestMapping(value="/workExperiences/",method=RequestMethod.GET, produces = "application/json")
						public ResponseEntity getWorkExperience(@Valid @RequestHeader(value="authorization") String authorization,@RequestHeader(value="brand") String brand){
							
							logger.info("getWorkExperience started");
							if(authorization==null || authorization.equals("")){
								throw new AuthorizationException(CandidatesConstants.AUTHORIZATION_FAILED_ERROR); 
							}
							List<CandidateWorkExpResponse> candidateWorkExpResponseList;
							String  emailId = candidatesUtil.authenticateAccessToken(authorization);
							logger.info("----- email id - >"+emailId);
							if(null != emailId && !emailId.equalsIgnoreCase("")){
								candidateWorkExpResponseList = candidatesProfileService.getCandidateWorkExperiences(emailId);
								if(null == candidateWorkExpResponseList || candidateWorkExpResponseList.isEmpty()){
									logger.info("No Work Exp Found");
									throw new NotFoundException(CandidatesConstants.NO_RECORDS_FOUND);
								}
								
							}else{
								logger.info("Inside getWorkExperience Method.Authorization Failed");
								throw new AuthorizationException(CandidatesConstants.AUTHORIZATION_FAILED_ERROR); 
							}
							
							
							return new ResponseEntity<List<CandidateWorkExpResponse>>(candidateWorkExpResponseList, HttpStatus.OK);
						}

						   /*@description The candidate retrieves the number of unread notifications.
						    * @return ResponseEntity<?>  based on the parameters selected
						     */
						    
							@ApiOperation(value = CandidatesConstants.GET_NOTIFICATION_DESC, response = ResponseEntity.class)
							@ApiResponses(value = {
									@ApiResponse(code = 200, message = CandidatesConstants.GET_NOTIFICATION_200_RESPONSE),
							        @ApiResponse(code = 400, message = CandidatesConstants.GET_400_RESPONSE),
							        @ApiResponse(code = 404, message = CandidatesConstants.GET_404_RESPONSE),
							        @ApiResponse(code = 503, message = CandidatesConstants.GET_503_RESPONSE),
							        @ApiResponse(code = 500, message = CandidatesConstants.GET_500_RESPONSE)
							}
							)
							@RequestMapping(value="/notifications/open/count/",method=RequestMethod.GET, produces = "application/json")
							public ResponseEntity getUnreadNotificationsCount(@Valid @RequestHeader(value="Authorization") String authorization,@RequestHeader(value="brand") String brand){
								
								logger.info("UnreadNotificationsCount started");
								if(authorization==null || authorization.equals("")){
									throw new AuthorizationException(CandidatesConstants.AUTHORIZATION_FAILED_ERROR); 
								}
								UnreadNotificationResponse unreadNotificationResponse;
								String  emailId = candidatesUtil.authenticateAccessToken(authorization);
								if(null != emailId && !emailId.equalsIgnoreCase("")){
									unreadNotificationResponse = candidatesProfileService.getUnreadNotificationsCount(emailId);
									if(null == unreadNotificationResponse ){
										logger.info("No Notifications Found");
										throw new NotFoundException(CandidatesConstants.NO_RECORDS_FOUND);
									}
									
								}else{
									logger.info("Inside getUnreadNotificationsCount Method.Authorization Failed");
									throw new AuthorizationException(CandidatesConstants.AUTHORIZATION_FAILED_ERROR); 
								}
								
								
								return new ResponseEntity<UnreadNotificationResponse>(unreadNotificationResponse, HttpStatus.OK);
							}

}
