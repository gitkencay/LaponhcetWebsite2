package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.AcademicYearDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;

public class AcademicYearDAO extends DAOBase{
	private static final long serialVersionUID = 1L;
	
	private String qryAcademicYearAdd = "ACADEMIC_YEAR_ADD";
	private String qryAcademicYearUpdate = "ACADEMIC_YEAR_UPDATE";
	private String qryAcademicYearDelete = "ACADEMIC_YEAR_DELETE";
	private String qryAcademicYearByCode = "ACADEMIC_YEAR_BY_CODE";
	private String qryAcademicYearList = "ACADEMIC_YEAR_LIST";
	private String qryAcademicYearLastCode = "ACADEMIC_YEAR_LAST_CODE";
	private String qryAcademicYearListSearchByName = "ACADEMIC_YEAR_LIST_SEARCHBY_NAME";
	
	private String getGeneratedCode() {
		AcademicYearDTO academicYear = getAcademicYearLast();
		String code = "001"; //default teacher code
		if(academicYear != null) {
			int nextNum = Integer.parseInt(academicYear.getCode()) + 1; 
			code = StringUtil.getPadded(String.valueOf(nextNum), 3, "0", true);
		}
		return code;
	}
		
	private AcademicYearDTO getAcademicYearLast() {
		return (AcademicYearDTO) getDTO(qryAcademicYearLastCode);
	}
	
	@Override
	public void executeAdd(DTOBase obj) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		AcademicYearDTO academicYear = (AcademicYearDTO) obj;
		academicYear.setCode(getGeneratedCode());
		academicYear.setBaseDataOnInsert();
		add(conn, prepStmntList, academicYear);	
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
		
	}
	private void add(Connection conn, List<PreparedStatement> prepStmntList, DTOBase obj) {
		AcademicYearDTO academicYear = (AcademicYearDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryAcademicYearAdd));
			prepStmnt.setString(1, academicYear.getCode());
			prepStmnt.setString(2, academicYear.getName());
			prepStmnt.setString(3, DateTimeUtil.getDateTimeToStr(academicYear.getDateStart(), "yyyy-MM-dd"));
			prepStmnt.setString(4, DateTimeUtil.getDateTimeToStr(academicYear.getDateEnd(), "yyyy-MM-dd"));
			prepStmnt.setString(5, academicYear.getAcademicProgramCodes());
			prepStmnt.setString(6, academicYear.getAddedBy());
			prepStmnt.setTimestamp(7, academicYear.getAddedTimestamp());
			prepStmnt.setString(8, academicYear.getUpdatedBy());
			prepStmnt.setTimestamp(9, academicYear.getUpdatedTimestamp());	
		} catch (Exception e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	@Override
	public void executeAddList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeDelete(DTOBase obj) {
		AcademicYearDTO academicYear = (AcademicYearDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		delete(conn, prepStmntList, academicYear);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
		
	private void delete(Connection conn, List<PreparedStatement> prepStmntList, DTOBase obj ) {
		AcademicYearDTO academicYear = (AcademicYearDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryAcademicYearDelete));
			prepStmnt.setInt(1, academicYear.getId());	
		} catch (Exception e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
		
	}
	@Override
	public void executeDeleteList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeUpdate(DTOBase obj) {
		AcademicYearDTO academicYear = (AcademicYearDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		update(conn, prepStmntList, academicYear);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
		
	}
	
	private void update(Connection conn, List<PreparedStatement> prepStmntList, DTOBase obj ) {
		AcademicYearDTO academicYear = (AcademicYearDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryAcademicYearUpdate));
			prepStmnt.setString(1, academicYear.getCode());
			prepStmnt.setString(2, academicYear.getName());
			prepStmnt.setString(3, DateTimeUtil.getDateTimeToStr(academicYear.getDateStart(), "yyyy-MM-dd"));
			prepStmnt.setString(4, DateTimeUtil.getDateTimeToStr(academicYear.getDateEnd(), "yyyy-MM-dd"));
			prepStmnt.setString(5, academicYear.getAcademicProgramCodes());
			prepStmnt.setString(6, academicYear.getUpdatedBy());
			prepStmnt.setTimestamp(7, academicYear.getUpdatedTimestamp());
			prepStmnt.setInt(8, academicYear.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
		
	}
	
	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}

	public AcademicYearDTO getAcademicYearByCode(String code) {
		return (AcademicYearDTO) getDTO(qryAcademicYearByCode, code);
	}
	
	public List<DTOBase> getAcademicYearListSearchByName(String searchValue) {
		return getDTOList(qryAcademicYearListSearchByName, "%" + searchValue + "%");
	}
	
	public List<DTOBase> getAcademicYearList() {
		return getDTOList(qryAcademicYearList);
	}

	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
	    AcademicYearDTO academicYear = new AcademicYearDTO();
	    academicYear.setId(((Integer)getDBVal(resultSet, "id")));
	    academicYear.setCode((String)getDBVal(resultSet, "code"));
	    academicYear.setName((String)getDBVal(resultSet, "name"));
	    academicYear.setDateStart((Date)getDBVal(resultSet, "date_start"));
	    academicYear.setDateEnd((Date)getDBVal(resultSet, "date_end"));
	    academicYear.setAcademicProgramCodes((String)getDBVal(resultSet, "academic_program_codes"));
	    academicYear.setDisplayText(academicYear.getName() + " [" + DateTimeUtil.getDateTimeToStr(academicYear.getDateStart(), "MM-dd-yyyy") + " to " + DateTimeUtil.getDateTimeToStr(academicYear.getDateEnd(), "MM-dd-yyyy") + "]");
	    return academicYear;
	}
}