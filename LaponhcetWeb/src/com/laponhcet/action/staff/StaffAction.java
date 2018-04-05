package com.laponhcet.action.staff;

import java.util.List;

import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.StaffDTO;
import com.laponhcet.util.AcademicProgramUtil;
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
import com.mytechnopal.util.WebUtil;

public class StaffAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	protected void setInput() {
		StaffDTO staff = (StaffDTO) getSessionAttribute(StaffDTO.SESSION_STAFF);
		List<DTOBase> cityList = (List<DTOBase>) getSessionAttribute(CityDTO.SESSION_CITY_LIST);
		List<DTOBase> religionList = (List<DTOBase>) getSessionAttribute(ReligionDTO.SESSION_RELIGION_LIST);
		List<DTOBase> academicProgramList = (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
		List<DTOBase> academicProgramGroupList = (List<DTOBase>) getSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST);
		
		staff.setRfid(getRequestString("txtRfid"));
		staff.setPrefixName(getRequestString("cboPrefixName"));
		staff.setLastName(getRequestString("txtLastName"));
		staff.setFirstName(getRequestString("txtFirstName"));
		staff.setMiddleName(getRequestString("txtMiddleName"));
		staff.setSuffixName(getRequestString("cboSuffixName"));
		staff.setOtherTitle(getRequestString("txtOtherTitle"));
		
		staff.setStreetPermanent(getRequestString("txtStreetPermanent"));
		staff.setBarangayPermanent(getRequestString("txtBarangayPermanent"));
		staff.setCityPermanent((CityDTO)DTOUtil.getObjById(cityList, getRequestInt("cboCityPermanent")));

	    if(getRequestInt("txtIfChecked") == 1){
				staff.setStreetPresent(staff.getStreetPermanent());
				staff.setBarangayPresent(staff.getBarangayPermanent());
				staff.setCityPresent(staff.getCityPermanent());
				
			}else{
				staff.setStreetPresent(getRequestString("txtStreetPresent"));
				staff.setBarangayPresent(getRequestString("txtBarangayPresent"));
				staff.setCityPresent((CityDTO) DTOUtil.getObjById(cityList, getRequestInt("cboCityPresent")));
				
			}

		staff.setBirthDate(getRequestDateTime("txtBirthDate", "MM/dd/yyyy"));
		staff.setGender(getRequestString("cboGender"));
		staff.setReligion((ReligionDTO) DTOUtil.getObjById(religionList, getRequestInt("cboReligion")));
		staff.setMaritalStatus(getRequestString("cboStatus"));
		staff.setCpNumber(getRequestString("txtCpNumber"));
		staff.setEmailAddress(getRequestString("txtEmailAddress"));
		staff.setProgramGraduated(getRequestString("txtProgramGraduated"));
		
	    staff.setJobRole(getRequestString("txtJobRole"));
	    staff.setAssignedOffice(getRequestString("txtAssignedOffice"));
		staff.setAcademicProgramCodes(getRequestString("txtAcademicProgramCodes"));
		String academicProgramCodes = "";
		for(int i=0; i<academicProgramGroupList.size(); i++) {
			AcademicProgramGroupDTO academicProgramGroup = (AcademicProgramGroupDTO)academicProgramGroupList.get(i);
			String selectedAcademicProgramCodes = getSelectedCheckBox(AcademicProgramUtil.getAcademicProgramListByAcademicProgramGroupCode(academicProgramList, academicProgramGroup.getCode()), "AcademicProgram"+academicProgramGroup.getCode());
			if(selectedAcademicProgramCodes.length() >=1 && academicProgramCodes.length() >= 1) {
				academicProgramCodes += "~";
			}
			academicProgramCodes += selectedAcademicProgramCodes;
		}
		staff.setAcademicProgramCodes(academicProgramCodes);
	}


	protected void validateInput() {
		if (!sessionInfo.isCurrentLinkDeleteSubmit()) {
			StaffDTO staff = (StaffDTO) getSessionAttribute(StaffDTO.SESSION_STAFF);

				if (StringUtil.isEmpty(staff.getLastName())) {
			      actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Last Name");
			    }
			    else if (StringUtil.isEmpty(staff.getFirstName())) {
			    	actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "First Name");
			    }
			    else if (StringUtil.isEmpty(staff.getMiddleName())) {
			    	actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Middle Name");
			    }
			    else if(!staff.getPrefixName().isEmpty()) {
					if(staff.getGender().equalsIgnoreCase(UserDTO.GENDER_FEMALE)) {
						if(!staff.isGenderFemaleByPrefixName(staff.getPrefixName())) {
							if(staff.isGenderMaleByPrefixName(staff.getPrefixName())) {
								actionResponse.constructMessage(ActionResponse.TYPE_MISMATCH, new String[]{staff.getPrefixName(), staff.getGender()});
							}
						}
					}
					else if(staff.getGender().equalsIgnoreCase(UserDTO.GENDER_MALE)) {
						if(!staff.isGenderMaleByPrefixName(staff.getPrefixName())) {
							if(staff.isGenderFemaleByPrefixName(staff.getPrefixName())) {
								actionResponse.constructMessage(ActionResponse.TYPE_MISMATCH, new String[]{staff.getPrefixName(), staff.getGender()});
							}
						}
					}
				}
			    else if (StringUtil.isEmpty(staff.getStreetPermanent())) {
			    	actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Permanent Address - Street");
			    }
			    else if (StringUtil.isEmpty(staff.getBarangayPermanent())) {
			    	actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Permanent Address - Barangay");
			    }
			    else if (StringUtil.isEmpty(staff.getStreetPresent())) {
			    	actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Present Address - Street");
			    }
			    else if (StringUtil.isEmpty(staff.getBarangayPresent())) {
			    	actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Present Address - Barangay");
			    }
			    else if (!StringUtil.isEmpty(staff.getCpNumber()) && !StringUtil.isValidCPNumber(staff.getCpNumber())) {
						actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Cellphone Number.");
				}
			    else if(DateTimeUtil.getNumberOfMonths(staff.getBirthDate(), DateTimeUtil.getCurrentTimestamp()) < 216){
					actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Birth Date");
				}
				else if(!WebUtil.isValidEmail(staff.getEmailAddress())) {
					actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Email");
				}
			    else if (StringUtil.isEmpty(staff.getJobRole())) {
			    	actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Job Role");
			    }
			    else if (StringUtil.isEmpty(staff.getAssignedOffice())) {
			    	actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Assigned Office");
			    }
			    else if (StringUtil.isEmpty(staff.getAcademicProgramCodes())) {
			    	actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Academic Program");
			    }else {
					List<DTOBase> userList = (List<DTOBase>) new UserDAO().getUserListByLastNameFirstNameMiddleNameUserGroupCode(staff.getLastName(), staff.getFirstName(), staff.getMiddleName(), UserGroupDTO.USER_GROUP_STAFF_CODE);
					if(userList.size() > 0){
						UserDTO userNameExist = new UserDAO().getUserByCode(userList.get(0).getCode());
						if(sessionInfo.isPreviousLinkAdd()) {
								actionResponse.constructMessage(ActionResponse.TYPE_INFO, "Name: " + userNameExist.getName(false, true, false) + " is already existing in the system" + " with a birthday of " + userNameExist.getBirthDate());	
						}else if(sessionInfo.isPreviousLinkUpdate()) {
							StaffDTO staffOrig = (StaffDTO) getSessionAttribute(StaffDTO.SESSION_STAFF + "_ORIG");
							if(!staffOrig.getName(false, true, false).equalsIgnoreCase(staff.getName(false, true, false))) {
								actionResponse.constructMessage(ActionResponse.TYPE_INFO, "Name: " + userNameExist.getName(false, true, false) + " is already existing in the system" + " with a birthday of " + userNameExist.getBirthDate());
						}
					}
				}
			}
		}
	}
}
