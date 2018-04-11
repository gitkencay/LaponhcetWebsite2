package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.dto.FeeStudentSpecificDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class FeeStudentSpecificDAO extends DAOBase {
	private static final long serialVersionUID = 1L;

	private String qryFeeStudentSpecificAdd = "FEE_STUDENT_SPECIFIC_ADD";
	private String qryFeeStudentSpecificUpdate = "FEE_STUDENT_SPECIFIC_UPDATE";
	private String qryFeeStudentSpecificDelete = "FEE_STUDENT_SPECIFIC_DELETE";
	private String qryFeeStudentSpecificList = "FEE_STUDENT_SPECIFIC_LIST";
	private String qryFeeStudentSpecificListSearchByStudent = "FEE_STUDENT_SPECIFIC_LIST_SEARCHBY_STUDENT";
	private String qryFeeStudentSpecificListByStudentCodeAcademicYearCode = "FEE_STUDENT_SPECIFIC_LIST_BY_STUDENTCODEACADEMICYEARCODE";
	private String qryFeeStudentSpecificListByStudentCodeSemesterCode = "FEE_STUDENT_SPECIFIC_LIST_BY_STUDENTCODESEMESTERCODE";
	
	@Override
	public void executeAdd(DTOBase obj) {
		FeeStudentSpecificDTO feeStudentSpecific = (FeeStudentSpecificDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		feeStudentSpecific.setBaseDataOnInsert();
		add(conn, prepStmntList, feeStudentSpecific);		
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	protected void add(Connection conn, List<PreparedStatement> prepStmntList, DTOBase obj) {
		FeeStudentSpecificDTO feeStudentSpecific = (FeeStudentSpecificDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryFeeStudentSpecificAdd));
			prepStmnt.setString(1, feeStudentSpecific.getAcademicYear().getCode());
			prepStmnt.setString(2, feeStudentSpecific.getSemester().getCode());
			prepStmnt.setString(3, feeStudentSpecific.getStudent().getCode());
			prepStmnt.setString(4, feeStudentSpecific.getFee().getCode());
			prepStmnt.setDouble(5, feeStudentSpecific.getAmount());
			prepStmnt.setString(6, feeStudentSpecific.getAddedBy());
			prepStmnt.setTimestamp(7, feeStudentSpecific.getAddedTimestamp());
			prepStmnt.setString(8, feeStudentSpecific.getUpdatedBy());
			prepStmnt.setTimestamp(9, feeStudentSpecific.getUpdatedTimestamp());	
		} catch (Exception e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public List<DTOBase> getFeeStudentSpecificList() {
		return getDTOList(qryFeeStudentSpecificList);
	}

	public List<DTOBase> getFeeStudentSpecificListSearchByStudent(String searchValue) {
		return getDTOList(qryFeeStudentSpecificListSearchByStudent, "%" + searchValue + "%");
	}
	
	public List<DTOBase> getFeeStudentSpecificListByStudentCodeAcademicYearCode(String studentCode, String academicYearCode) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(studentCode);
		paramList.add(academicYearCode);
		return getDTOList(qryFeeStudentSpecificListByStudentCodeAcademicYearCode, paramList);
	}
	
	public List<DTOBase> getFeeStudentSpecificListByStudentCodeSemesterCode(String studentCode, String semesterCode) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(studentCode);
		paramList.add(semesterCode);
		return getDTOList(qryFeeStudentSpecificListByStudentCodeSemesterCode, paramList);
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

	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		FeeStudentSpecificDTO feeStudentSpecific = new FeeStudentSpecificDTO();
		feeStudentSpecific.setId((Integer) getDBVal(resultSet, "id"));
		feeStudentSpecific.getAcademicYear().setCode((String) getDBVal(resultSet, "academic_year_code"));
		feeStudentSpecific.getSemester().setCode((String) getDBVal(resultSet, "semester_code"));
		feeStudentSpecific.getStudent().setCode((String) getDBVal(resultSet, "student_code"));
		feeStudentSpecific.getFee().setCode((String) getDBVal(resultSet, "fee_code"));
		feeStudentSpecific.setAmount((Double) getDBVal(resultSet, "amount"));
		feeStudentSpecific.setDisplayText(feeStudentSpecific.getFee().getName());
		return feeStudentSpecific;
	}

}
