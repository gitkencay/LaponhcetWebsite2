package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;

public class QuestionnaireAnswerDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_QUESTIONNAIRE_ANSWER = "SESSION_QUESTIONNAIRE_ANSWER";
	public static final String SESSION_QUESTIONNAIRE_ANSWER_LIST = "SESSION_QUESTIONNAIRE_ANSWER_LIST";
	
	private QuestionnaireDTO questionnaire;
	
	public QuestionnaireAnswerDTO getQuestionnaireAnswer() {
		QuestionnaireAnswerDTO questionnaireAnswer  = new QuestionnaireAnswerDTO();
		questionnaireAnswer.setId(super.getId());
		questionnaireAnswer.setCode(super.getCode());
		questionnaireAnswer.setQuestionnaire(this.questionnaire);
		return questionnaireAnswer;
	}
	
	public QuestionnaireAnswerDTO() {
		super();
		questionnaire = new QuestionnaireDTO();
	}

	public QuestionnaireDTO getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(QuestionnaireDTO questionnaire) {
		this.questionnaire = questionnaire;
	}
	
}
