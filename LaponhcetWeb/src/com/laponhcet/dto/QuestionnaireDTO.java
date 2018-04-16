package com.laponhcet.dto;

import java.util.ArrayList;
import java.util.List;

import com.mytechnopal.base.DTOBase;

public class QuestionnaireDTO extends DTOBase {
	private static final long serialVersionUID = 1L;
	
	public static final String SESSION_QUESTIONNAIRE = "SESSION_QUESTIONNAIRE";
	public static final String SESSION_QUESTIONNAIRE_LIST = "SESSION_QUESTIONNAIRE_LIST";
	
	public static final String QUESTIONNAIRE_OPTION_TYPE_UNI = "UNI";
	public static final String QUESTIONNAIRE_OPTION_TYPE_MULTI = "MULTI";
	public static final String QUESTIONNAIRE_OPTION_TYPE_FREEFORMAT = "FREE FORMAT";
	
	private QuestionnaireGroupDTO questionnaireGroup;
	private String question;
	private String optionType;
	private List<DTOBase> questionnaireOptionList;
	private String answer;
	private boolean isMandatory;
	
	public QuestionnaireDTO() {
		super();
		this.questionnaireGroup = new QuestionnaireGroupDTO();
		this.question = "";
		this.optionType = QUESTIONNAIRE_OPTION_TYPE_UNI;
		this.isMandatory = false;
		this.questionnaireOptionList = new ArrayList<DTOBase>();
		this.answer = "";
	}

	public QuestionnaireDTO getQuestionnaire() {
		QuestionnaireDTO questionnaire = new QuestionnaireDTO();
		questionnaire.setId(super.getId());
		questionnaire.setCode(super.getCode());
		questionnaire.setQuestionnaireGroup(this.questionnaireGroup);
		questionnaire.setQuestion(this.question);
		questionnaire.setOptionType(this.optionType);
		questionnaire.setMandatory(this.isMandatory);
		questionnaire.setQuestionnaireOptionList(this.questionnaireOptionList);
		questionnaire.setAnswer(this.answer);
		return questionnaire;
	}
	
	public QuestionnaireGroupDTO getQuestionnaireGroup() {
		return questionnaireGroup;
	}

	public void setQuestionnaireGroup(QuestionnaireGroupDTO questionnaireGroup) {
		this.questionnaireGroup = questionnaireGroup;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOptionType() {
		return optionType;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	public List<DTOBase> getQuestionnaireOptionList() {
		return questionnaireOptionList;
	}

	public void setQuestionnaireOptionList(List<DTOBase> questionnaireOptionList) {
		this.questionnaireOptionList = questionnaireOptionList;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isMandatory() {
		return isMandatory;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}
}
