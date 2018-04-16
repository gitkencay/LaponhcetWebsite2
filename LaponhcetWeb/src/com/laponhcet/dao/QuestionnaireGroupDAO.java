package com.laponhcet.dao;

import java.sql.ResultSet;
import java.util.List;

import com.laponhcet.dto.QuestionnaireGroupDTO;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class QuestionnaireGroupDAO extends DAOBase {
	private static final long serialVersionUID = 1L;

	private String qryQuestionnaireGroupList = "QUESTIONNAIRE_GROUP_LIST";
	
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

	public List<DTOBase> getQuestionnaireGroupList() {
		return getDTOList(qryQuestionnaireGroupList);
	}
	
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		QuestionnaireGroupDTO questionnaireGroup = new QuestionnaireGroupDTO();
		questionnaireGroup.setId((Integer) getDBVal(resultSet, "id"));
		questionnaireGroup.setCode((String) getDBVal(resultSet, "code"));
		questionnaireGroup.setName((String) getDBVal(resultSet, "name"));
		questionnaireGroup.setRemarks((String) getDBVal(resultSet, "remarks"));
		questionnaireGroup.setIcon((String) getDBVal(resultSet, "icon"));
		return questionnaireGroup;
	}

}
