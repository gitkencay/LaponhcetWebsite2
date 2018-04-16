package com.laponhcet.action.guest;

import com.laponhcet.dao.QuestionnaireGroupDAO;
import com.laponhcet.dto.QuestionnaireGroupDTO;
import com.mytechnopal.base.ActionBase;

public class HomeAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		//setSessionBeforeTrans(RegisterDTO.SESSION_REGISTER, new RegisterDTO());
		sessionInfo.setTransitionLink(new String[] {"", "U00024", ""}, new String[] {"U00025", "", ""}, new String[] {"U00026", "", ""}, "", "");	
		setSessionAttribute(QuestionnaireGroupDTO.SESSION_QUESTIONNAIRE_GROUP_LIST, new QuestionnaireGroupDAO().getQuestionnaireGroupList());
		setSessionAttribute(QuestionnaireGroupDTO.SESSION_QUESTIONNAIRE_GROUP, new QuestionnaireGroupDTO());
	}
}