package com.laponhcet.dto;

import com.laponhcet.util.SettingsUtil;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.DateTimeUtil;

public class StudentDTO extends UserDTO {
	private static final long serialVersionUID = 1L;
	
	public static final String SESSION_STUDENT = "SESSION_STUDENT";
	public static final String SESSION_STUDENT_LIST = "SESSION_STUDENT_LIST";
	public static final String SESSION_STUDENT_PAGINATION = "SESSION_STUDENT_PAGINATION";	
	
	public static final String[] PAGINATION_SEARCH_CRITERIA_LIST = new String[] {"Name or ID", "Program"};

	private String learnerReferenceNumber;
	private AcademicProgramDTO academicProgram;
	private String skills;
	private SchoolDTO elementarySchoolCompletedAt;
	private int elementarySchoolGraduatedYear;
	private SchoolDTO juniorHighSchoolCompletedAt;
	private int juniorHighSchoolGraduatedYear;
	private SchoolDTO seniorHighSchoolCompletedAt;
	private int seniorHighSchoolGraduatedYear;
	private SchoolDTO vocationalSchoolCompletedAt;
	private int vocationalSchoolGraduatedYear;
	private SchoolDTO collegeSchoolCompletedAt;
	private int collegeSchoolGraduatedYear;
	private SchoolDTO graduateSchoolCompletedAt;
	private int graduateSchoolGraduatedYear;
	private SchoolDTO schoolLastAttendedAt;
	private int schoolLastAttendedYear;
	private String highestEducationalAttainment;
	private String highestEducationalAttainmentMajor;
	private String entranceCredentials;
	
	public StudentDTO() {
		super();
		super.setTableName("student");
		super.getReligion().setCode(SettingsUtil.DEFAULT_RELIGION);
		super.getCityPermanent().setCode(SettingsUtil.DEFAULT_CITY);
		super.getCityPresent().setCode(SettingsUtil.DEFAULT_CITY);
		super.setBirthDate(DateTimeUtil.getStrToDateTime("1970-01-01", "yyyy-MM-dd"));
		learnerReferenceNumber = "";
		academicProgram = new AcademicProgramDTO();
		skills = "";
		elementarySchoolCompletedAt = new SchoolDTO();
		elementarySchoolGraduatedYear = 0;
		juniorHighSchoolCompletedAt = new SchoolDTO();
		juniorHighSchoolGraduatedYear = 0;
		seniorHighSchoolCompletedAt = new SchoolDTO();
		seniorHighSchoolGraduatedYear = 0;
		vocationalSchoolCompletedAt = new SchoolDTO();
		vocationalSchoolGraduatedYear = 0;
		collegeSchoolCompletedAt = new SchoolDTO();
		collegeSchoolGraduatedYear = 0;
		graduateSchoolCompletedAt = new SchoolDTO();
		graduateSchoolGraduatedYear = 0;
		
		schoolLastAttendedAt = new SchoolDTO();
		schoolLastAttendedYear = 0;
		highestEducationalAttainment = "";
		highestEducationalAttainmentMajor = "";
		entranceCredentials = "";
	}
	
	public StudentDTO getStudent() {
		StudentDTO student = new StudentDTO();
		
		//User specific arrange based from user table sequence
		student.setProfilePict(super.getProfilePict());
		student.setRfid(super.getRfid());
		student.setFacebookId(super.getFacebookId());
		student.setUserName(super.getUserName());
		student.setPassword(super.getPassword());
		student.setUserGroupCodes(super.getUserGroupCodes());
		student.setLastName(super.getLastName());
		student.setFirstName(super.getFirstName());
		student.setMiddleName(super.getMiddleName());
		student.setPrefixName(super.getPrefixName());
		student.setSuffixName(super.getSuffixName());
		student.setOtherTitle(super.getOtherTitle());
		student.setGender(super.getGender());
		student.setStreetPermanent(super.getStreetPermanent());
		student.setBarangayPermanent(super.getBarangayPermanent());
		student.setCityPermanent(super.getCityPermanent());
		student.setStreetPresent(super.getStreetPresent());
		student.setBarangayPresent(super.getBarangayPresent());
		student.setCityPresent(super.getCityPresent());
		student.setBirthPlace(super.getBirthPlace());
		student.setBirthDate(super.getBirthDate());
		student.setReligion(super.getReligion());
		student.setMaritalStatus(super.getMaritalStatus());
		student.setCitizenship(super.getCitizenship());
		student.setPassportNumber(super.getPassportNumber());
		student.setOccupation(super.getOccupation());
		student.setCpNumber(super.getCpNumber());
		student.setLandlineNumber(super.getLandlineNumber());
		student.setEmailAddress(super.getEmailAddress());
		student.setFatherName(super.getFatherName());
		student.setFatherOccupation(super.getFatherOccupation());
		student.setFatherCpNumber(super.getFatherCpNumber());
		student.setMotherName(super.getMotherName());
		student.setMotherOccupation(super.getMotherOccupation());
		student.setMotherCpNumber(super.getMotherCpNumber());
		student.setGuardianName(super.getGuardianName());
		student.setGuardianOccupation(super.getGuardianOccupation());
		student.setGuardianRelation(super.getGuardianRelation());
		student.setContactPerson(super.getContactPerson());
		student.setContactRelation(super.getContactRelation());
		student.setContactAddress(super.getContactAddress());
		student.setContactCPNumber(super.getContactCPNumber());
		student.setContactLandlineNumber(super.getContactLandlineNumber());
		student.setContactEmailAddress(super.getContactEmailAddress());
		student.setContactFacebookId(super.getContactFacebookId());
		student.setActive(super.isActive());
		
		//Student Specific arrange based from student table sequence
		student.setId(this.getId());
		student.setCode(super.getCode());
		student.setLearnerReferenceNumber(this.learnerReferenceNumber);
		student.setAcademicProgram(this.academicProgram);
		student.setSkills(this.skills);
		student.setElementarySchoolCompletedAt(this.elementarySchoolCompletedAt);
		student.setElementarySchoolGraduatedYear(this.getElementarySchoolGraduatedYear());
		student.setJuniorHighSchoolCompletedAt(this.getJuniorHighSchoolCompletedAt());
		student.setJuniorHighSchoolGraduatedYear(this.getJuniorHighSchoolGraduatedYear());
		student.setSeniorHighSchoolCompletedAt(this.seniorHighSchoolCompletedAt);
		student.setSeniorHighSchoolGraduatedYear(this.seniorHighSchoolGraduatedYear);
		student.setVocationalSchoolCompletedAt(this.vocationalSchoolCompletedAt);
		student.setVocationalSchoolGraduatedYear(this.vocationalSchoolGraduatedYear);
		student.setCollegeSchoolCompletedAt(this.getCollegeSchoolCompletedAt());
		student.setCollegeSchoolGraduatedYear(this.getCollegeSchoolGraduatedYear());
		student.setGraduateSchoolCompletedAt(this.graduateSchoolCompletedAt);
		student.setGraduateSchoolGraduatedYear(this.graduateSchoolGraduatedYear);
		student.setSchoolLastAttendedAt(this.getSchoolLastAttendedAt());
		student.setSchoolLastAttendedYear(this.getSchoolLastAttendedYear());
		student.setHighestEducationalAttainment(this.getHighestEducationalAttainment());
		student.setHighestEducationalAttainmentMajor(this.highestEducationalAttainmentMajor);
		student.setEntranceCredentials(this.getEntranceCredentials());
		return student;
	}

	public String getLearnerReferenceNumber() {
		return learnerReferenceNumber;
	}

	public void setLearnerReferenceNumber(String learnerReferenceNumber) {
		this.learnerReferenceNumber = learnerReferenceNumber;
	}

	public AcademicProgramDTO getAcademicProgram() {
		return academicProgram;
	}

	public void setAcademicProgram(AcademicProgramDTO academicProgram) {
		this.academicProgram = academicProgram;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public SchoolDTO getElementarySchoolCompletedAt() {
		return elementarySchoolCompletedAt;
	}

	public void setElementarySchoolCompletedAt(SchoolDTO elementarySchoolCompletedAt) {
		this.elementarySchoolCompletedAt = elementarySchoolCompletedAt;
	}

	public int getElementarySchoolGraduatedYear() {
		return elementarySchoolGraduatedYear;
	}

	public void setElementarySchoolGraduatedYear(int elementarySchoolGraduatedYear) {
		this.elementarySchoolGraduatedYear = elementarySchoolGraduatedYear;
	}

	public SchoolDTO getJuniorHighSchoolCompletedAt() {
		return juniorHighSchoolCompletedAt;
	}

	public void setJuniorHighSchoolCompletedAt(SchoolDTO juniorHighSchoolCompletedAt) {
		this.juniorHighSchoolCompletedAt = juniorHighSchoolCompletedAt;
	}

	public int getJuniorHighSchoolGraduatedYear() {
		return juniorHighSchoolGraduatedYear;
	}

	public void setJuniorHighSchoolGraduatedYear(int juniorHighSchoolGraduatedYear) {
		this.juniorHighSchoolGraduatedYear = juniorHighSchoolGraduatedYear;
	}

	public SchoolDTO getSeniorHighSchoolCompletedAt() {
		return seniorHighSchoolCompletedAt;
	}

	public void setSeniorHighSchoolCompletedAt(SchoolDTO seniorHighSchoolCompletedAt) {
		this.seniorHighSchoolCompletedAt = seniorHighSchoolCompletedAt;
	}

	public int getSeniorHighSchoolGraduatedYear() {
		return seniorHighSchoolGraduatedYear;
	}

	public void setSeniorHighSchoolGraduatedYear(int seniorHighSchoolGraduatedYear) {
		this.seniorHighSchoolGraduatedYear = seniorHighSchoolGraduatedYear;
	}

	public SchoolDTO getVocationalSchoolCompletedAt() {
		return vocationalSchoolCompletedAt;
	}

	public void setVocationalSchoolCompletedAt(SchoolDTO vocationalSchoolCompletedAt) {
		this.vocationalSchoolCompletedAt = vocationalSchoolCompletedAt;
	}

	public int getVocationalSchoolGraduatedYear() {
		return vocationalSchoolGraduatedYear;
	}

	public void setVocationalSchoolGraduatedYear(int vocationalSchoolGraduatedYear) {
		this.vocationalSchoolGraduatedYear = vocationalSchoolGraduatedYear;
	}

	public SchoolDTO getCollegeSchoolCompletedAt() {
		return collegeSchoolCompletedAt;
	}

	public void setCollegeSchoolCompletedAt(SchoolDTO collegeSchoolCompletedAt) {
		this.collegeSchoolCompletedAt = collegeSchoolCompletedAt;
	}

	public int getCollegeSchoolGraduatedYear() {
		return collegeSchoolGraduatedYear;
	}

	public void setCollegeSchoolGraduatedYear(int collegeSchoolGraduatedYear) {
		this.collegeSchoolGraduatedYear = collegeSchoolGraduatedYear;
	}

	public SchoolDTO getGraduateSchoolCompletedAt() {
		return graduateSchoolCompletedAt;
	}

	public void setGraduateSchoolCompletedAt(SchoolDTO graduateSchoolCompletedAt) {
		this.graduateSchoolCompletedAt = graduateSchoolCompletedAt;
	}

	public int getGraduateSchoolGraduatedYear() {
		return graduateSchoolGraduatedYear;
	}

	public void setGraduateSchoolGraduatedYear(int graduateSchoolGraduatedYear) {
		this.graduateSchoolGraduatedYear = graduateSchoolGraduatedYear;
	}

	public SchoolDTO getSchoolLastAttendedAt() {
		return schoolLastAttendedAt;
	}

	public void setSchoolLastAttendedAt(SchoolDTO schoolLastAttendedAt) {
		this.schoolLastAttendedAt = schoolLastAttendedAt;
	}

	public int getSchoolLastAttendedYear() {
		return schoolLastAttendedYear;
	}

	public void setSchoolLastAttendedYear(int schoolLastAttendedYear) {
		this.schoolLastAttendedYear = schoolLastAttendedYear;
	}

	public String getHighestEducationalAttainment() {
		return highestEducationalAttainment;
	}

	public void setHighestEducationalAttainment(String highestEducationalAttainment) {
		this.highestEducationalAttainment = highestEducationalAttainment;
	}

	public String getHighestEducationalAttainmentMajor() {
		return highestEducationalAttainmentMajor;
	}

	public void setHighestEducationalAttainmentMajor(String highestEducationalAttainmentMajor) {
		this.highestEducationalAttainmentMajor = highestEducationalAttainmentMajor;
	}

	public String getEntranceCredentials() {
		return entranceCredentials;
	}

	public void setEntranceCredentials(String entranceCredentials) {
		this.entranceCredentials = entranceCredentials;
	}
}
