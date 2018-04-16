package com.laponhcet.dao;

import java.sql.ResultSet;
import java.util.List;

import com.laponhcet.dto.QuestionnaireDTO;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class QuestionnaireDAO extends DAOBase {

	private static final long serialVersionUID = 1L;

	private String qryQuestionnaireListByQuestionnaireGroupCode = "QUESTIONNAIRE_LIST_BY_QUESTIONNAIREGROUPCODE";
	
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

	public List<DTOBase> getQuestionnaireListByQuestionnaireGroupCode(String questionnaireGroupCode) {
		return getDTOList(qryQuestionnaireListByQuestionnaireGroupCode, questionnaireGroupCode);
	}
	
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		QuestionnaireDTO questionnaire = new QuestionnaireDTO();
		questionnaire.setId((Integer) getDBVal(resultSet, "id"));
		questionnaire.setCode((String) getDBVal(resultSet, "code"));
		questionnaire.getQuestionnaireGroup().setCode((String) getDBVal(resultSet, "questionnaire_group_code"));
		questionnaire.setQuestion((String) getDBVal(resultSet, "question"));
		questionnaire.setOptionType((String) getDBVal(resultSet, "option_type"));
		questionnaire.setMandatory((Boolean) getDBVal(resultSet, "is_mandatory"));
		return questionnaire;
	}

	public static void main(String[] a) {
		QuestionnaireDAO questionnaireDAO = new QuestionnaireDAO();
		questionnaireDAO.getQuestionnaireListByQuestionnaireGroupCode("001");
	}
}
