package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;

public class QuestionnaireOptionDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_QUESTIONNAIRE_OPTION = "SESSION_QUESTIONNAIRE_OPTION";
	public static final String SESSION_QUESTIONNAIRE_OPTION_LIST = "SESSION_QUESTIONNAIRE_OPTION_LIST";
	
	private String questionnaireCode;
	private String description;
	
	public QuestionnaireOptionDTO() {
		super();
		this.questionnaireCode = "";
		this.description = "";
	}
	
	public QuestionnaireOptionDTO getQuestionnaireOption() {
		QuestionnaireOptionDTO questionnaireOption = new QuestionnaireOptionDTO();
		questionnaireOption.setId(super.getId());
		questionnaireOption.setCode(super.getCode());
		questionnaireOption.setQuestionnaireCode(this.questionnaireCode);
		questionnaireOption.setDescription(this.description);
		return questionnaireOption;
	}

	public String getQuestionnaireCode() {
		return questionnaireCode;
	}

	public void setQuestionnaireCode(String questionnaireCode) {
		this.questionnaireCode = questionnaireCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
