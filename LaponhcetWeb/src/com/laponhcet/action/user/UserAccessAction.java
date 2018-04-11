package com.laponhcet.action.user;

import java.util.ArrayList;
import java.util.List;

import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.LinkDAO;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.dto.UserGroupDTO;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.DateTimeUtil;

public class UserAccessAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void setInput() {
		UserDTO user = (UserDTO) getSessionAttribute(UserDTO.SESSION_USER + "_ACCESS");		
		List<DTOBase> userGroupList = (List<DTOBase>) getSessionAttribute(UserGroupDTO.SESSION_USER_GROUP_LIST);
		user.setUserName(getRequestString("txtUserName"));
		user.setPassword(getRequestString("txtPassword"));
		user.setUserGroupCodes(((UserGroupDTO) DTOUtil.getObjById(userGroupList, getRequestInt("cboUserGroup"))).getCode());
		// Temporary Birthday to avoid error
		user.setBirthDate(DateTimeUtil.getStrToDateTime("1970-01-01", "yyyy-MM-dd"));
		// New User Link List
		String checkedLinks = getRequestString("txtCheckedLinks");
		if(!checkedLinks.isEmpty()){
			String[] links = checkedLinks.split("~");
			List<DTOBase> newListLink = new ArrayList<DTOBase>();
			for (int i=0; i < links.length ; i++){
				newListLink.add(new LinkDAO().getLinkByCode(links[i]));
			}
			user.setUserLinkList(newListLink);
		}
	}
	
	protected void validateInput() {
		UserDTO user = (UserDTO) getSessionAttribute(UserDTO.SESSION_USER + "_ACCESS");
		UserDTO userExisting = new UserDAO().getUserByUserNamePassword(user.getUserName(), user.getPassword());
		if(user.getUserName().isEmpty()){
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Username");
		}else if(user.getPassword().isEmpty()){
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Password");
		}else if(userExisting!=null){
			if(!user.getCode().equalsIgnoreCase(userExisting.getCode())){
				actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Username and Password");
			}
		}else if(user.getUserLinkList().isEmpty()){
			actionResponse.constructMessage(ActionResponse.TYPE_INFO, "User's Link List is Empty!");
		}
	}
}
