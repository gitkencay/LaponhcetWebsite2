package com.laponhcet.action.message;

import java.util.List;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.StudentDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.MessageDTO;
import com.laponhcet.dto.StudentDTO;
import com.laponhcet.util.MessageUtil;
import com.laponhcet.util.UserAccessUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;


public class AddMessageAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"U00012", "U00007", "U00013"}, new String[] {"U00008", "U00014", "U00016"}, new String[] {"U00009", "U00015", "U00017"}, "U00010", "U00011");
		setSessionBeforeTrans(MessageDTO.SESSION_MESSAGE,  new MessageDTO());
	}
}
