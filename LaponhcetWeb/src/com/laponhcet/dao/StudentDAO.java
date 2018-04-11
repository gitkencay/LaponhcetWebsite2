package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.SemesterDTO;
import com.laponhcet.dto.StudentDTO;
import com.laponhcet.util.SemesterUtil;
import com.laponhcet.util.SettingsUtil;
import com.laponhcet.util.UserRFIDUtil;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.MediaDAO;
import com.mytechnopal.dao.UpdateKeyDAO;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dao.UserLinkDAO;
import com.mytechnopal.dto.UserGroupDTO;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;

public class StudentDAO extends DAOBase {
	
	private static final long serialVersionUID = 1L;

	private String qryStudentAdd = "STUDENT_ADD";
	private String qryStudentList = "STUDENT_LIST";
	private String qryStudentByCode = "STUDENT_BY_CODE";
	private String qryStudentUpdate = "STUDENT_UPDATE";
	
	private String qryStudentDelete = "STUDENT_DELETE";
	private String qryStudentByName = "STUDENT_BY_NAME";
	private String qryStudentListSearchByAcademicProgramCode = "STUDENT_LIST_SEARCHBY_ACADEMICPROGRAMCODE";
	private String qryStudentLast = "STUDENT_LAST";
	private String qryStudentLastBCC = "STUDENT_LAST_BCC";
	private String qryStudentLastFAST = "STUDENT_LAST_FAST";
	private String qryStudentLastFBC = "STUDENT_LAST_FBC";
	
	@Override
	public void executeAdd(DTOBase obj) {
		StudentDTO student = (StudentDTO) obj;	
		student.setUserGroupCodes(UserGroupDTO.USER_GROUP_STUDENT_CODE);

		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		
		if(StringUtil.isEmpty(student.getCode())) {
			student.setCode(getGeneratedCode());
		}
		student.setUserName(student.getCode());
		student.setPassword(StringUtil.getUniqueId(3, 3));
		student.setActive(true);
		student.setBaseDataOnInsert();
		
		//Student
		add(conn, prepStmntList, student);

		//User
		new UserDAO().add(conn, prepStmntList, student);
		
		//User RFID
		new UserRFIDDAO().add(conn, prepStmntList, UserRFIDUtil.getUserRFIDByUser(student));
				
		//Media
		student.setProfilePictInfo("STUDENT", "PROFILE_PICT");
		new MediaDAO().add(conn, prepStmntList, student.getProfilePict());
		
		//Update key
		student.setUpdateKeyInfo();
		new UpdateKeyDAO().add(conn, prepStmntList, student.getUpdateKey());
		
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
		
		//To be used if facekeeper and the server are in separate machines
		//User RFID
		/*try {
			UserRFIDDAO UserRFIDDAO = new UserRFIDDAO();
			UserRFIDDAO.daoConnector.serverLocation = DAOConnector.SERVER_LOCATION_FACEKEEPER1;
			UserRFIDDTO userRFID = new UserRFIDDTO();
			UserRFIDUtil.setUserRFIDByUser(student, userRFID);
			UserRFIDDAO.executeAdd(userRFID);
			
			if(SettingsUtil.OWNER_CODE.equalsIgnoreCase(SettingsUtil.OWNER_CODE_BCC)) {
				UserRFIDDAO.daoConnector.serverLocation = DAOConnector.SERVER_LOCATION_FACEKEEPER2;
				UserRFIDDAO.executeAdd(userRFID);
			}
			UserRFIDDAO.daoConnector.serverLocation = DAOConnector.SERVER_LOCATION_LOCAL;
		}
		catch(Exception e) {
			e.printStackTrace();
		}*/
	}
	
	public String getGeneratedCode() {
		StudentDTO student = null;
		String code = "";
		if(SettingsUtil.OWNER_CODE.equalsIgnoreCase(SettingsUtil.OWNER_CODE_BCC)) {
			student = getStudentLastBCC();
			SemesterDTO semester = SemesterUtil.getSemesterCurrent();
			code = StringUtil.getLeft(semester.getAcademicYear().getName(), 4) + String.valueOf(semester.getName()) + "00001"; //default student code
			if(student != null) {
				int nextStudNum = Integer.parseInt(StringUtil.getRight(student.getCode(), 5)) + 1;
				code = StringUtil.getLeft(semester.getAcademicYear().getName(), 4) + String.valueOf(semester.getName()) + StringUtil.getPadded(String.valueOf(nextStudNum), 5, "0", true);
			}
		}
		else if(SettingsUtil.OWNER_CODE.equalsIgnoreCase(SettingsUtil.OWNER_CODE_FAST)) {
			student = getStudentLastFAST();
			code = StringUtil.getRight( String.valueOf(DateTimeUtil.getCurrentYear()), 2) + "0001"; //default student code
			if(student != null) {
				int nextStudNum = Integer.parseInt(StringUtil.getRight(student.getCode(), 4)) + 1;
				code = StringUtil.getRight( String.valueOf(DateTimeUtil.getCurrentYear()), 2) + StringUtil.getPadded(String.valueOf(nextStudNum), 4, "0", true);
			}
		}
		else if(SettingsUtil.OWNER_CODE.equalsIgnoreCase(SettingsUtil.OWNER_CODE_FBC)) {
			student = getStudentLastFBC();
			code = "0001" + StringUtil.getRight(String.valueOf(DateTimeUtil.getCurrentYear()), 2); //default student code
			if(student != null) {
				int nextStudNum = Integer.parseInt(StringUtil.getLeft(student.getCode(), 4)) + 1;
				code = StringUtil.getPadded(String.valueOf(nextStudNum) + StringUtil.getRight( String.valueOf(DateTimeUtil.getCurrentYear()), 2), 6, "0", true);
			}
		}
		else {
			student = getStudentLast();
			code = "00001" + StringUtil.getRight(String.valueOf(DateTimeUtil.getCurrentYear()), 2); //default student code
			if(student != null) {
				int nextStudNum = Integer.parseInt(StringUtil.getLeft(student.getCode(), 5)) + 1;
				code = StringUtil.getPadded(String.valueOf(nextStudNum) + StringUtil.getRight( String.valueOf(DateTimeUtil.getCurrentYear()), 2), 7, "0", true);
			}
		}
		return code;		
	}
	
	//Other Schools
	private StudentDTO getStudentLast() {
		return (StudentDTO) getDTO(qryStudentLast);
	}
		
	//BCC
	private StudentDTO getStudentLastBCC() {
		return (StudentDTO) getDTO(qryStudentLastBCC);
	}
	
	//FAST
	private StudentDTO getStudentLastFAST() {
		return (StudentDTO) getDTO(qryStudentLastFAST);
	}
	
	//FBC
	private StudentDTO getStudentLastFBC() {
		return (StudentDTO) getDTO(qryStudentLastFBC, StringUtil.getRight(String.valueOf(DateTimeUtil.getCurrentYear()), 2));
	}
		
	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		StudentDTO student = (StudentDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryStudentAdd));
			prepStmnt.setString(1, student.getCode());
			prepStmnt.setString(2, student.getLearnerReferenceNumber());
			prepStmnt.setString(3, student.getAcademicProgram().getCode());
			prepStmnt.setString(4, student.getSkills());
			prepStmnt.setString(5, student.getElementarySchoolCompletedAt().getCode());
			prepStmnt.setInt(6, student.getElementarySchoolGraduatedYear());
			prepStmnt.setString(7, student.getJuniorHighSchoolCompletedAt().getCode());
			prepStmnt.setInt(8, student.getJuniorHighSchoolGraduatedYear());
			prepStmnt.setString(9, student.getSeniorHighSchoolCompletedAt().getCode());
			prepStmnt.setInt(10, student.getSeniorHighSchoolGraduatedYear());
			prepStmnt.setString(11, student.getVocationalSchoolCompletedAt().getCode());
			prepStmnt.setInt(12, student.getVocationalSchoolGraduatedYear());
			prepStmnt.setString(13, student.getCollegeSchoolCompletedAt().getCode());
			prepStmnt.setInt(14, student.getCollegeSchoolGraduatedYear());
			prepStmnt.setString(15, student.getGraduateSchoolCompletedAt().getCode());
			prepStmnt.setInt(16, student.getGraduateSchoolGraduatedYear());
			prepStmnt.setString(17, student.getSchoolLastAttendedAt().getCode());
			prepStmnt.setInt(18, student.getSchoolLastAttendedYear());
			prepStmnt.setString(19, student.getHighestEducationalAttainment());
			prepStmnt.setString(20, student.getHighestEducationalAttainmentMajor());
			prepStmnt.setString(21, student.getEntranceCredentials());
			prepStmnt.setString(22, student.getAddedBy());
			prepStmnt.setTimestamp(23, student.getAddedTimestamp());
			prepStmnt.setString(24, student.getUpdatedBy());
			prepStmnt.setTimestamp(25, student.getUpdatedTimestamp());	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	@Override
	public void executeDelete(DTOBase obj) {
		StudentDTO student = (StudentDTO) obj;
		
		List<DTOBase> userLinkList = new UserLinkDAO().getUserLinkListByUserCode(student.getCode());
		
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		
		//Student
		delete(conn, prepStmntList, student);

		//User
		new UserDAO().delete(conn, prepStmntList, student);
		
		//UserRFID
		new UserRFIDDAO().delete(conn, prepStmntList, UserRFIDUtil.getUserRFIDByUser(student));
		
		//Media
		new MediaDAO().delete(conn, prepStmntList, student.getProfilePict());
		
		//Update Key
		new UpdateKeyDAO().delete(conn, prepStmntList, student.getUpdateKey());
		
		//User Link
		if(userLinkList.size() >= 1) {
			new UserLinkDAO().delete(conn, prepStmntList, student);
		}

		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		StudentDTO student = (StudentDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryStudentDelete));	
			prepStmnt.setInt(1, student.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	@Override
	public void executeUpdate(DTOBase obj) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		StudentDTO student = (StudentDTO) obj;
		student.setBaseDataOnUpdate();
		
		//Student
		update(conn, prepStmntList, student);
		
		//User
		new UserDAO().update(conn, prepStmntList, student);
		
		//UserRFID
		new UserRFIDDAO().update(conn, prepStmntList, UserRFIDUtil.getUserRFIDByUser(student));
				
		//Media
		new MediaDAO().update(conn, prepStmntList, student.getProfilePict());
		
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		StudentDTO student = (StudentDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryStudentUpdate));
			prepStmnt.setString(1, student.getCode());
			prepStmnt.setString(2, student.getLearnerReferenceNumber());
			prepStmnt.setString(3, student.getAcademicProgram().getCode());
			prepStmnt.setString(4, student.getSkills());
			prepStmnt.setString(5, student.getElementarySchoolCompletedAt().getCode());
			prepStmnt.setInt(6, student.getElementarySchoolGraduatedYear());
			prepStmnt.setString(7, student.getJuniorHighSchoolCompletedAt().getCode());
			prepStmnt.setInt(8, student.getJuniorHighSchoolGraduatedYear());
			prepStmnt.setString(9, student.getSeniorHighSchoolCompletedAt().getCode());
			prepStmnt.setInt(10, student.getSeniorHighSchoolGraduatedYear());
			prepStmnt.setString(11, student.getVocationalSchoolCompletedAt().getCode());
			prepStmnt.setInt(12, student.getVocationalSchoolGraduatedYear());
			prepStmnt.setString(13, student.getCollegeSchoolCompletedAt().getCode());
			prepStmnt.setInt(14, student.getCollegeSchoolGraduatedYear());
			prepStmnt.setString(15, student.getGraduateSchoolCompletedAt().getCode());
			prepStmnt.setInt(16, student.getGraduateSchoolGraduatedYear());
			prepStmnt.setString(17, student.getSchoolLastAttendedAt().getCode());
			prepStmnt.setInt(18, student.getSchoolLastAttendedYear());
			prepStmnt.setString(19, student.getHighestEducationalAttainment());
			prepStmnt.setString(20, student.getHighestEducationalAttainmentMajor());
			prepStmnt.setString(21, student.getEntranceCredentials());
			prepStmnt.setString(22, student.getUpdatedBy());
			prepStmnt.setTimestamp(23, student.getUpdatedTimestamp());		
			prepStmnt.setInt(24, student.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public StudentDTO getStudentByCode(String code) {
		return (StudentDTO) getDTO(qryStudentByCode, code);
	}
	
	public List<DTOBase> getStudentListSearchByAcademicProgramCode(String searchValue) {
		return getDTOList(qryStudentListSearchByAcademicProgramCode, "%" + searchValue + "%");
	}
	
	public List<DTOBase> getStudentList() {
		return getDTOList(qryStudentList);
	}
	
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		StudentDTO student = new StudentDTO();
		student.setId((Integer) getDBVal(resultSet, "id"));
		student.setCode((String) getDBVal(resultSet, "user_code"));
		student.setLearnerReferenceNumber((String) getDBVal(resultSet, "learner_reference_number"));
		student.getAcademicProgram().setCode((String) getDBVal(resultSet, "academic_program_code"));
		student.setSkills((String) getDBVal(resultSet, "skills"));
		student.getElementarySchoolCompletedAt().setCode((String) getDBVal(resultSet, "elementary_school_code_completed_at"));
		student.setElementarySchoolGraduatedYear((Integer) getDBVal(resultSet, "elementary_school_graduated_year"));
		student.getJuniorHighSchoolCompletedAt().setCode((String)getDBVal(resultSet, "junior_high_school_code_completed_at"));
		student.setJuniorHighSchoolGraduatedYear((Integer) getDBVal(resultSet, "junior_high_school_graduated_year"));
		student.getSeniorHighSchoolCompletedAt().setCode((String)getDBVal(resultSet, "senior_high_school_code_completed_at"));
		student.setSeniorHighSchoolGraduatedYear((Integer) getDBVal(resultSet, "senior_high_school_graduated_year"));
		student.getVocationalSchoolCompletedAt().setCode((String)getDBVal(resultSet, "vocational_school_code_completed_at"));
		student.setVocationalSchoolGraduatedYear((Integer) getDBVal(resultSet, "vocational_school_graduated_year"));
		student.getCollegeSchoolCompletedAt().setCode((String)getDBVal(resultSet, "college_school_code_completed_at"));
		student.setCollegeSchoolGraduatedYear((Integer) getDBVal(resultSet, "college_school_graduated_year"));
		student.getGraduateSchoolCompletedAt().setCode((String)getDBVal(resultSet, "graduate_school_code_completed_at"));
		student.setGraduateSchoolGraduatedYear((Integer) getDBVal(resultSet, "graduate_school_graduated_year"));
		student.getSchoolLastAttendedAt().setCode((String)getDBVal(resultSet, "school_code_last_attended_at"));
		student.setSchoolLastAttendedYear((Integer) getDBVal(resultSet, "school_last_attended_year"));
		student.setHighestEducationalAttainment((String) getDBVal(resultSet, "highest_educational_attainment"));
		student.setHighestEducationalAttainmentMajor((String) getDBVal(resultSet, "highest_educational_attainment_major"));
		student.setEntranceCredentials((String) getDBVal(resultSet, "entrance_credentials"));
		student.setDisplayText(student.getName(true, false, true));
		return student;
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

	public StudentDTO getStudentByName(String lastName, String firstName, String middleName) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(lastName);
		paramList.add(firstName);
		paramList.add(middleName);
		return (StudentDTO) getDTO(qryStudentByName, paramList);
	}

}
