package com.laponhcet.util;

import java.io.Serializable;
import java.util.List;
import com.laponhcet.dto.RegisterDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.MediaDAO;
import com.mytechnopal.dto.MediaDTO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.UserUtil;


public class RegisterUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static void setPaginationRecord(SessionInfo sessionInfo, Pagination pagination) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			RegisterDTO register = (RegisterDTO) dto;
			MediaDTO profilePict = new MediaDAO().getMediaByCodeTableField(register.getCode(), register.getTableName(), "PROFILE_PICT");
			register.setProfilePict(profilePict == null?register.getProfilePict():profilePict);
			register.setPaginationRecord(new String[]{register.getLastName(), register.getFirstName(), register.getMiddleName(), RegisterUtil.getRegisterStatus(register), pagination.getLinkButtonStr(sessionInfo, register.getId()).replace("~", ",")});
		}
	}		
	
	public static void setRegisterList(List<DTOBase> registerList) {
		for(DTOBase registerObj: registerList) {
		    RegisterDTO register = (RegisterDTO)registerObj;
		    setRegister(register, UserUtil.getUserByUserCode(register.getCode()));
		}
	}
	
	public static void setRegister(RegisterDTO register, UserDTO user) {
		MediaDTO profilePict = new MediaDAO().getMediaByCodeTableField(user.getCode(), register.getTableName(), "PROFILE_PICT");
		register.setProfilePict(profilePict == null?user.getProfilePict():profilePict);
		register.setUserGroupCodes(user.getUserGroupCodes());
		register.setLastName(user.getLastName());
		register.setFirstName(user.getFirstName());
		register.setMiddleName(user.getMiddleName());
		register.setPrefixName(user.getPrefixName());
		register.setSuffixName(user.getSuffixName());
		register.setBirthDate(user.getBirthDate());
		register.setGender(user.getGender());
		register.setCpNumber(user.getCpNumber());
		register.setEmailAddress(user.getEmailAddress());
		register.setStatus(user.getStatus());
		register.setPassword(user.getPassword());
		register.setDisplayText(register.getName(false, false, true));
	}
	
	public static UserDTO getUserByRegister(RegisterDTO register){
		UserDTO user = new UserDTO();
		user.setUserGroupCodes(register.getUserGroupCodes());
		user.setLastName(register.getLastName());
		user.setFirstName(register.getFirstName());
		user.setMiddleName(register.getMiddleName());
		user.setPrefixName(register.getPrefixName());
		user.setSuffixName(register.getSuffixName());
		user.setBirthDate(register.getBirthDate());
		user.setGender(register.getGender());
		user.setOccupation(register.getOccupation());
		user.setCpNumber(register.getCpNumber());
		user.setEmailAddress(register.getEmailAddress());
		user.setUserName(register.getUserName());
		user.setPassword(register.getPassword());	
		user.setAddedBy(register.getAddedBy());
		user.setAddedTimestamp(register.getAddedTimestamp());
		user.setUpdatedBy(register.getUpdatedBy());
		user.setUpdatedTimestamp(register.getUpdatedTimestamp());		
		return user;
	}
	
	public static String getRecordButtonStr(SessionInfo sessionInfo, RegisterDTO register){
		StringBuffer str = new StringBuffer();
		str.append("<button class='fa fa-pencil fa-sm btn-rounded btn-outline btn btn-success' title='Update' onclick=\"recordAction('" + register.getId() + "','" +sessionInfo.getUpdateLink().getCode() +  "')\"></button>");
		str.append("<button class='fa fa-trash btn-rounded btn-outline btn btn-danger m-l-xs' title='Delete' onclick=\"recordAction('" + register.getId() + "','" + sessionInfo.getDeleteSubmitLink().getCode()  +  "')\"></button>");
		if(!register.getStatus().equalsIgnoreCase(RegisterDTO.REGISTER_STATUS_APPROVED) && !register.getStatus().equalsIgnoreCase(RegisterDTO.REGISTER_STATUS_APPLIED) && !register.getStatus().equalsIgnoreCase(RegisterDTO.REGISTER_STATUS_REJECTED)){
			str.append("<button class='fa fa-check btn-rounded btn-outline btn btn-info m-r-xs' title='Approve' onclick=\"updateRegisterStatus('" + register.getId() + "','" + RegisterDTO.REGISTER_STATUS_APPROVED +"')\"></button>");
			str.append("<button class='fa fa-times btn-rounded btn-outline btn btn-info' title='Reject' onclick=\"updateRegisterStatus('" + register.getId() + "','" + RegisterDTO.REGISTER_STATUS_REJECTED +"')\"></button>");
		}
		str.append("<input type='hidden' id='txtRegisterStatus' name='txtRegisterStatus'>");
		str.append("<script>");
		str.append("function updateRegisterStatus(id, status){");
		str.append("	document.getElementById('txtRegisterStatus').value = status;");
		str.append("	recordAction(id, 'UE0012');");
		str.append("}");
		str.append("</script>");
		return str.toString();
	}
	
	public static String getRegisterStatus(RegisterDTO register){
		String registerStatusStr = "";
		if(register.getStatus().equalsIgnoreCase(RegisterDTO.REGISTER_STATUS_APPROVED)){
			registerStatusStr = "<span class='label label-success'>" + register.getStatus() + "</span>";
		}else if(register.getStatus().equalsIgnoreCase(RegisterDTO.REGISTER_STATUS_FOR_APPROVAL)){
			registerStatusStr = "<span class='label label-warning'>" + register.getStatus() + "</span>";
		}else if(register.getStatus().equalsIgnoreCase(RegisterDTO.REGISTER_STATUS_APPLIED)){
			registerStatusStr = "<span class='label label-default'>" + register.getStatus() + "</span>";
		}else if(register.getStatus().equalsIgnoreCase(RegisterDTO.REGISTER_STATUS_REJECTED)){
			registerStatusStr = "<span class='label label-danger'>" + register.getStatus() + "</span>";
		}
		return registerStatusStr;
	}
}
