package com.laponhcet.dao;

import java.sql.ResultSet;
import java.util.List;

import com.laponhcet.dto.QuestionnaireOptionDTO;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class QuestionnaireOptionDAO extends DAOBase {
	private static final long serialVersionUID = 1L;

	private String qryQuestionnaireOptionList = "QUESTIONNAIRE_OPTION_LIST";
	private String qryQuestionnaireOptionListByQuestionnaireCode  = "QUESTIONNAIRE_OPTION_LIST_BY_QUESTIONNAIRECODE";
	@Override
	public void executeAdd(DTOBase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeAddList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeDelete(DTOBase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeDeleteList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeUpdate(DTOBase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	public List<DTOBase> getQuestionnaireOptionList() {
		return getDTOList(qryQuestionnaireOptionList);
	}
	
	public List<DTOBase> getQuestionnaireOptionListByQuestionnaireCode(String questionnaireCode) {
		return getDTOList(qryQuestionnaireOptionListByQuestionnaireCode, questionnaireCode);
	}
	
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		QuestionnaireOptionDTO questionnaireOption = new QuestionnaireOptionDTO();
		
		questionnaireOption.setId((Integer) getDBVal(resultSet, "id"));
		questionnaireOption.setCode((String) getDBVal(resultSet, "code"));
		questionnaireOption.setQuestionnaireCode((String) getDBVal(resultSet, "questionnaire_code"));
		questionnaireOption.setDescription((String) getDBVal(resultSet, "description"));
		
		return questionnaireOption;
	}

	public static void main(String[] a) {
		QuestionnaireOptionDAO questionnaireOptionDAO = new QuestionnaireOptionDAO();
		questionnaireOptionDAO.getQuestionnaireOptionListByQuestionnaireCode("001");
	}
}
