package com.laponhcet.action.student;

import java.util.List;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.StudentDTO;
import com.laponhcet.dto.SchoolDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.CityDTO;
import com.mytechnopal.dto.ReligionDTO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.dto.UserGroupDTO;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;
import com.mytechnopal.util.UserUtil;
import com.mytechnopal.util.WebUtil;

public class StudentAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setInput() {
		StudentDTO student = (StudentDTO) getSessionAttribute(StudentDTO.SESSION_STUDENT);
		List<DTOBase> cityList = (List<DTOBase>) getSessionAttribute(CityDTO.SESSION_CITY_LIST);
		List<DTOBase> religionList = (List<DTOBase>) getSessionAttribute(ReligionDTO.SESSION_RELIGION_LIST);
		List<DTOBase> academicProgramList = (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
		List<DTOBase> schoolList = (List<DTOBase>) getSessionAttribute(SchoolDTO.SESSION_SCHOOL_LIST);
		
		//Personal Information
		student.getProfilePict().setPict(getRequestString("txtProfilePict", true));
		student.setRfid(getRequestString("txtRfid"));
		student.setFacebookId(getRequestString("txtFacebookId"));
		student.setLearnerReferenceNumber(getRequestString("txtLearnerReferenceNumber"));
		if(getRequestInt("cboAcademicProgram")!=0) {
			student.setAcademicProgram((AcademicProgramDTO) DTOUtil.getObjById(academicProgramList, getRequestInt("cboAcademicProgram")));
		}
		student.setCpNumber(getRequestString("txtCpNumber"));
		student.setLandlineNumber(getRequestString("txtLandlineNumber"));
		student.setEmailAddress(getRequestString("txtEmailAddress"));
		student.setPrefixName(getRequestString("cboPrefixName"));
		student.setLastName(getRequestString("txtLastName"));
		student.setFirstName(getRequestString("txtFirstName"));
		student.setMiddleName(getRequestString("txtMiddleName"));
		student.setSuffixName(getRequestString("cboSuffixName"));
		
		// Address
		student.setStreetPermanent(getRequestString("txtStreetPermanent"));
		student.setBarangayPermanent(getRequestString("txtBarangayPermanent"));
		student.setCityPermanent((CityDTO) DTOUtil.getObjById(cityList, getRequestInt("cboCityPermanent")));
		// If Present Address == Permanent Address
		if(getRequestBoolean("chkSameAsPermanent")) {
			student.setStreetPresent(student.getStreetPermanent());
			student.setBarangayPresent(student.getBarangayPermanent());
			student.setCityPresent(student.getCityPermanent());
		}
		else {
			student.setStreetPresent(getRequestString("txtStreetPresent"));
			student.setBarangayPresent(getRequestString("txtBarangayPresent"));
			student.setCityPresent((CityDTO) DTOUtil.getObjById(cityList, getRequestInt("cboCityPresent")));
		}
		
		student.setBirthPlace(getRequestString("txtBirthPlace"));
		student.setBirthDate(DateTimeUtil.getStrToDateTime(getRequestString("txtBirthDate"), "MM/dd/yyyy"));
		student.setGender(getRequestString("cboGender"));
		student.setReligion((ReligionDTO) DTOUtil.getObjById(religionList, getRequestInt("cboReligion")));
		student.setMaritalStatus(getRequestString("cboMaritalStatus"));
		student.setCitizenship(StringUtil.getStrFromStrArr(StringUtil.getCountryList(), getRequestString("cboCitizenship")));
		student.setPassportNumber(getRequestString("txtPassportNumber"));
		
		// Scholastic - Elementary
		if(getRequestInt("cboElementarySchoolCompletedAt") != 0) {
			student.setElementarySchoolCompletedAt((SchoolDTO) DTOUtil.getObjById(schoolList, getRequestInt("cboElementarySchoolCompletedAt")));
			student.setElementarySchoolGraduatedYear(getRequestInt("txtElementarySchoolGraduatedYear"));
		}
		
		// Scholastic - Junior High School
		if(getRequestInt("cboJuniorHighSchoolCompletedAt") != 0) {
			student.setJuniorHighSchoolCompletedAt((SchoolDTO) DTOUtil.getObjById(schoolList, getRequestInt("cboJuniorHighSchoolCompletedAt")));
			student.setJuniorHighSchoolGraduatedYear(getRequestInt("txtJuniorHighSchoolGraduatedYear"));
		}
		
		// Scholastic - Senior High School
		if(getRequestInt("cboSeniorHighSchoolCompletedAt") != 0) {
			student.setSeniorHighSchoolCompletedAt((SchoolDTO) DTOUtil.getObjById(schoolList, getRequestInt("cboSeniorHighSchoolCompletedAt")));
			student.setSeniorHighSchoolGraduatedYear(getRequestInt("txtSeniorHighSchoolGraduatedYear"));
		}
		
		// Scholastic - Vocational School
		if(getRequestInt("cboVocationalSchoolCompletedAt") != 0) {
			student.setVocationalSchoolCompletedAt((SchoolDTO) DTOUtil.getObjById(schoolList, getRequestInt("cboVocationalSchoolCompletedAt")));
			student.setVocationalSchoolGraduatedYear(getRequestInt("txtVocationalSchoolGraduatedYear"));
		}
		
		// Scholastic - College School
		if(getRequestInt("cboCollegeSchoolCompletedAt") != 0) {
			student.setCollegeSchoolCompletedAt((SchoolDTO) DTOUtil.getObjById(schoolList, getRequestInt("cboCollegeSchoolCompletedAt")));
			student.setCollegeSchoolGraduatedYear(getRequestInt("txtCollegeSchoolGraduatedYear"));
		}
		
		// Scholastic - Graduate School
		if(getRequestInt("cboGraduateSchoolCompletedAt") != 0) {
			student.setGraduateSchoolCompletedAt((SchoolDTO) DTOUtil.getObjById(schoolList, getRequestInt("cboGraduateSchoolCompletedAt")));
			student.setGraduateSchoolGraduatedYear(getRequestInt("txtGraduateSchoolGraduatedYear"));
		}
		
		// Scholastic - Last School Attended
		if(getRequestInt("cboSchoolLastAttendedAt") != 0) {
			student.setSchoolLastAttendedAt((SchoolDTO) DTOUtil.getObjById(schoolList, getRequestInt("cboSchoolLastAttendedAt")));
			student.setSchoolLastAttendedYear(getRequestInt("txtSchoolLastAttendedYear"));
		}
		
		// Scholastic - Others
		student.setHighestEducationalAttainment(getRequestString("txtHighestEducationalAttainment"));
		student.setHighestEducationalAttainmentMajor(getRequestString("txtHighestEducationalAttainmentMajor"));
		student.setEntranceCredentials(getRequestString("txtEntranceCredentials"));
		student.setSkills(getRequestString("txtSkills"));
		
		// Parent or Guardian Information
		student.setFatherName(getRequestString("txtFatherName"));
		student.setFatherOccupation(getRequestString("txtFatherOccupation"));
		student.setFatherCpNumber(getRequestString("txtFatherCpNumber"));
		student.setMotherName(getRequestString("txtMotherName"));
		student.setMotherOccupation(getRequestString("txtMotherOccupation"));
		student.setMotherCpNumber(getRequestString("txtMotherCpNumber"));
		student.setGuardianName(getRequestString("txtGuardianName"));
		student.setGuardianOccupation(getRequestString("txtGuardianOccupation"));
		student.setGuardianRelation(getRequestString("txtGuardianRelation"));
		
		// Contact Information
		student.setContactPerson(getRequestString("txtContactPerson"));
		student.setContactRelation(getRequestString("txtContactRelation"));
		student.setContactAddress(getRequestString("txtContactAddress"));
		student.setContactCPNumber(getRequestString("txtContactCPNumber"));
		student.setContactLandlineNumber(getRequestString("txtContactLandlineNumber"));
		student.setContactEmailAddress(getRequestString("txtContactEmailAddress"));
		student.setContactFacebookId(getRequestString("txtContactFacebookId"));
	}

	
	protected void validateInput() {
		if(!sessionInfo.isCurrentLinkDeleteSubmit()){
			StudentDTO student = (StudentDTO) getSessionAttribute(StudentDTO.SESSION_STUDENT);
						
			//Academic Program
			if(getRequestInt("cboAcademicProgram")==0) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Academic Program");
			}
			// Student Phone Number
			else if(!StringUtil.isEmpty(student.getCpNumber()) && !StringUtil.isValidCPNumber(student.getCpNumber())) {
				actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Cellphone Number");
			}
			// Student Email Address
			else if(!StringUtil.isEmpty(student.getEmailAddress()) && !WebUtil.isValidEmail(student.getEmailAddress())) {
				actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Email Address is not valid!");
			}
			// Last Name and First Name
			// Other have no middle name
			else if(StringUtil.isEmpty(student.getLastName())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Last Name");
			}
			else if(StringUtil.isEmpty(student.getFirstName())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "First Name");
			}
			// Prefix Name and Gender
			else if(!StringUtil.isEmpty(student.getPrefixName())) {
				if(student.getGender().equalsIgnoreCase(UserDTO.GENDER_FEMALE)) {
					if(!student.isGenderFemaleByPrefixName(student.getPrefixName())) {
						if(student.isGenderMaleByPrefixName(student.getPrefixName())) {
							actionResponse.constructMessage(ActionResponse.TYPE_MISMATCH, new String[]{student.getPrefixName(), student.getGender()});
						}
					}
				}
				else if(student.getGender().equalsIgnoreCase(UserDTO.GENDER_MALE)) {
					if(!student.isGenderMaleByPrefixName(student.getPrefixName())) {
						if(student.isGenderFemaleByPrefixName(student.getPrefixName())) {
							actionResponse.constructMessage(ActionResponse.TYPE_MISMATCH, new String[]{student.getPrefixName(), student.getGender()});
						}
					}
				}
			}
			// Permanent Street/Lot/Block
			else if(StringUtil.isEmpty(student.getStreetPermanent())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Permanent Street");
			}// Permanent Barangay
			else if(StringUtil.isEmpty(student.getStreetPermanent())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Permanent Barangay");
			}
			// Present Street/Lot/Block
			else if(StringUtil.isEmpty(student.getStreetPresent())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Present Street");
			}
			// Present Barangay
			else if(StringUtil.isEmpty(student.getStreetPresent())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Present Barangay");
			}
			// Birthday
			else if(DateTimeUtil.getNumberOfMonths(student.getBirthDate(), DateTimeUtil.getCurrentTimestamp()) < 24){
				actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Birth Date");
			}
			// Mother Phone Number
			else if(!StringUtil.isEmpty(student.getMotherCpNumber()) && !StringUtil.isValidCPNumber(student.getMotherCpNumber())) {
				actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Mother's Cellphone Number");
			}
			// Father Phone Number
			else if(!StringUtil.isEmpty(student.getFatherCpNumber()) && !StringUtil.isValidCPNumber(student.getFatherCpNumber())) {
				actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Father's Cellphone Number");
			}
			// Contact Person Name
			else if(StringUtil.isEmpty(student.getContactPerson())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Contact Person's Name");
			}
			// Contact Person Relation
			else if(StringUtil.isEmpty(student.getContactPerson())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Contact Person's Relationship");
			}
			// Contact Person Address
			else if(StringUtil.isEmpty(student.getContactAddress())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Contact Person's Address");
			}
			// Contact Person Number
			else if(!StringUtil.isEmpty(student.getContactCPNumber()) && !StringUtil.isValidCPNumber(student.getContactCPNumber())){
					actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Contact Person's Cellphone Number");
			}
			else {
				if(sessionInfo.isPreviousLinkAdd()) {
					String msg = "";
					if(StringUtil.isEmpty(student.getCode())) {
						List<DTOBase> existingUserListByNameAndGroup = new UserDAO().getUserListByNameUserGroupCode(student.getLastName(), student.getFirstName(), student.getMiddleName(), UserGroupDTO.USER_GROUP_STUDENT_CODE);
						
						if(existingUserListByNameAndGroup.size() >= 1) {
							if(existingUserListByNameAndGroup.size() == 1) {
								UserDTO existingUser = (UserDTO) existingUserListByNameAndGroup.get(0);
								if(existingUser != null) {
									msg = "Entered student last, first and middle name is already existing in our database.  The existing record has a birthday of " + DateTimeUtil.getDateTimeToStr(existingUser.getBirthDate(), "MM/dd/yyyy");
								}	
							}
							else {
								for(DTOBase userObj: existingUserListByNameAndGroup) {
									UserDTO existingUser = (UserDTO) userObj;
									if(existingUser != null) {
										if(StringUtil.isEmpty(msg)) {
											msg = "Entered student last, first and middle name is already existing in our database.  The existing records has the following birthdays: ";
										}
										msg += "<br>" + DateTimeUtil.getDateTimeToStr(existingUser.getBirthDate(), "MM/dd/yyyy");
									}
								}
							}
							msg += "\nProceeding will result to create a duplicate names unless they had been proven different.";
						}	
					}	
					
					if(StringUtil.isEmpty(msg)) {
						if(!StringUtil.isEmpty(student.getRfid())) {
							UserDTO existingUser = new UserDAO().getUserByRFId(student.getRfid());
							if(existingUser != null) {
								msg = "RFID was already encoded to " + existingUser.getName(false, false, true);
							}
						}
					}
					
					if(StringUtil.isEmpty(msg)) {
						if(!StringUtil.isEmpty(student.getCpNumber())) {
							UserDTO existingUser = UserUtil.getUserByCpNumber(student.getCpNumber());
							if(existingUser != null) {
								msg = "CP Number was already encoded to " + existingUser.getName(false, false, true);
							}
						}
					}
					
					if(!StringUtil.isEmpty(msg)) {
						actionResponse.constructMessage(ActionResponse.TYPE_INFO, msg);
					}
				}	
				else if(sessionInfo.isPreviousLinkUpdate()) {
					StudentDTO studentOrig = (StudentDTO) getSessionAttribute(StudentDTO.SESSION_STUDENT + "_ORIG");
					if(studentOrig.equals(student)){
						actionResponse.constructMessage(ActionResponse.TYPE_INFO, "No change has been done.");
					}
				}
			}
		}
	}
}
