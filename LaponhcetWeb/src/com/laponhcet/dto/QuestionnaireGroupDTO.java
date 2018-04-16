package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;

public class QuestionnaireGroupDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_QUESTIONNAIRE_GROUP = "SESSION_QUESTIONNAIRE_GROUP";
	public static final String SESSION_QUESTIONNAIRE_GROUP_LIST = "SESSION_QUESTIONNAIRE_GROUP_LIST";
	
	private String name;
	private String remarks;
	private String icon;

	public QuestionnaireGroupDTO() {
		super();
		this.name = "";
		this.remarks = "";
		this.icon = "";
	}
	
	public QuestionnaireGroupDTO getQuestionnaireGroup() {
		QuestionnaireGroupDTO questionnaireGroup = new QuestionnaireGroupDTO();
		questionnaireGroup.setId(super.getId());
		questionnaireGroup.setCode(super.getCode());
		questionnaireGroup.setName(this.name);
		questionnaireGroup.setRemarks(this.remarks);
		questionnaireGroup.setIcon(this.icon);
		return questionnaireGroup;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
