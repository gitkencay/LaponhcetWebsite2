package com.laponhcet.dto;

import com.laponhcet.util.SettingsUtil;
import com.mytechnopal.dto.CityDTO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.DateTimeUtil;

public class PatientDTO extends UserDTO {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_PATIENT = "SESSION_PATIENT";
	public static final String SESSION_PATIENT_LIST = "SESSION_PATIENT_LIST";
	public static final String SESSION_PATIENT_PAGINATION = "SESSION_PATIENT_PAGINATION";	
	
	public static final String[] PAGINATION_SEARCH_CRITERIA_LIST = new String[] {"Name or ID"};

	public static final String AWARENESS_METHOD_WALKIN = "WALK IN";
	public static final String AWARENESS_METHOD_ADVERTISEMENT = "ADVERTISEMENT";
	public static final String AWARENESS_METHOD_INTERNET = "INTERNET";
	public static final String AWARENESS_METHOD_FRIEND = "FRIEND";
	
	private String streetOffice;
	private String barangayOffice;
	private CityDTO cityOffice;
	private String awarenessMethod;
	
	public PatientDTO() {
		super();
		super.getReligion().setCode(SettingsUtil.DEFAULT_RELIGION);
		super.getCityPermanent().setCode(SettingsUtil.DEFAULT_CITY);
		super.getCityPresent().setCode(SettingsUtil.DEFAULT_CITY);
		super.setBirthDate(DateTimeUtil.getStrToDateTime("1970-01-01", "yyyy-MM-dd"));
		streetOffice = "";
		barangayOffice = "";
		cityOffice = new CityDTO();
		awarenessMethod = AWARENESS_METHOD_WALKIN;
	}
	
	public PatientDTO getPatient() {
		PatientDTO patient = new PatientDTO();
		
		//User specific arrange based from user table sequence
		patient.setProfilePict(super.getProfilePict());
		patient.setRfid(super.getRfid());
		patient.setFacebookId(super.getFacebookId());
		patient.setUserName(super.getUserName());
		patient.setPassword(super.getPassword());
		patient.setUserGroupCodes(super.getUserGroupCodes());
		patient.setLastName(super.getLastName());
		patient.setFirstName(super.getFirstName());
		patient.setMiddleName(super.getMiddleName());
		patient.setPrefixName(super.getPrefixName());
		patient.setSuffixName(super.getSuffixName());
		patient.setOtherTitle(super.getOtherTitle());
		patient.setGender(super.getGender());
		patient.setStreetPermanent(super.getStreetPermanent());
		patient.setBarangayPermanent(super.getBarangayPermanent());
		patient.setCityPermanent(super.getCityPermanent());
		patient.setStreetPresent(super.getStreetPresent());
		patient.setBarangayPresent(super.getBarangayPresent());
		patient.setCityPresent(super.getCityPresent());
		patient.setBirthPlace(super.getBirthPlace());
		patient.setBirthDate(super.getBirthDate());
		patient.setReligion(super.getReligion());
		patient.setMaritalStatus(super.getMaritalStatus());
		patient.setCitizenship(super.getCitizenship());
		patient.setPassportNumber(super.getPassportNumber());
		patient.setOccupation(super.getOccupation());
		patient.setCpNumber(super.getCpNumber());
		patient.setLandlineNumber(super.getLandlineNumber());
		patient.setEmailAddress(super.getEmailAddress());
		patient.setFatherName(super.getFatherName());
		patient.setFatherOccupation(super.getFatherOccupation());
		patient.setFatherCpNumber(super.getFatherCpNumber());
		patient.setMotherName(super.getMotherName());
		patient.setMotherOccupation(super.getMotherOccupation());
		patient.setMotherCpNumber(super.getMotherCpNumber());
		patient.setGuardianName(super.getGuardianName());
		patient.setGuardianOccupation(super.getGuardianOccupation());
		patient.setGuardianRelation(super.getGuardianRelation());
		patient.setContactPerson(super.getContactPerson());
		patient.setContactRelation(super.getContactRelation());
		patient.setContactAddress(super.getContactAddress());
		patient.setContactCPNumber(super.getContactCPNumber());
		patient.setContactLandlineNumber(super.getContactLandlineNumber());
		patient.setContactEmailAddress(super.getContactEmailAddress());
		patient.setContactFacebookId(super.getContactFacebookId());
		patient.setActive(super.isActive());
		
		patient.setId(this.getId());
		patient.setCode(super.getCode());
		patient.setStreetOffice(this.streetOffice);
		patient.setBarangayOffice(this.barangayOffice);
		patient.setCityOffice(this.cityOffice);
		patient.setAwarenessMethod(this.awarenessMethod);
		
		return patient;
	}

	public String getStreetOffice() {
		return streetOffice;
	}

	public void setStreetOffice(String streetOffice) {
		this.streetOffice = streetOffice;
	}

	public String getBarangayOffice() {
		return barangayOffice;
	}

	public void setBarangayOffice(String barangayOffice) {
		this.barangayOffice = barangayOffice;
	}

	public CityDTO getCityOffice() {
		return cityOffice;
	}

	public void setCityOffice(CityDTO cityOffice) {
		this.cityOffice = cityOffice;
	}

	public String getAwarenessMethod() {
		return awarenessMethod;
	}

	public void setAwarenessMethod(String awarenessMethod) {
		this.awarenessMethod = awarenessMethod;
	}
}
