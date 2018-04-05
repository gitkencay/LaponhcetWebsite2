package com.laponhcet.util;

import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.StaffDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.dto.UserGroupDTO;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.StringUtil;
import com.mytechnopal.util.UserUtil;
import com.mytechnopal.util.WebUtil;
import com.mytechnopal.webcontrol.CheckBoxWebControl;

public class StaffUtil extends UserUtil {
	private static final long serialVersionUID = 1L;
	
	public static StaffDTO setStaff(StaffDTO staff, UserDTO user) {
		staff.setRfid(user.getRfid());
		staff.setFacebookId(user.getFacebookId());
		staff.setUserName(user.getUserName());
		staff.setPassword(user.getPassword());
		staff.setUserGroup(user.getUserGroup());
		staff.setLastName(user.getLastName());
		staff.setFirstName(user.getFirstName());
		staff.setMiddleName(user.getMiddleName());
		staff.setPrefixName(user.getPrefixName());
		staff.setSuffixName(user.getSuffixName());
		staff.setOtherTitle(user.getOtherTitle());
		staff.setGender(user.getGender());
		staff.setStreetPermanent(user.getStreetPermanent());
		staff.setBarangayPermanent(user.getBarangayPermanent());
		staff.setCityPermanent(user.getCityPermanent());
		staff.setStreetPresent(user.getStreetPresent());
		staff.setBarangayPresent(user.getBarangayPresent());
		staff.setCityPresent(user.getCityPresent());
		staff.setBirthPlace(user.getBirthPlace());
		staff.setBirthDate(user.getBirthDate());
		staff.setReligion(user.getReligion());
		staff.setMaritalStatus(user.getMaritalStatus());
		staff.setCitizenship(user.getCitizenship());
		staff.setPassportNumber(user.getPassportNumber());
		staff.setOccupation(user.getOccupation());
		staff.setCpNumber(user.getCpNumber());
		staff.setLandlineNumber(user.getLandlineNumber());
		staff.setEmailAddress(user.getEmailAddress());
		staff.setFatherName(user.getFatherName());
		staff.setFatherOccupation(user.getFatherOccupation());
		staff.setFatherCpNumber(user.getFatherCpNumber());
		staff.setMotherName(user.getMotherName());
		staff.setMotherOccupation(user.getMotherOccupation());
		staff.setMotherCpNumber(user.getMotherCpNumber());
		staff.setGuardianName(user.getGuardianName());
		staff.setGuardianOccupation(user.getGuardianOccupation());
		staff.setGuardianRelation(user.getGuardianRelation());
		staff.setContactPerson(user.getContactPerson());
		staff.setContactRelation(user.getContactRelation());
		staff.setContactAddress(user.getContactAddress());
		staff.setContactCPNumber(user.getContactCPNumber());
		staff.setContactLandlineNumber(user.getContactLandlineNumber());
		staff.setContactEmailAddress(user.getContactEmailAddress());
		staff.setContactFacebookId(user.getContactFacebookId());
		staff.setSourceDeviceInfo(user.getSourceDeviceInfo());
		staff.setActive(user.isActive());
		staff.setHtmlSkin(user.getHtmlSkin());
		staff.setLastLoginTimestamp(user.getLastLoginTimestamp());
		staff.setLastLoginIPAddress(user.getLastLoginIPAddress());
		staff.setProfilePict(user.getProfilePict());
		staff.setDisplayText(staff.getName(false, false, true));
		return staff;
	}
	
	//user obj converted to staff obj
	public static StaffDTO getStaff(DTOBase userObj) {
		StaffDTO staff= (StaffDTO) userObj;
		UserDTO user = new UserDAO().getUserByCode(staff.getCode());
		setStaff(staff, user);
		return staff;
	}
	
	
	public static List<DTOBase> getStaffList(List<DTOBase> staffList){
		List<DTOBase> newStaffList = new ArrayList<DTOBase>();
		List<DTOBase> userList = new UserDAO().getUserListByUserGroupCode(UserGroupDTO.USER_GROUP_STAFF_CODE);
		for(DTOBase obj : staffList){
			StaffDTO staff = (StaffDTO) obj;
			UserDTO user = (UserDTO) DTOUtil.getObjByCode(userList, staff.getCode());
			newStaffList.add(setStaff(staff, user));
		}
		return newStaffList;
	}
	
	public static List<DTOBase> getStaffListByCodename( List<DTOBase> UserListByCodename, List<DTOBase> staffList){
		List<DTOBase> newStaffList = new ArrayList<DTOBase>();
		for(DTOBase obj : UserListByCodename){
			UserDTO user = (UserDTO) obj;
			StaffDTO staff =  (StaffDTO) DTOUtil.getObjByCode(staffList, user.getCode());
			newStaffList.add(setStaff(staff, user));
		}
		return newStaffList;
	}
	
	

	public static List<DTOBase> getStaffListByNameCode(List<DTOBase> userList, List<DTOBase> staffList,
			List<DTOBase> cityList, List<DTOBase> religionList) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static List<DTOBase> getArrNameProgramGroupList(String code) {
		String[] codes = code.split("~");
		List<DTOBase> newListLink = new ArrayList<DTOBase>();
		for(int i=0; i < codes.length ; i++){
			newListLink.add(new AcademicProgramDAO().getAcademicProgramByName(codes[i]));
		}
		return newListLink;
	}
	
	public static String getNameList(List<DTOBase> list) {
		String name = "";
		for(DTOBase obj: list) {
			AcademicProgramDTO ap = (AcademicProgramDTO) obj;
				name += " | " + ap.getName();
		}
	return name.substring(3);
	}
	
	
	public static void setPaginationRecord(SessionInfo sessionInfo, Pagination pagination) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			StaffDTO staff = (StaffDTO) dto;
			UserDTO user = new UserDAO().getUserByCode(staff.getCode());
			staff.setPaginationRecord(new String []{"<img class = 'img-thumbnail img-md' src='" + staff.getProfilePict() + "'>", staff.getCode(), user.getName(true, true, true), staff.getJobRole(), staff.getAssignedOffice(), pagination.getLinkButtonStr(sessionInfo, staff.getId()).replace("~", ",")});
		}
	}
	
	public static String getPaneledCheckbox(SessionInfo sessionInfo, String gridClass, String panelName, String panelClass, Boolean isHeaderCheckbox, String panelHeaderLabel, List<DTOBase> ObjList, String strArrCodes,  int tableColCount, String webSettingForPanelBody){
		StringBuffer str = new StringBuffer();
		str.append("<div id=\"div" + panelName + "\">");
		str.append("<div class=\"" + gridClass  + "\">");
		str.append("<div class=\"" + panelClass + "\">");
		str.append("<div class=\"panel-heading\">");
		if(isHeaderCheckbox && sessionInfo.isCurrentLinkDataEntry()){
			str.append("<input type=\"checkbox\" id=\"chk" + panelName + "\" onchange=\"toggleCheckListByPrefixId(this)\">");
		}
		str.append("&nbsp;&nbsp;" + panelHeaderLabel + " </div>");
	    str.append("<div class=\"panel-body\" " + webSettingForPanelBody  + " >");
		str.append(WebUtil.getTable("", new CheckBoxWebControl().getCheckBoxWebControlArr(sessionInfo, panelName, ObjList, strArrCodes.split("~"), StringUtil.getStrArr(ObjList), "onchange=\"toggleCheckParent('chk" + panelName + "', " + ObjList.size() + ")\""), tableColCount));
	    str.append("</div></div></div></div>");  
	    return str.toString();
	}
	
	public static String getPaneledCheckbox(SessionInfo sessionInfo, String gridClass, String panelName, String panelClass, Boolean isHeaderCheckbox, String panelHeaderLabel, List<DTOBase> childObjList, String selectedValue , String strArrCodes,  int tableColCount, String webSettingForPanelBody){
		StringBuffer str = new StringBuffer();
		str.append("<div id=\"div" + panelName + "CheckList\">");
		str.append("<div class=\"" + gridClass  + "\">");
		str.append("<div class=\"" + panelClass + "  m-t\">");
		str.append("<div class=\"panel-heading\">");
		if(isHeaderCheckbox){
			str.append("<input type=\"checkbox\" id=\"chk" + panelName + "\" onchange=\"toggleCheckListByPrefixId(this)\">");
		}
		str.append("&nbsp;&nbsp;" +panelHeaderLabel + " </div>");
	    str.append("<div class=\"panel-body\" " + webSettingForPanelBody  + " >");
		str.append(WebUtil.getTable("", new CheckBoxWebControl().getCheckBoxWebControlArr(sessionInfo, panelName + selectedValue, childObjList, strArrCodes.split("~"), StringUtil.getStrArr(childObjList), "onchange=\"toggleCheckParent('chk" + panelName + selectedValue + "', " + childObjList.size() + ")\""), tableColCount));
	    str.append("</div></div></div></div>");  
	    return str.toString();
	}
	
	public static String getTableWithLabel(SessionInfo sessionInfo, String label, String name, boolean isImportant, List<DTOBase> ObjList, String strArrCodes, String  tableClass, int columnCount, String webControlStr){
		StringBuffer str = new StringBuffer();
		str.append("<label class=\"m-t m-b-xs\">" + label + (isImportant?" <font color=\"red\">*</font>":" ") +  "</label>\n");
		str.append("<div class=\"" + webControlStr + "\">" + WebUtil.getTable(tableClass, new CheckBoxWebControl().getCheckBoxWebControlArr(sessionInfo, name, ObjList, strArrCodes.split("~"), StringUtil.getStrArr(ObjList), "onchange=\"toggleCheckParent('chk" + name + "', " + ObjList.size() + ")\""), columnCount) + "</div>");
		return str.toString();
	}
}
