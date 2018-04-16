package com.laponhcet.action.test;

import java.util.List;

import com.laponhcet.dao.QuestionnaireAnswerDAO;
import com.laponhcet.dto.QuestionnaireDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class AddTestConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
	}
	
	protected void executeLogic() {
		executeList((List<DTOBase>)getSessionAttribute(QuestionnaireDTO.SESSION_QUESTIONNAIRE_LIST), new QuestionnaireAnswerDAO(), DAOBase.DAO_ACTION_ADD);
	}
}
