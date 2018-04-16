package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.QuestionnaireAnswerDTO;
import com.laponhcet.dto.QuestionnaireDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;

public class QuestionnaireAnswerDAO extends DAOBase {
	private static final long serialVersionUID = 1L;

	private String qryQuestionnaireAnswerLast = "QUESTIONNAIRE_ANSWER_LAST";
	private String qryQuestionnaireAnswerAdd = "QUESTIONNAIRE_ANSWER_ADD";
	
	public void executeAdd(DTOBase obj) {
		
	}

	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		QuestionnaireAnswerDTO questionnaireAnswer = (QuestionnaireAnswerDTO) obj;
		try {
			PreparedStatement prepStmnt = null;
			prepStmnt = conn.prepareStatement(getQueryStatement(qryQuestionnaireAnswerAdd));
			prepStmnt.setString(1, questionnaireAnswer.getCode());
			prepStmnt.setString(2, questionnaireAnswer.getQuestionnaire().getCode());
			prepStmnt.setString(3, questionnaireAnswer.getQuestionnaire().getAnswer());
			prepStmnt.setString(4, questionnaireAnswer.getAddedBy());
			prepStmnt.setTimestamp(5, questionnaireAnswer.getAddedTimestamp());
			prepStmnt.setString(6, questionnaireAnswer.getUpdatedBy());
			prepStmnt.setTimestamp(7, questionnaireAnswer.getUpdatedTimestamp());
			prepStmntList.add(prepStmnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void executeAddList(List<DTOBase> objList) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		String code = getNextCode(((QuestionnaireDTO)objList.get(0)).getQuestionnaireGroup().getCode());
		for(DTOBase questionaireObj: objList) {
			QuestionnaireDTO questionnaire = (QuestionnaireDTO) questionaireObj;
			QuestionnaireAnswerDTO questionnaireAnswer = new QuestionnaireAnswerDTO();
			questionnaireAnswer.setCode(code);
			questionnaireAnswer.setQuestionnaire(questionnaire);
			add(conn, prepStmntList, questionnaireAnswer);
		}
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));

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

	public String getNextCode(String questionnaireGroupCode) {
		String code = questionnaireGroupCode + "00001";
		QuestionnaireAnswerDTO questionnaireAnswerLast = (QuestionnaireAnswerDTO) getDTO(qryQuestionnaireAnswerLast, questionnaireGroupCode);
		if(questionnaireAnswerLast != null) {
			int nextSeries = Integer.parseInt(StringUtil.getRight(questionnaireAnswerLast.getCode(), 5)) + 1;
			String nextSeriesStr = StringUtil.getPadded(String.valueOf(nextSeries), 5, "0", true);
			code = questionnaireGroupCode + nextSeriesStr;
		}
		return code;
	}
	
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		QuestionnaireAnswerDTO questionnaireAnswer = new QuestionnaireAnswerDTO();
		
		questionnaireAnswer.setId((Integer) getDBVal(resultSet, "id"));
		questionnaireAnswer.setCode((String) getDBVal(resultSet, "code"));
		questionnaireAnswer.getQuestionnaire().setCode((String) getDBVal(resultSet, "questionnaire_code"));
		questionnaireAnswer.getQuestionnaire().setAnswer((String) getDBVal(resultSet, "answer"));
		
		return questionnaireAnswer;
	}

}
