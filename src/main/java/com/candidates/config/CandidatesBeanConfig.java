package com.candidates.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.candidates.util.CandidatesUtil;

@Configuration
public class CandidatesBeanConfig {
	
	@Bean
    public CandidatesUtil candidatesUtil() {
        return new CandidatesUtil();
    }

}
