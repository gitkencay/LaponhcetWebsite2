package com.laponhcet.dto;
import com.mytechnopal.base.DTOBase;

public class UserRFIDDTO extends DTOBase {
	private static final long serialVersionUID = 1L;	
	
	public static final String SESSION_USERRFID = "SESSION_USERRFID";
	public static final String SESSION_USERRFID_LIST = "SESSION_USERRFID_LIST";
	
	private String profilePict;
	private String rfid;
	private String lastName;
	private String firstName;
	private String middleName;
	private String prefixName;
	private String suffixName;
	private String otherTitle;
	private String gender;
	private String cpNumber;
	private String contactCPNumber;
	
	public UserRFIDDTO() {
		super();
		profilePict = "";
		rfid = "";
		lastName = "";
		firstName = "";
		middleName = "";
		prefixName = "";
		suffixName = "";
		otherTitle = "";
		gender = "";
		cpNumber = "";
		contactCPNumber = "";	
	}

	public UserRFIDDTO getUserRFID() {
		UserRFIDDTO userRFID = new UserRFIDDTO();
		userRFID.setId(super.getId());
		userRFID.setCode(super.getCode());
		userRFID.setProfilePict(this.getProfilePict());
		userRFID.setRfid(this.rfid);
		userRFID.setLastName(this.lastName);
		userRFID.setFirstName(this.firstName);
		userRFID.setMiddleName(this.middleName);
		userRFID.setPrefixName(this.prefixName);
		userRFID.setSuffixName(this.suffixName);
		userRFID.setOtherTitle(this.otherTitle);
		userRFID.setGender(this.gender);
		userRFID.setCpNumber(this.cpNumber);
		userRFID.setContactCPNumber(this.contactCPNumber);
		return userRFID;
	}

	public String getProfilePict() {
		return profilePict;
	}

	public void setProfilePict(String profilePict) {
		this.profilePict = profilePict;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPrefixName() {
		return prefixName;
	}

	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}

	public String getSuffixName() {
		return suffixName;
	}

	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	public String getOtherTitle() {
		return otherTitle;
	}

	public void setOtherTitle(String otherTitle) {
		this.otherTitle = otherTitle;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCpNumber() {
		return cpNumber;
	}

	public void setCpNumber(String cpNumber) {
		this.cpNumber = cpNumber;
	}

	public String getContactCPNumber() {
		return contactCPNumber;
	}

	public void setContactCPNumber(String contactCPNumber) {
		this.contactCPNumber = contactCPNumber;
	}
}
