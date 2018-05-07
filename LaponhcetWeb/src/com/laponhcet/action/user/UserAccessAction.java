package com.laponhcet.action.user;

import java.util.ArrayList;
import java.util.List;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.LinkDTO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.dto.UserGroupDTO;

public class UserAccessAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setInput() {
		UserDTO user = (UserDTO) getSessionAttribute(UserDTO.SESSION_USER + "_ACCESS");		
		List<DTOBase> userGroupList = (List<DTOBase>) getSessionAttribute(UserGroupDTO.SESSION_USER_GROUP_LIST);
		user.setUserName(getRequestString("txtUserName"));
		user.setPassword(getRequestString("txtPassword"));
		user.setActive(getRequestString("rbStatus").equalsIgnoreCase("Active")?true:false);
		user.setUserGroupCodes(getSelectedCheckBox(userGroupList, "UserGroup"));

		String[] userLinkArr = request.getParameterValues("chkUserLink");
		List<DTOBase> linkList = new ArrayList<DTOBase>();
		
		if(userLinkArr != null) {
			//add link group list to newUserLinkList 
			for(int i=0; i<userLinkArr.length; i++) {
				LinkDTO currentLink = sessionInfo.getLinkByCode(userLinkArr[i]);
				for(DTOBase linkDTO: sessionInfo.getLinkGroupList(currentLink.getLinkGroup())) {
				    LinkDTO link = (LinkDTO) linkDTO;
					linkList.add(link);
				}
			}
			
			//get parent menu link list
			List<LinkDTO> parentMenuLinkList = new ArrayList<LinkDTO>();
			for(DTOBase linkDTO: linkList) {
			    LinkDTO userLink = (LinkDTO) linkDTO;
				for(LinkDTO parentMenuLink: sessionInfo.getParentMainLinkListByLinkGroup(userLink.getLinkGroup())) {
					parentMenuLinkList.add(parentMenuLink);
				}
			}
			
			//add parent menu link to new user link list
			for(LinkDTO parentMenuLink: parentMenuLinkList) {
				boolean isFound = false;
				for(DTOBase linkDTO: linkList) {
				    LinkDTO newUserLink = (LinkDTO) linkDTO;
					if(parentMenuLink.getId() == newUserLink.getId()) {
						isFound = true;
						break;
					}
				}
				if(!isFound) {
					linkList.add(parentMenuLink); 
					//add link group for parent link not equal to main link
					if(!parentMenuLink.getLinkGroup().endsWith("0")) {
						for(LinkDTO link: sessionInfo.getLinkGroupList(parentMenuLink.getLinkGroup())) {
							if(link.getId()!=parentMenuLink.getId()) {
								linkList.add(link);
							}
						}
					}
				}
			}
		}
		user.setUserLinkList(linkList);
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
