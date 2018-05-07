package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.laponhcet.dto.RegisterDTO;
import com.laponhcet.util.RegisterUtil;
import com.laponhcet.util.UserRFIDUtil;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.MediaDAO;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.dto.UserGroupDTO;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;

public class RegisterDAO extends DAOBase {
	private static final long serialVersionUID = 1L;
	
	private String qryRegisterAdd = "REGISTER_ADD";
	private String qryRegisterUpdate = "REGISTER_UPDATE";
	private String qryRegisterUpdateStatus = "REGISTER_UPDATE_STATUS";
	private String qryRegisterDelete = "REGISTER_DELETE";
	private String qryRegisterByName = "REGISTER_BY_NAME";
	private String qryRegisterList = "REGISTER_LIST";
	private String qryRegisterListSearchByName = "REGISTER_LIST_SEARCHBY_NAME";
	private String qryRegisterListSearchByInstitution = "REGISTER_LIST_SEARCHBY_INSTITUTION";
	private String qryRegisterLast = "REGISTER_LAST";
	
	
	@Override
	public void executeAdd(DTOBase obj) {
		RegisterDTO register = (RegisterDTO) obj;	
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		register.setCode(getGeneratedCode());
		register.setBaseDataOnInsert();
		
		/*if(!userObj.getUserGroup().getCode().equalsIgnoreCase(UserGroupDTO.USER_GROUP_GUEST_CODE)){
			register.setStatus(RegisterDTO.REGISTER_STATUS_APPROVED);
			register.getUserGroup().setCode(UserGroupDTO.USER_GROUP_MEMBER_CODE);
			register.setUserName(getSequentialGeneratedCode(registerLast, "M", "00001"));
			register.setPassword(StringUtil.getUniqueId(3, 3));
			UserDTO user = RegisterUtil.getUserByRegister(register);
			userDAO.add(conn, prepStmntList, user);
		}*/
		
		register.setUserGroupCodes(UserGroupDTO.USER_GROUP_MEMBER_CODE);	
		
		//Register
		add(conn, prepStmntList, register);
		
		//Media
		register.setProfilePictInfo("REGISTER", "PROFILE_PICT");
		new MediaDAO().add(conn, prepStmntList, register.getProfilePict());
		
		//User RFID
		new UserRFIDDAO().add(conn, prepStmntList, UserRFIDUtil.getUserRFIDByUser(register));
		
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	public String getGeneratedCode() {
		RegisterDTO register = (RegisterDTO) getDTO(qryRegisterLast);
		String code = "M00001";
		if(register != null) {
			code = "M" + StringUtil.getPadded(String.valueOf(Integer.parseInt(StringUtil.getRight(register.getCode(), 5))+1), 5, "0", true);
		}
		return code;
	}
	
	public void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		RegisterDTO register = (RegisterDTO) obj;	
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryRegisterAdd));
			prepStmnt.setString(1, register.getCode());
			prepStmnt.setString(2, register.getRfid());
			prepStmnt.setString(3, register.getGender());
			prepStmnt.setString(4, DateTimeUtil.getDateTimeToStr(register.getBirthDate(), "yyyy-MM-dd"));
			prepStmnt.setString(5, register.getCpNumber());
			prepStmnt.setString(6, register.getEmailAddress());
			prepStmnt.setString(7, register.getPrefixName());
			prepStmnt.setString(8, register.getLastName());
			prepStmnt.setString(9, register.getFirstName());
			prepStmnt.setString(10, register.getMiddleName());
			prepStmnt.setString(11, register.getSuffixName());
			prepStmnt.setString(12, register.getInstitutionConnectedWith());
			prepStmnt.setString(13, register.getOccupation());
			prepStmnt.setString(14, register.getStatus());
			prepStmnt.setString(15, register.getAddedBy());
			prepStmnt.setTimestamp(16, register.getAddedTimestamp());
			prepStmnt.setString(17, register.getUpdatedBy());
			prepStmnt.setTimestamp(18, register.getUpdatedTimestamp());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	
	
	public RegisterDTO getRegisterByName(String lastName, String firstName, String middleName){
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(lastName);
		paramList.add(firstName );
		paramList.add(middleName);
		return (RegisterDTO) getDTO(qryRegisterByName, paramList);
	}
	
	public List<DTOBase> getRegisterListSearchByName(String name){
		ArrayList<Object> param = new ArrayList<Object>();
		param.add("%" + name + "%");
		param.add("%" + name + "%");
		param.add("%" + name + "%");
		return  getDTOList(qryRegisterListSearchByName, param);
	}
	
	public List<DTOBase> getRegisterListSearchByInstitution(String institution){
		return  getDTOList(qryRegisterListSearchByInstitution, "%" + institution + "%");
	}
	
	
	
	@Override
	protected String getSequentialGeneratedCode(DTOBase lastObj, String startingCode) {
		// TODO Auto-generated method stub
		return super.getSequentialGeneratedCode(lastObj, startingCode);
	}
	
	

	@Override
	public void executeAddList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeDelete(DTOBase obj) {
		RegisterDTO register = (RegisterDTO) obj;	
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		delete(conn, prepStmntList, register);
		
		UserDTO user = new UserDAO().getUserByCode(register.getCode());
		if(user!=null){
			new UserDAO().delete(conn, prepStmntList, register);
		}
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	public void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		RegisterDTO register = (RegisterDTO) obj;	
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryRegisterDelete));
			prepStmnt.setInt(1, register.getId());
		} catch (SQLException e) {
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
		RegisterDTO register = (RegisterDTO) obj;	
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		register.setBaseDataOnUpdate();
		update(conn, prepStmntList, register);
		UserDTO user = RegisterUtil.getUserByRegister(register);
		new UserDAO().update(conn, prepStmntList, user);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList, true));
	}
	
	public void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		RegisterDTO register = (RegisterDTO) obj;	
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryRegisterUpdate));
			prepStmnt.setString(1, register.getRfid());
			prepStmnt.setString(2, register.getGender());
			prepStmnt.setString(3, DateTimeUtil.getDateTimeToStr(register.getBirthDate(), "yyyy-MM-dd"));
			prepStmnt.setString(4, register.getCpNumber());
			prepStmnt.setString(5, register.getEmailAddress());
			prepStmnt.setString(6, register.getPrefixName());
			prepStmnt.setString(7, register.getLastName());
			prepStmnt.setString(8, register.getFirstName());
			prepStmnt.setString(9, register.getMiddleName());
			prepStmnt.setString(10, register.getSuffixName());
			prepStmnt.setString(11, register.getInstitutionConnectedWith());
			prepStmnt.setString(12, register.getOccupation());
			prepStmnt.setString(13, register.getStatus());
			prepStmnt.setString(14, register.getUpdatedBy());
			prepStmnt.setTimestamp(15, register.getUpdatedTimestamp());
			prepStmnt.setInt(16, register.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public void executeUpdateStatus(DTOBase obj){
		RegisterDTO register = (RegisterDTO) obj;	
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		register.setBaseDataOnUpdate();
		updateStatus(conn, prepStmntList, register);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList, true));
	}
	
	public void updateStatus(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		RegisterDTO register = (RegisterDTO) obj;	
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryRegisterUpdateStatus));
			prepStmnt.setString(1, register.getStatus());
			prepStmnt.setString(2, register.getUpdatedBy());
			prepStmnt.setTimestamp(3, register.getUpdatedTimestamp());
			prepStmnt.setInt(4, register.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}

	public List<DTOBase> getRegisterList() {
		return getDTOList(qryRegisterList);
	}
	
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		RegisterDTO register = new RegisterDTO();
		register.setId((Integer) getDBVal(resultSet, "id"));
		register.setCode((String) getDBVal(resultSet, "user_code"));
		register.getProfilePict().setPict((String) getDBVal(resultSet, "pict"));
		register.setRfid((String) getDBVal(resultSet, "rfid"));
		register.setGender((String)getDBVal(resultSet, "gender"));
		register.setBirthDate((Date) getDBVal(resultSet, "birth_date"));
		register.setCpNumber((String)getDBVal(resultSet, "cp_number"));
		register.setEmailAddress((String) getDBVal(resultSet, "email_address"));
		register.setPrefixName((String) getDBVal(resultSet, "prefix_name"));
		register.setLastName((String) getDBVal(resultSet, "last_name"));
		register.setFirstName((String) getDBVal(resultSet, "first_name"));
		register.setMiddleName((String) getDBVal(resultSet, "middle_name"));
		register.setSuffixName((String) getDBVal(resultSet, "suffix_name"));
		register.setInstitutionConnectedWith((String) getDBVal(resultSet, "institution_connected_with"));
		register.setOccupation((String) getDBVal(resultSet, "occupation"));
		register.setStatus((String) getDBVal(resultSet, "status"));
		return register;
	}
}
