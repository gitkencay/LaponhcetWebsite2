package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.laponhcet.dto.UserRFIDDTO;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class UserRFIDDAO extends DAOBase {
	private static final long serialVersionUID = 1L;
	
	private String qryUserRFIDAdd = "USER_RFID_ADD";
	private String qryUserRFIDUpdate = "USER_RFID_UPDATE";
	private String qryUserRFIDDelete = "USER_RFID_DELETE";
	
	private String qryUserRFIDByRFId = "USER_RFID_BY_RFID";
	private String qryUserRFIDList = "USER_RFID_LIST";
	
	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		UserRFIDDTO userRFID = (UserRFIDDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryUserRFIDAdd));
			prepStmnt.setString(1, userRFID.getCode());
			prepStmnt.setString(2, userRFID.getProfilePict());
			prepStmnt.setString(3, userRFID.getRfid());
			prepStmnt.setString(4, userRFID.getLastName());
			prepStmnt.setString(5, userRFID.getFirstName());
			prepStmnt.setString(6, userRFID.getMiddleName());
			prepStmnt.setString(7, userRFID.getPrefixName());
			prepStmnt.setString(8, userRFID.getSuffixName());
			prepStmnt.setString(9, userRFID.getOtherTitle());
			prepStmnt.setString(10, userRFID.getGender());
			prepStmnt.setString(11, userRFID.getCpNumber());
			prepStmnt.setString(12, userRFID.getContactCPNumber());
			prepStmnt.setString(13, userRFID.getAddedBy());
			prepStmnt.setTimestamp(14, userRFID.getAddedTimestamp());
			prepStmnt.setString(15, userRFID.getUpdatedBy());
			prepStmnt.setTimestamp(16, userRFID.getUpdatedTimestamp());	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		UserRFIDDTO userRFID = (UserRFIDDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryUserRFIDUpdate));
			prepStmnt.setString(1, userRFID.getProfilePict());
			prepStmnt.setString(2, userRFID.getRfid());
			prepStmnt.setString(3, userRFID.getLastName());
			prepStmnt.setString(4, userRFID.getFirstName());
			prepStmnt.setString(5, userRFID.getMiddleName());
			prepStmnt.setString(6, userRFID.getPrefixName());
			prepStmnt.setString(7, userRFID.getSuffixName());
			prepStmnt.setString(8, userRFID.getOtherTitle());
			prepStmnt.setString(9, userRFID.getGender());
			prepStmnt.setString(10, userRFID.getCpNumber());
			prepStmnt.setString(11, userRFID.getContactCPNumber());
			prepStmnt.setString(12, userRFID.getUpdatedBy());
			prepStmnt.setTimestamp(13, userRFID.getUpdatedTimestamp());	
			prepStmnt.setString(14, userRFID.getCode());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		UserRFIDDTO userRFID = (UserRFIDDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryUserRFIDDelete));
			prepStmnt.setString(1, userRFID.getCode());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
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
	
	public List<DTOBase> getUserRFIDList() {
		return getDTOList(qryUserRFIDList);
	}

	public UserRFIDDTO getUserRFIDByRFID(String rfid) {
		return (UserRFIDDTO) getDTO(qryUserRFIDByRFId, rfid);
	}
	
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		UserRFIDDTO userRFID = new UserRFIDDTO();
		userRFID.setId((Integer) getDBVal(resultSet, "id"));
		userRFID.setCode((String) getDBVal(resultSet, "user_code"));
		userRFID.setProfilePict((String) getDBVal(resultSet, "profile_pict"));
		userRFID.setRfid((String) getDBVal(resultSet, "rfid"));
		userRFID.setLastName((String) getDBVal(resultSet, "last_name"));
		userRFID.setFirstName((String) getDBVal(resultSet, "first_name"));
		userRFID.setMiddleName((String) getDBVal(resultSet, "middle_name"));
		userRFID.setPrefixName((String) getDBVal(resultSet, "prefix_name"));
		userRFID.setSuffixName((String) getDBVal(resultSet, "suffix_name"));
		userRFID.setOtherTitle((String) getDBVal(resultSet, "other_title"));
		userRFID.setGender((String) getDBVal(resultSet, "gender"));
		userRFID.setCpNumber((String) getDBVal(resultSet, "cp_number"));		
		userRFID.setContactCPNumber((String) getDBVal(resultSet, "contact_cp_number"));
		return userRFID;
	}

}
