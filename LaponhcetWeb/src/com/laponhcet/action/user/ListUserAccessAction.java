package com.laponhcet.action.user;

import java.util.List;

import com.laponhcet.util.UserAccessUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dao.UserGroupDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.dto.UserGroupDTO;

public class ListUserAccessAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"UA0007", "", "UA0004"}, new String[] {"", "UA0005", ""}, new String[] {"", "UA0006", ""}, "UA0002", "UA0003");
		if((!sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit())) {
			Pagination pagination = new Pagination();
			List<DTOBase> userList = new UserDAO().getUserList();
			pagination.setName(UserDTO.SESSION_USER_PAGINATION);
			pagination.setSearchCriteria(UserAccessUtil.PAGINATION_SEARCH_CRITERIA_LIST[0]);
			pagination.setColumnNameList(new String[] {"ID", "Last Name","First Name", "Middle Name", "Group", ""});
			pagination.setColumnWidthList(new String[] {"10","23", "21", "21", "15","10"});	
			pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
			pagination.setRecordListUnfiltered(userList);
			pagination.setRecordList(userList);
			pagination.setAjaxResultDetailsList(new String[] {"id", "lastName", "firstName", "middleName", "group", "button"});
			setPaginationRecord(pagination, sessionInfo);
			setSessionAttribute(UserDTO.SESSION_USER_PAGINATION, pagination);
			setSessionAttribute(UserDTO.SESSION_USER + "_ACCESS", new UserDTO());
		}
	}
	
	private void setPaginationRecord(Pagination pagination, SessionInfo sessionInfo) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			UserDTO user = (UserDTO) dto;
			UserGroupDTO userGroupDTO = new UserGroupDAO().getUserGroupByCode(user.getUserGroupCodes());
			user.setPaginationRecord(new String[]{user.getCode(), user.getLastName(), user.getFirstName(), user.getMiddleName(),  userGroupDTO.getName() , UserAccessUtil.getRecordButtonStr(sessionInfo, user)});
		}
	}		
}
