package com.candidates.dao.impl;

/**
* @author riddhi.dilip.vyas
* @date 10/12/2018
*
* @group Accounts
* @group-content 
*
* @description: CandidatesDaoImpl is a doa impl class which connect to the database and fetches records
* 
* Change History
* 10/19/2018     Riddhi 		Added Class and method level Comments  
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.candidates.dao.CandidatesDao;
import com.candidates.model.ConsentResponse;
import com.candidates.util.QueryConstants;

@Repository
public class CandidatesDaoImpl extends JdbcDaoSupport implements CandidatesDao {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	/*
	 * @description Returns Consent Type and Consent Email Body based on language
	 * provided
	 * 
	 * @param String country
	 * 
	 * @param String brand
	 * 
	 * @param String language Based on the language Consent Type and Email body is
	 * fetched
	 * 
	 * @return List<ConsentResponse> based on the parameters selected
	 */
	public List<ConsentResponse> getConditions(String country, String brand, String language) {

		String sql = QueryConstants.GET_CONDITIONS;
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, language);
		List<ConsentResponse> result = new ArrayList<>();
		if (null != rows && !rows.isEmpty()) {
			for (Map<String, Object> row : rows) {
				if (null != row) {
					ConsentResponse consentType = new ConsentResponse();
					consentType.setContent((null == row.get(QueryConstants.COLUMN_CONSENT_EMAIL_BODY) ? ""
							: (String) row.get(QueryConstants.COLUMN_CONSENT_EMAIL_BODY)));
					consentType.setTitle((null == row.get(QueryConstants.COLUMN_CONSENT_NAME) ? ""
							: (String) row.get(QueryConstants.COLUMN_CONSENT_NAME)));
					result.add(consentType);
				}

			}
		}

		logger.info("List of Consent Type - " + result.toString());

		return result;
	}

	/*
	 * @description This method searches if the candidate's email id is already
	 * present
	 * 
	 * @param String email email Email Id of the candidate
	 * 
	 * @param String brand
	 * 
	 * @return boolean true is the Email Id doesn't exists
	 */
	public boolean postCheckUnicity(String email, String brand) {

		String sql = QueryConstants.CHECK_EMAIL_UNICITY_CONTACT;
		Integer count = getJdbcTemplate().queryForObject(sql, new Object[] { email.toUpperCase() }, Integer.class);

		if (count > 0) {
			logger.info("Email already exists");
			return false;
		} else {
			logger.info("Email does not exists in the Contact Table.Checking the User table");
			sql = QueryConstants.CHECK_EMAIL_UNICITY_USER;
			count = getJdbcTemplate().queryForObject(sql, new Object[] { email }, Integer.class);

			if (count > 0) {
				logger.info("Email exists in User Table.Returning false");
				return false;
			}
		}
		logger.info("Email does not exists");
		return true;
	}

}
