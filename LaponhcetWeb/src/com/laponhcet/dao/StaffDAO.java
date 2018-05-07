package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.StaffDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.LinkDAO;
import com.mytechnopal.dao.UpdateKeyDAO;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dao.UserGroupLinkDAO;
import com.mytechnopal.dto.UpdateKeyDTO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.dto.UserGroupDTO;
import com.mytechnopal.util.StringUtil;

public class StaffDAO extends DAOBase {
	private static final long serialVersionUID = 1L;
	
	private String qryStaffAdd = "STAFF_ADD";
	private String qryStaffUpdate = "STAFF_UPDATE";
	private String qryStaffDelete = "STAFF_DELETE";
	private String qryStaffLastCode = "STAFF_LAST_CODE";
	private String qryStaffByCode = "STAFF_BY_CODE";
	private String qryStaffList = "STAFF_LIST";
	
	@Override
	public void executeAdd(DTOBase obj) {
		StaffDTO staff = (StaffDTO) obj;
		staff.setUserGroupCodes(UserGroupDTO.USER_GROUP_STAFF_CODE);
		List<DTOBase> userGroupLinkListByUserGroup = new UserGroupLinkDAO().getUserGroupLinkListByUserGroupCode(UserGroupDTO.USER_GROUP_STAFF_CODE);
		List<DTOBase> linkList = new LinkDAO().getLinkList();
		
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		staff.setCode(getGeneratedCode());
		staff.setUserName(staff.getCode());
		staff.setPassword(StringUtil.getUniqueId(3, 3));
		staff.setActive(true);
		staff.setBaseDataOnInsert();
		
		//User
		new UserDAO().add(conn, prepStmntList, staff);
				
		//Staff
		add(conn, prepStmntList, staff);
		
		UpdateKeyDTO updateKey = new UpdateKeyDTO();
		updateKey.setUserCode(staff.getCode());
		updateKey.setCpNumber(staff.getPassword());
		new UpdateKeyDAO().add(conn, prepStmntList, updateKey);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
		
		//User RFID
		/*try {
			UserRFIDDAO UserRFIDDAO = new UserRFIDDAO();
			UserRFIDDAO.daoConnector.serverLocation = DAOConnector.SERVER_LOCATION_FACEKEEPER1;
			UserRFIDDTO userRFID = new UserRFIDDTO();
			UserRFIDUtil.setUserRFIDByUser(staff, userRFID);
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
		StaffDTO staff = getStaffLast();
		String code = "S001"; //default staff code
		if(staff != null) {
			int nextProfNum = Integer.parseInt(StringUtil.getRight(staff.getCode(), 3)) + 1; 
			code =  "S"+ StringUtil.getPadded(String.valueOf(nextProfNum), 3, "0", true);
		}
		return code;
	}
	
	private StaffDTO getStaffLast() {
		return (StaffDTO) getDTO(qryStaffLastCode);
	}
	
	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		StaffDTO staff = (StaffDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryStaffAdd));
			prepStmnt.setString(1, staff.getCode());
			prepStmnt.setString(2, staff.getProgramGraduated());
			prepStmnt.setString(3, staff.getJobRole());
			prepStmnt.setString(4, staff.getAssignedOffice());
			prepStmnt.setString(5, staff.getAddedBy());
			prepStmnt.setTimestamp(6, staff.getAddedTimestamp());
			prepStmnt.setString(7, staff.getUpdatedBy());
			prepStmnt.setTimestamp(8, staff.getUpdatedTimestamp());	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	@Override
	public void executeDelete(DTOBase obj) {
		StaffDTO staff = (StaffDTO) obj;
		//User
		UserDTO user = new UserDAO().getUserByCode(staff.getCode());
		
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		
		//delete staff
		delete(conn, prepStmntList, staff);
		//delete user
		new UserDAO().delete(conn, prepStmntList, user);
		//delete user link list
		//new UserLinkDAO().delete(conn, prepStmntList, user);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
		
		//User RFID
		/*try {
			UserRFIDDAO UserRFIDDAO = new UserRFIDDAO();
			UserRFIDDAO.daoConnector.serverLocation = DAOConnector.SERVER_LOCATION_FACEKEEPER1;
			UserRFIDDTO userRFID = new UserRFIDDTO();
			UserRFIDUtil.setUserRFIDByUser(staff, userRFID);
			UserRFIDDAO.executeDelete(userRFID);
			
			if(SettingsUtil.OWNER_CODE.equalsIgnoreCase(SettingsUtil.OWNER_CODE_BCC)) {
				UserRFIDDAO.daoConnector.serverLocation = DAOConnector.SERVER_LOCATION_FACEKEEPER2;
				UserRFIDDAO.executeDelete(userRFID);
			}
			UserRFIDDAO.daoConnector.serverLocation = DAOConnector.SERVER_LOCATION_LOCAL;
		}
		catch(Exception e) {
			e.printStackTrace();
		}*/
	}

	protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		StaffDTO staff = (StaffDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryStaffDelete));	
			prepStmnt.setInt(1, staff.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	@Override
	public void executeUpdate(DTOBase obj) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		StaffDTO staff = (StaffDTO) obj;
		staff.setBaseDataOnUpdate();
		
		//User
		new UserDAO().update (conn, prepStmntList, staff);
		
		//Staff
		update(conn, prepStmntList, staff);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
		
		//User RFID
		/*try {
			UserRFIDDAO UserRFIDDAO = new UserRFIDDAO();
			UserRFIDDAO.daoConnector.serverLocation = DAOConnector.SERVER_LOCATION_FACEKEEPER1;
			UserRFIDDTO userRFID = new UserRFIDDTO();
			UserRFIDUtil.setUserRFIDByUser(staff, userRFID);
			UserRFIDDAO.executeUpdate(userRFID);
			
			if(SettingsUtil.OWNER_CODE.equalsIgnoreCase(SettingsUtil.OWNER_CODE_BCC)) {
				UserRFIDDAO.daoConnector.serverLocation = DAOConnector.SERVER_LOCATION_FACEKEEPER2;
				UserRFIDDAO.executeUpdate(userRFID);
			}
			UserRFIDDAO.daoConnector.serverLocation = DAOConnector.SERVER_LOCATION_LOCAL;
		}
		catch(Exception e) {
			e.printStackTrace();
		}*/
	}

	protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		StaffDTO staff = (StaffDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryStaffUpdate));
			prepStmnt.setString(1, staff.getProgramGraduated());
			prepStmnt.setString(2, staff.getJobRole());
			prepStmnt.setString(3, staff.getAssignedOffice());
			prepStmnt.setString(4, staff.getUpdatedBy());
			prepStmnt.setTimestamp(5, staff.getUpdatedTimestamp());		
			prepStmnt.setInt(6, staff.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public StaffDTO getStaffByCode(String code) {
		return (StaffDTO) getDTO(qryStaffByCode, code);
	}
	
	public List<DTOBase> getStaffList() {
		return getDTOList(qryStaffList);
	}
	
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		StaffDTO staff = new StaffDTO();
		staff.setId((Integer) getDBVal(resultSet, "id"));
		staff.setCode((String) getDBVal(resultSet, "code"));
		staff.setProgramGraduated((String) getDBVal(resultSet, "program_graduated"));
		staff.setJobRole((String) getDBVal(resultSet, "job_role"));
		staff.setAssignedOffice((String) getDBVal(resultSet, "assigned_office"));
		staff.setAcademicProgramCodes((String) getDBVal(resultSet, "program_codes"));
		staff.setDisplayText(staff.getName(false, true, true));
		return staff;
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
}
