package com.laponhcet.dto;

import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.DateTimeUtil;

public class RegisterDTO extends UserDTO {
	private static final long serialVersionUID = 1L;
	
	public static final String SESSION_REGISTER = "SESSION_REGISTER";
	public static final String SESSION_REGISTER_LIST = "SESSION_REGISTER_LIST";
	public static final String SESSION_REGISTER_PAGINATION = "SESSION_REGISTER_PAGINATION";

	public static final String[] PAGINATION_SEARCH_CRITERIA_LIST = new String[] {"Name", "Institution"};
	
	public static final String REGISTER_STATUS_APPROVED = "APPROVED";
	public static final String REGISTER_STATUS_FOR_APPROVAL = "FOR APPROVAL";
	public static final String REGISTER_STATUS_APPLIED = "APPLIED";
	public static final String REGISTER_STATUS_REJECTED = "REJECTED";
	
	private String institutionConnectedWith;
	private String status;
	private String repeatPassword;

	public RegisterDTO() {
		super();
		super.setBirthDate(DateTimeUtil.getStrToDateTime("1970-01-01", "yyyy-MM-dd"));
		super.setTableName("register");
		this.institutionConnectedWith = "";
		this.status = REGISTER_STATUS_FOR_APPROVAL;
		this.repeatPassword = "";
	}

	public RegisterDTO getRegister() {
		RegisterDTO register = new RegisterDTO();
		register.setId(super.getId());
		register.setCode(super.getCode());
		register.setProfilePict(super.getProfilePict());
		register.setPrefixName(super.getPrefixName());
		register.setLastName(super.getLastName());
		register.setFirstName(super.getFirstName());
		register.setMiddleName(super.getMiddleName());
		register.setSuffixName(super.getSuffixName());
		register.setGender(super.getGender());
		register.setBirthDate(super.getBirthDate());
		register.setCpNumber(super.getCpNumber());
		register.setEmailAddress(super.getEmailAddress());
		register.setInstitutionConnectedWith(this.institutionConnectedWith);
		register.setOccupation(super.getOccupation());
		register.setInstitutionConnectedWith(this.institutionConnectedWith);
		register.setStatus(this.status);
		return register;
	}
	
	public String getInstitutionConnectedWith() {
		return institutionConnectedWith;
	}
	public void setInstitutionConnectedWith(String institutionConnectedWith) {
		this.institutionConnectedWith = institutionConnectedWith;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRepeatPassword() {
		return repeatPassword;
	}
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
	
}
