package com.laponhcet.util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dao.StudentDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.StudentDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.MediaDAO;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.MediaDTO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;

public class StudentUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/*public static void setPaginationRecord(SessionInfo sessionInfo, Pagination pagination, List<DTOBase> academicProgramList) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			StudentDTO student = (StudentDTO) dto;
			UserDTO user = new UserDAO().getUserByCode(student.getCode());
			//AcademicProgramDTO academicProgram = (AcademicProgramDTO) DTOUtil.getObjByCode(academicProgramList, student.getAcademicProgram().getCode());
			String profilePict = StringUtil.isEmpty(user.getProfilePict())?UserDTO.PROFILE_PICT_DEFAULT:user.getProfilePict();
			student.setPaginationRecord(new String[]{"<img width='200px' height='200px' src='" + profilePict + "'>", student.getAcademicProgram().getCode(), user.getCode(), user.getLastName(), user.getFirstName(), user.getMiddleName(), pagination.getLinkButtonStr(sessionInfo, student.getId()).replace("~", ",")});
		}
	}*/
	
	public static String getRecordGrid(StudentDTO student) {
		StringBuffer strBuff = new StringBuffer();	
		strBuff.append("<div class='ibox'>");
		strBuff.append("	<div class='ibox-content product-box'>");
		strBuff.append("		<div class='product-imitation' style=\"background-image: url('" + student.getProfilePict().getPict() + "');background-size: 100% auto;\"></div>");
		strBuff.append("		<div class='product-desc'>");
		strBuff.append("			<span class='product-price'>" + student.getAcademicProgram().getCode() + "</span>");
		strBuff.append("			<a href='#' class='product-name'>" + student.getLastName());
		strBuff.append("				<font class='product-name-sub'>" + student.getFirstName() + ' ' + student.getMiddleName() + "</font>");
		strBuff.append("			</a>");
		strBuff.append("			<div class='m-t-xs'>");
		strBuff.append("				<i class='fa fa-id-card-o'></i>&nbsp;&nbsp;&nbsp;" + student.getCode() + "<br />");
		strBuff.append("				<i class='fa fa-calendar'></i>&nbsp;&nbsp;&nbsp;&nbsp;" + DateTimeUtil.getDateTimeToStr(student.getBirthDate(), "MMMM dd, YYYY") + "\r\n");
		strBuff.append("			</div>");
		strBuff.append("			<div class='m-t text-center'>");
		strBuff.append("				<a href='#' onclick=\"recordAction('" + student.getId() + "', 'US0095')\" class='btn btn-xs btn-primary'>View <i class='fa fa-long-arrow-right'></i></a>");
		strBuff.append("				<a href='#' onclick=\"recordAction('" + student.getId() + "', 'US0062')\" class='btn btn-xs btn-success m-l-xs m-r-xs'>Update <i class='fa fa-pencil'></i></a>");
		strBuff.append("				<a href='#' onclick=\"recordAction('" + student.getId() + "', 'US0065')\" class='btn btn-xs btn-danger'>Delete <i class='fa fa-trash'></i></a>");
		strBuff.append("			</div>");
		strBuff.append("		</div>");
		strBuff.append("	</div>");
		strBuff.append("</div>");
		return strBuff.toString();
	}
	
	public static void setPaginationRecord(SessionInfo sessionInfo, Pagination pagination, List<DTOBase> academicProgramList, boolean isList) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			StudentDTO student = (StudentDTO) dto;
			UserDTO user = new UserDAO().getUserByCode(student.getCode());
			student = StudentUtil.setStudent(student, user, academicProgramList);
			if(isList) {
				student.setPaginationRecord(new String[] {student.getCode(), student.getLastName(), student.getFirstName(), student.getMiddleName(), student.getAcademicProgram().getDisplayText()});
			}
			else {
				student.setPaginationRecord(new String[]{getRecordGrid(student)});
			}
		}
	}
	
	public static List<DTOBase> getStudentListByUserList(Pagination pagination, List<DTOBase> userList, List<DTOBase> academicProgramList){
		List<DTOBase> resultList = new ArrayList<DTOBase>();
		for(DTOBase userObj : userList){
			UserDTO user = (UserDTO) userObj;
			StudentDTO student = setStudent((StudentDTO) DTOUtil.getObjByCode(pagination.getRecordListUnfiltered(), user.getCode()), user, academicProgramList);
			resultList.add(student);
		}
		return resultList;
	}
	
	public static StudentDTO setStudent(StudentDTO student, UserDTO user, List<DTOBase> academicProgramList) {	
		MediaDTO profilePict = new MediaDAO().getMediaByCodeTableField(user.getCode(), student.getTableName(), "PROFILE_PICT");
		student.setProfilePict(profilePict == null?user.getProfilePict():profilePict);
		student.setRfid(user.getRfid());
		student.setFacebookId(user.getFacebookId());
		student.setAcademicProgram((AcademicProgramDTO) DTOUtil.getObjByCode(academicProgramList, student.getAcademicProgram().getCode()));
		student.setCpNumber(user.getCpNumber());
		student.setLandlineNumber(user.getLandlineNumber());
		student.setEmailAddress(user.getEmailAddress());
		student.setPrefixName(user.getPrefixName());
		student.setLastName(user.getLastName());
		student.setFirstName(user.getFirstName());
		student.setMiddleName(user.getMiddleName());
		student.setSuffixName(user.getSuffixName());
		student.setStreetPermanent(user.getStreetPermanent());
		student.setBarangayPermanent(user.getBarangayPermanent());
		student.setCityPermanent(user.getCityPermanent());
		student.setStreetPresent(user.getStreetPresent());
		student.setBarangayPresent(user.getBarangayPresent());
		student.setCityPresent(user.getCityPresent());
		student.setBirthPlace(user.getBirthPlace());
		student.setBirthDate(user.getBirthDate());
		student.setGender(user.getGender());
		student.setReligion(user.getReligion());
		student.setMaritalStatus(user.getMaritalStatus());
		student.setCitizenship(user.getCitizenship());
		student.setPassportNumber(user.getPassportNumber());
		student.setFatherName(user.getFatherName());
		student.setFatherOccupation(user.getFatherOccupation());
		student.setFatherCpNumber(user.getFatherCpNumber());
		student.setMotherName(user.getMotherName());
		student.setMotherOccupation(user.getOccupation());
		student.setMotherCpNumber(user.getMotherCpNumber());
		student.setGuardianName(user.getGuardianName());
		student.setGuardianOccupation(user.getGuardianOccupation());
		student.setGuardianRelation(user.getGuardianRelation());
		student.setContactPerson(user.getContactPerson());
		student.setContactRelation(user.getContactRelation());
		student.setContactAddress(user.getContactAddress());
		student.setContactCPNumber(user.getContactCPNumber());
		student.setContactLandlineNumber(user.getContactLandlineNumber());
		student.setContactEmailAddress(user.getContactEmailAddress());
		student.setContactFacebookId(user.getContactRelation());
		return student;
	}
	
	/*
	
	public static List<DTOBase> getStudentListByCodeName(List<DTOBase> userListByCodeName, List<DTOBase> studentList){
		List<DTOBase> newStudentList = new ArrayList<DTOBase>();
		for(DTOBase obj : userListByCodeName){
			UserDTO user = (UserDTO) obj;
			StudentDTO student = (StudentDTO) DTOUtil.getObjByCode(studentList, user.getCode());
			newStudentList.add(setStudent(student, user));
		}
		return newStudentList;
	}
	
	public static List<DTOBase> getStudentListByAcademicProgram(List<DTOBase> userList, List<DTOBase> studentListByAcademicProgramCode){
		List<DTOBase> newStudentList = new ArrayList<DTOBase>();
		for(DTOBase userObj : studentListByAcademicProgramCode){
			StudentDTO student = (StudentDTO) userObj;
			UserDTO user = (UserDTO) DTOUtil.getObjByCode(userList, student.getCode());
			if(user!=null && !StringUtil.isEmpty(user.getCpNumber())){
				newStudentList.add(setStudent(student, user));
			}
		}
		return newStudentList;
	}
	
	public static List<DTOBase> getStudentListByAcademicProgram(List<DTOBase> studentList, String academicProgramCode){
		List<DTOBase> newStudentList = new ArrayList<DTOBase>();
		for(DTOBase studentObj : studentList){
			StudentDTO student = (StudentDTO) studentObj;
			if(student.getAcademicProgram().getCode().equalsIgnoreCase(academicProgramCode)){
				newStudentList.add(student);
			}
		}
		return newStudentList;
	}
	
	
	
	public static StudentDTO getStudent(DTOBase obj){
		StudentDTO student = (StudentDTO) obj;
		UserDTO user = new UserDAO().getUserByCode(student.getCode());
		return setStudent(student, user);
	}
	
	public static String getProfilePicture(StudentDTO student){
		String pict = !StringUtil.isEmpty(student.getProfilePict())?student.getProfilePict():" ";
		return "<img src='" + pict + "' class='thumbnail' width='70' height='70' />";
	}
	
	*/
}
