package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.SemesterDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;

public class SemesterDAO extends DAOBase {
	private static final long serialVersionUID = 1L;
	
	private String qrySemesterAdd = "SEMESTER_ADD";
	private String qrySemesterUpdate = "SEMESTER_UPDATE";
	private String qrySemesterDelete = "SEMESTER_DELETE";
	private String qrySemesterLast = "SEMESTER_LAST";
	private String qrySemesterByName = "SEMESTER_BY_NAME";
	private String qrySemesterByCode = "SEMESTER_BY_CODE";
	private String qrySemesterByAcademicYearCodeName = "SEMESTER_BY_ACADEMICYEARCODENAME";
	private String qrySemesterByDateStartDateEnd = "SEMESTER_BY_DATEENDDATESTART";
	private String qrySemesterList = "SEMESTER_LIST";
	private String qrySemesterListByAcademicYearCode = "SEMESTER_LIST_BY_ACADEMICYEARCODE";
	private String qrySemesterSearchByName = "SEMESTER_LIST_SEARCHBY_NAME";

	@Override
	public void executeAdd(DTOBase obj) {
		SemesterDTO semester = (SemesterDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		semester.setCode(getGeneratedCode());
		semester.setBaseDataOnInsert();
		add(conn, prepStmntList, semester);		
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	protected void add(Connection conn, List<PreparedStatement> prepStmntList, DTOBase obj) {
		SemesterDTO semester = (SemesterDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qrySemesterAdd));
			prepStmnt.setString(1, semester.getCode());
			prepStmnt.setString(2, semester.getAcademicYear().getCode());
			prepStmnt.setInt(3, semester.getName());
			prepStmnt.setString(4, DateTimeUtil.getDateTimeToStr(semester.getDateStart(), "yyyy-MM-dd"));
			prepStmnt.setString(5, DateTimeUtil.getDateTimeToStr(semester.getDateEnd(), "yyyy-MM-dd"));
			prepStmnt.setString(6, semester.getAddedBy());
			prepStmnt.setTimestamp(7, semester.getAddedTimestamp());
			prepStmnt.setString(8, semester.getUpdatedBy());
			prepStmnt.setTimestamp(9, semester.getUpdatedTimestamp());	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public String getGeneratedCode() {
		SemesterDTO semester = getSemesterLast();
		String code = "001"; //default semester code
		if(semester != null) {
			int nextNum = Integer.parseInt(semester.getCode()) + 1; 
			code =  StringUtil.getPadded(String.valueOf(nextNum), 3, "0", true);
		}
		return code;
	}
	
	private SemesterDTO getSemesterLast() {
		return (SemesterDTO) getDTO(qrySemesterLast);
	}
	
	public List<DTOBase> getSemesterList() {
		return getDTOList(qrySemesterList);
	}
	
	public List<DTOBase> getSemesterListByAcademicYearCode(String academicYearCode) {	
		return getDTOList(qrySemesterListByAcademicYearCode, academicYearCode);
	}
	
	public List<DTOBase> getSemesterListSearchByName(Integer searchValue) {
		return getDTOList(qrySemesterSearchByName, searchValue);
	}
	
	public SemesterDTO getSemesterByAcademicYearCodeName(String academicYearCode, Integer Name) {
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(academicYearCode);
		paramList.add(Name );	
		return (SemesterDTO) getDTO(qrySemesterByAcademicYearCodeName, paramList);	
	}
	
	public SemesterDTO getSemesterByDateEndDateStart(String dateStart, String dateEnd) {
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(dateStart);
		paramList.add(dateEnd );	
		return (SemesterDTO) getDTO(qrySemesterByDateStartDateEnd, paramList);	
	}
	
	public SemesterDTO getSemesterByCode(String code) {
		return (SemesterDTO) getDTO(qrySemesterByCode, code);
	}

	@Override
	public void executeDelete(DTOBase obj) {
		SemesterDTO semester = (SemesterDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		delete(conn, prepStmntList, semester);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		SemesterDTO semester = (SemesterDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qrySemesterDelete));	
			prepStmnt.setInt(1, semester.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}

	@Override
	public void executeUpdate(DTOBase obj) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		SemesterDTO semester = (SemesterDTO) obj;
		semester.setBaseDataOnUpdate();
		update(conn, prepStmntList, semester);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	protected void update(Connection conn, List<PreparedStatement> prepStmntList, DTOBase obj) {
		SemesterDTO semester = (SemesterDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qrySemesterUpdate));
			prepStmnt.setString(1, semester.getAcademicYear().getCode());
			prepStmnt.setInt(2, semester.getName());
			prepStmnt.setString(3, DateTimeUtil.getDateTimeToStr(semester.getDateStart(), "yyyy-MM-dd"));
			prepStmnt.setString(4, DateTimeUtil.getDateTimeToStr(semester.getDateEnd(), "yyyy-MM-dd"));
			prepStmnt.setString(5, semester.getUpdatedBy());
			prepStmnt.setTimestamp(6, semester.getUpdatedTimestamp());
			prepStmnt.setInt(7, semester.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	@Override
	public void executeAddList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeDeleteList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		SemesterDTO semester = new SemesterDTO();
		semester.setId((Integer) getDBVal(resultSet, "id"));
		semester.setCode((String) getDBVal(resultSet, "code"));
		semester.getAcademicYear().setCode((String) getDBVal(resultSet, "academic_year_code"));
		semester.setName((Integer) getDBVal(resultSet, "name"));
		semester.setDateStart((Date) getDBVal(resultSet, "date_start"));
		semester.setDateEnd((Date) getDBVal(resultSet, "date_end"));
		return semester;
	}

}
