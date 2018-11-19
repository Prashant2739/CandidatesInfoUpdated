package com.candidates;
/**
* @author riddhi.dilip.vyas
* @date 10/12/2018
*
* @group Accounts
* @group-content 
*
* @description: CandidatesSpringBootMain is a class to bootstrap the Application
* 
* Change History
* 10/19/2018     Riddhi 		Added Class level Comments  
*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//@EnableRetry
@SpringBootApplication
public class CandidatesSpringBootMain {
	
	@Bean
	public Logger log() {
	   return LoggerFactory.getLogger(CandidatesSpringBootMain.class);
	}
	
	public static void main(String[] args) {
		 SpringApplication.run(CandidatesSpringBootMain.class, args);
	}


}
	