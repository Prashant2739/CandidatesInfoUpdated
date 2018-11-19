package com.candidates.util;
/**
* @author riddhi.dilip.vyas
* @date 10/17/2018
*
* @group Accounts
* @group-content 
*
* @description: QueryConstants contains all the queries and column names  
*/
public class QueryConstants {

	private QueryConstants() {
		
	}
	
	
	public static final String GET_CONDITIONS = "select sn.name,sn.tr1__consent_email_body_request__c from salesforce.tr1__consent_type__c as sn where sn.tr1__language__c =? ";
	
	public static final String COLUMN_CONSENT_EMAIL_BODY = "tr1__consent_email_body_request__c";
	
	public static final String COLUMN_CONSENT_NAME = "name";
	
	public static final String CHECK_EMAIL_UNICITY_CONTACT = "select Count(Email) as count from salesforce.Contact where UPPER(Email) = ?";
	
	public static final String CHECK_EMAIL_UNICITY_USER = "select Count(Email) as count from salesforce.User  where UPPER(Email) = ?";
	
	public static final String GET_DATA_HISTORY = "select status,reason from salesforce.case where UPPER(ContactEmail)= ? and reason in ('Portability','Field History') ";
	
	public static final String COLUMN_STATUS = "status";
	
	public static final String COLUMN_REASON = "reason";
	
	public static final String GET_SKILLS = "select sfid,tr1__language__c  from salesforce.contact where Upper(email) = ?";
	
	public static final String COLUMN_LANGUAGE = "tr1__language__c";
	
	public static final String GET_WORK_EXP = "select eh.sfid,tr1__employername__c,TR1__City__c,tr1__title__c,tr1__description__c,tr1__startdate__c,tr1__enddate__c from salesforce.tr1__employmenthistory__c eh, salesforce.contact cnt where cnt.sfid = eh.tr1__contact__c and UPPER(cnt.email) = ?";
	
	public static final String COLUMN_COMPANY_NAME = "tr1__employername__c";
	
	public static final String COLUMN_COMPANY_LOCATION = "TR1__City__c";
	
	public static final String COLUMN_WORK_EXP_DESC = "tr1__description__c";
	
	public static final String COLUMN_WORK_EXP_TITLE = "tr1__title__c";
	
	public static final String GET_CANDIDATES_EDUCATION = "select eh.sfid,tr1__degreename__c,tr1__degreetype__c,tr1__startdate__c,tr1__enddate__c from salesforce.tr1__educationhistory__c eh, salesforce.contact cnt where cnt.sfid = eh.tr1__contact__c and UPPER(cnt.email) =?";
	
	public static final String COLUMN_QUALIFICATION_NAME = "tr1__degreename__c";
	
	public static final String COLUMN_QUALIFICATION_TYPE = "tr1__degreetype__c";
	
	public static final String COLUMN_START_DATE = "tr1__startdate__c";
	
	public static final String COLUMN_END_DATE = "tr1__enddate__c";
	
	public static final String COLUMN_SF_ID = "sfid";
	
	public static final String COLUMN_FULL_NAME = "name";
	
	public static final String GET_OPEN_NOTIFICATIONS = "select count(corecap_status__c) as count from salesforce.CORECaP_Notification__c nf, salesforce.contact cnt where nf.corecap_notificationtype__c='Profile Inactivity' and nf.corecap_contact__c = cnt.sfid and Upper(cnt.email) = ? and  nf.corecap_status__c= 'Open' ";
	
	public static final String GET_PROFILE = "select \r\n" + 
			"	attch.sfid as documentId , attch.name as documentName,attch.createddate as dateAdded,cnt.sfid as contactId,cnt.tr1__language__c as contactSkills,cnt.name as contactName,emphist.sfid as employmentId,emphist.tr1__title__c as employmentName,edhist.sfid as educationID,edhist.tr1__degreename__c as educationName  \r\n" + 
			"	\r\n" + 
			"from \r\n" + 
			"	salesforce.tr1__employmenthistory__c emphist,salesforce.contact cnt,salesforce.Attachment attch,salesforce.tr1__educationhistory__c edhist\r\n" + 
			" where \r\n" + 
			"	emphist.tr1__startdate__c = \r\n" + 
			"		(select max(emphist1.tr1__startdate__c) \r\n" + 
			"			from salesforce.tr1__employmenthistory__c emphist1,salesforce.contact cnt2  \r\n" + 
			"			where cnt2.sfid = emphist1.tr1__contact__c \r\n" + 
			"			and upper(cnt2.email) = ? \r\n" + 
			"		)\r\n" + 
			"\r\n" + 
			"and attch.createddate = \r\n" + 
			"	(select max(attch1.createddate) \r\n" + 
			"		from \r\n" + 
			"			salesforce.Attachment attch1,salesforce.contact cnt1  \r\n" + 
			"		where \r\n" + 
			"			cnt1.sfid = attch1.parentid and upper(cnt1.email) = ? \r\n" + 
			"	)\r\n" + 
			"and edhist.tr1__enddate__c=\r\n" + 
			"		(select max(edhist1.tr1__enddate__c) \r\n" + 
			"			from salesforce.tr1__educationhistory__c edhist1,salesforce.contact cnt3  \r\n" + 
			"			where cnt3.sfid = edhist1.tr1__contact__c \r\n" + 
			"			and upper(cnt3.email) = ?)\r\n" + 
			"and cnt.sfid = attch.parentid and\r\n" + 
			"cnt.sfid = emphist.tr1__contact__c and Upper(cnt.email) = ?\r\n" + 
			"and cnt.sfid = edhist.tr1__contact__c";
	
	public static final String COLUMN_DOCUMENT_ID = "documentId";

	public static final String COLUMN_DOCUMENT_NAME = "documentName";
	
	public static final String COLUMN_DATE_ADDED = "dateAdded";
	
	public static final String COLUMN_CONTACT_ID = "contactId";
	
	public static final String COLUMN_CONTACT_NAME = "contactName";
	
	public static final String COLUMN_CONTACT_SKILL = "contactSkills";
	
	public static final String COLUMN_EMPLOYMENT_ID = "employmentId";
	
	public static final String COLUMN_EMPLOYMENT_NAME = "employmentName";
	
	public static final String COLUMN_EDUCATION_ID = "educationID";
	
	public static final String COLUMN_EDUCATION_NAME = "educationName";
	
	public static final String GET_LATEST_EDUCATION = "select edhist.sfid as educationID,edhist.tr1__degreename__c as educationName \r\n" + 
			"       from salesforce.tr1__educationhistory__c edhist,salesforce.contact cnt     \r\n" + 
			"       \r\n" + 
			"       where edhist.tr1__enddate__c = \r\n" + 
			"       \r\n" + 
			"       (select max(edhist1.tr1__enddate__c)\r\n" + 
			"                                         from salesforce.tr1__educationhistory__c edhist1,salesforce.contact cnt3  \r\n" + 
			"                                         where cnt3.sfid = edhist1.tr1__contact__c \r\n" + 
			"                                         and upper(cnt3.email) = ? HAVING  (max(edhist1.tr1__enddate__c) IS NOT NULL) OR\r\n" + 
			"                      (max(edhist1.tr1__enddate__c) <> '')\r\n" + 
			"                      )\r\n" + 
			"       \r\n" + 
			"        and \r\n" + 
			"                         cnt.sfid = edhist.tr1__contact__c  and upper(cnt.email) = ?";
	
	public static final String GET_LATEST_EMPLOYMENT = "select emphist.sfid as employmentId,emphist.tr1__title__c as employmentName\r\n" + 
			"      from \r\n" + 
			"              salesforce.tr1__employmenthistory__c emphist,salesforce.contact cnt                  \r\n" + 
			"                                         \r\n" + 
			"        where emphist.tr1__startdate__c   =    \r\n" + 
			"                \r\n" + 
			"                       ( select max(emphist1.tr1__startdate__c)\r\n" + 
			"                                   from \r\n" + 
			"                                         salesforce.tr1__employmenthistory__c emphist1,salesforce.contact cnt2 \r\n" + 
			"                                    where \r\n" + 
			"                                     cnt2.sfid = emphist1.tr1__contact__c \r\n" + 
			"                                     and upper(cnt2.email) = ?\r\n" + 
			"                                   HAVING \r\n" + 
			"                                    (max(emphist1.tr1__startdate__c) IS NOT NULL) OR (max(emphist1.tr1__startdate__c) <> '')\r\n" + 
			"                                   )\r\n" + 
			"                                and \r\n" + 
			"                         cnt.sfid = emphist.tr1__contact__c  and upper(cnt.email) = ? ";
	
	public static final String GET_LATEST_ATTACHMENT = "select attch.sfid as documentId , attch.name as documentName,attch.createddate as dateAdded from \r\n" + 
			"\r\n" + 
			"salesforce.Attachment attch,salesforce.contact cnt\r\n" + 
			"where attch.createddate = \r\n" + 
			"\r\n" + 
			"(select max(attch1.createddate) \r\n" + 
			"                                  from \r\n" + 
			"                                         salesforce.Attachment attch1,salesforce.contact cnt1   \r\n" + 
			"                                  where  \r\n" + 
			"                                         cnt1.sfid = attch1.parentid and upper(cnt1.email) = ? )\r\n" + 
			"                                         \r\n" + 
			"                                         \r\n" + 
			"      and cnt.sfid = attch.parentid  and upper(cnt.email) = ? ";
	
	public static final String GET_PERSONAL_DETAILS_AND_SKILLS = "select cnt.sfid as contactId,cnt.tr1__language__c as contactSkills,cnt.name as contactName\r\n" + 
			"         from salesforce.contact cnt\r\n" + 
			"         where upper(cnt.email) = ?";
	
}
