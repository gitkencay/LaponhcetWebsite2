package com.laponhcet.action.user;

import java.util.List;

import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserGroupDAO;
import com.mytechnopal.dao.UserLinkDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.DTOUtil;

public class UpdateUserAccessAction extends ActionBase {

	private static final long serialVersionUID = 1L;

	
	protected void setSessionVars() {
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			Pagination pagination = (Pagination) getSessionAttribute(UserDTO.SESSION_USER_PAGINATION);
			DTOBase obj = DTOUtil.getObjById(pagination.getCurrentPageRecordList(), getRequestInt("txtSelectedRecord"));
			UserDTO user = (UserDTO) obj;
			user.setUserLinkList(new UserLinkDAO().getUserLinkListByUserCode(user.getCode()));
			List<DTOBase> userGroupList = new UserGroupDAO().getUserGroupList(true);
			setSessionAttribute(UserDTO.SESSION_USER + "_ORIG", user);
			setSessionBeforeTrans(UserDTO.SESSION_USER + "_ACCESS", user.getUser());
		}
	}
}
