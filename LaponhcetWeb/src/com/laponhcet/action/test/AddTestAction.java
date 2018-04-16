package com.laponhcet.action.test;

import java.util.List;

import com.laponhcet.dao.QuestionnaireDAO;
import com.laponhcet.dao.QuestionnaireOptionDAO;
import com.laponhcet.dto.QuestionnaireDTO;
import com.laponhcet.dto.QuestionnaireGroupDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class AddTestAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {	
		if(!sessionInfo.isPreviousLinkAddSubmit()){
			List<DTOBase> questionnaireGroupList = (List<DTOBase>) getSessionAttribute(QuestionnaireGroupDTO.SESSION_QUESTIONNAIRE_GROUP_LIST);
			QuestionnaireGroupDTO questionnaireGroup = (QuestionnaireGroupDTO) DTOUtil.getObjById(questionnaireGroupList, getRequestInt("txtSelectedRecord"));
			
			List<DTOBase> questionnaireList = new QuestionnaireDAO().getQuestionnaireListByQuestionnaireGroupCode(questionnaireGroup.getCode());
			for(DTOBase questionnaireObj: questionnaireList) {
				QuestionnaireDTO questionnaire = (QuestionnaireDTO) questionnaireObj;
				questionnaire.setQuestionnaireGroup(questionnaireGroup);
				questionnaire.setQuestionnaireOptionList(new QuestionnaireOptionDAO().getQuestionnaireOptionListByQuestionnaireCode(questionnaire.getCode()));
			}
			
			setSessionAttribute(QuestionnaireGroupDTO.SESSION_QUESTIONNAIRE_GROUP, questionnaireGroup);
			setSessionBeforeTrans(QuestionnaireDTO.SESSION_QUESTIONNAIRE_LIST, questionnaireList);
		}
	}
}
