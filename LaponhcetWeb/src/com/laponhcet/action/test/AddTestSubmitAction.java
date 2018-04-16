package com.laponhcet.action.test;

import java.util.List;

import com.laponhcet.dto.QuestionnaireDTO;
import com.laponhcet.dto.QuestionnaireOptionDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;
import com.mytechnopal.util.WebUtil;

public class AddTestSubmitAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
	
	protected void setInput() {
		for(DTOBase questionnaireObj: (List<DTOBase>) getSessionAttribute(QuestionnaireDTO.SESSION_QUESTIONNAIRE_LIST)) {
			QuestionnaireDTO questionnaire = (QuestionnaireDTO) questionnaireObj;
			String answerUni = getRequestString(questionnaire.getCode());
			if(questionnaire.getOptionType().equalsIgnoreCase(QuestionnaireDTO.QUESTIONNAIRE_OPTION_TYPE_UNI)) {
				questionnaire.setAnswer(answerUni);
			}
			else if(questionnaire.getOptionType().equalsIgnoreCase(QuestionnaireDTO.QUESTIONNAIRE_OPTION_TYPE_MULTI)) {
				String answerMulti = ""; 
				for(DTOBase questionnaireOptionObj:  questionnaire.getQuestionnaireOptionList()) {
					QuestionnaireOptionDTO questionnaireOption = (QuestionnaireOptionDTO) questionnaireOptionObj;
					String ans = getRequestString(getRequestString(questionnaireOption.getCode()));
					if(!StringUtil.isEmpty(ans)) {
						if(!StringUtil.isEmpty(answerMulti)) {
							answerMulti += "~";
						}
						answerMulti += ans;
					}					
				}
				questionnaire.setAnswer(answerMulti);
			}
			else {
				questionnaire.setAnswer(answerUni);
			}
		}
	}
	
	protected void validateInput() {
		int i=1;
		for(DTOBase questionnaireObj: (List<DTOBase>) getSessionAttribute(QuestionnaireDTO.SESSION_QUESTIONNAIRE_LIST)) {
			QuestionnaireDTO questionnaire = (QuestionnaireDTO) questionnaireObj;
			if(questionnaire.isMandatory()) {
				if(StringUtil.isEmpty(questionnaire.getAnswer())) {
					actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Question #" + i);
					break;
				}
				else {
					if(i==1) {
						if(!WebUtil.isValidEmail(questionnaire.getAnswer())) {
							actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Email Address");
						}
					}
				}
			}
			i++;
		}
	}
	
}
