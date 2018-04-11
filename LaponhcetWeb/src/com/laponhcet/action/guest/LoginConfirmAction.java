package com.laponhcet.action.guest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import com.laponhcet.dao.AcademicYearDAO;
import com.laponhcet.dao.SemesterDAO;
import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.dto.SemesterDTO;
import com.laponhcet.util.SemesterUtil;
import com.laponhcet.util.SettingsUtil;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.base.SettingsBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dao.UserLogDAO;
import com.mytechnopal.dto.LinkDTO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;

public class LoginConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void executeLogic() {
		UserDTO user = (UserDTO) getSessionAttribute(UserDTO.SESSION_USER);
		resetSessionInfo(new LinkDTO(), sessionInfo.getUserByCode(user.getCode()), new LinkDTO());
		
		LinkDTO link = null;
		LinkDTO homeLink = null;
		link = sessionInfo.getLinkByCode(SettingsBase.USER_HOME_LINK_CODE);
		homeLink = sessionInfo.getLinkByCode(link.getCode());
		sessionInfo.setCurrentLink(link);
		sessionInfo.setHomeLink(homeLink);	
		
		if(SettingsUtil.OWNER_CODE.equalsIgnoreCase(SettingsUtil.OWNER_CODE_BCC) || SettingsUtil.OWNER_CODE.equalsIgnoreCase(SettingsUtil.OWNER_CODE_FBC)) {
			List<DTOBase> academicYearList = new AcademicYearDAO().getAcademicYearList();
			List<DTOBase> semesterList = new SemesterDAO().getSemesterList();
			setSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_LIST, academicYearList);
			setSessionAttribute(SemesterDTO.SESSION_SEMESTER_LIST, SemesterUtil.getSemesterListByAcademicYear(semesterList, academicYearList));
		}
	}

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
	
	protected void validateInput() {
		String userName = request.getParameter("txtUsernName");
		String password = request.getParameter("txtPassword");
		
		UserDTO user = new UserDTO();
		user.setUserName(userName);
		user.setPassword(password);
		setSessionAttribute(UserDTO.SESSION_USER, user);
		
		if(StringUtil.isEmpty(userName)) {
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, new String[]{"User Name"});
		}
		else if(StringUtil.isEmpty(password)) {
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, new String[]{"Password"});
		}
		else {
			UserDAO userDAO = new UserDAO();
			user = userDAO.getUserByUserNamePassword(userName, password);
			if(user == null) {
				actionResponse.constructMessage(ActionResponse.TYPE_FAIL, new String[]{"Wrong Combination of user name and password."});
			}
			else {
				if(user.isActive()) {
					user.setAddedTimestamp(DateTimeUtil.getCurrentTimestamp());
					try {
						user.setSourceDeviceInfo(InetAddress.getLocalHost().toString());
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					new UserLogDAO().executeAdd(user);
				}
				else {
					actionResponse.constructMessage(ActionResponse.TYPE_FAIL, new String[]{"Your profile is already inactive.  Please contact the administrator"});
				}
				setSessionAttribute(UserDTO.SESSION_USER, user);
			}
		}
	}
}
