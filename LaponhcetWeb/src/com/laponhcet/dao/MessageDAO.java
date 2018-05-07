package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.laponhcet.dto.MessageAcademicProgramGroupDTO;
import com.laponhcet.dto.MessageAcademicProgramSubgroupDTO;
import com.laponhcet.dto.MessageCourseDTO;
import com.laponhcet.dto.MessageDTO;
import com.laponhcet.dto.MessageIndividualDTO;
import com.laponhcet.dto.MessageSMSDTO;
import com.laponhcet.util.MessageUtil;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;

public class MessageDAO extends DAOBase {
	private static final long serialVersionUID = 1L;
	
	private String qryMessageAdd = "MESSAGE_ADD";
	private String qryMessageUpdate = "MESSAGE_UPDATE";
	private String qryMessageDelete = "MESSAGE_DELETE";
	private String qryMessageByCode = "MESSAGE_BY_CODE";
	private String qryMessageList = "MESSAGE_LIST";
	private String qryMessageListSearchByType = "MESSAGE_LIST_SEARCHBY_TYPE";
	private String qryMessageListSearchByContent = "MESSAGE_LIST_SEARCHBY_CONTENT";
	private String qryMessageByMessageTypeCodeContent = "MESSAGE_BY_MESSAGETYPECODECONTENT";
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void executeAdd(DTOBase obj) {
		MessageDTO message = (MessageDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		String[] messageTypeCodes = message.getMessageTypeCodes().split(Pattern.quote("|"));
		message.setCode(getGeneratedCode());
		message.setMessageTypeCodes(messageTypeCodes[2]);
		message.setBaseDataOnInsert();
		add(conn, prepStmntList, message);
		
		if(message.getRecipientList().size()>0){
			List<DTOBase> userList = new UserDAO().getUserList();
			for(DTOBase userObj: message.getRecipientList()){
				UserDTO user = (UserDTO) DTOUtil.getObjByCode(userList, userObj.getCode());
				if(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[0].equalsIgnoreCase(messageTypeCodes[0])){
					sendMessageIndividual(message, user);
				}else if(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[1].equalsIgnoreCase(messageTypeCodes[0])){
					sendMessageAcademicProgramGroup(message, messageTypeCodes);
				}else if(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[2].equalsIgnoreCase(messageTypeCodes[0])){
					sendMessageAcademicProgramSubgroup(message, messageTypeCodes);
				}else if(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[3].equalsIgnoreCase(messageTypeCodes[0])){
					sendMessageCourse(message, messageTypeCodes);
				}
				
				if(StringUtil.isStrExistInStrArr(messageTypeCodes[2].split("~"), MessageDTO.SCHOOL_MESSAGE_MEDIUM_CODE_LIST[1])){
					sendMessageSMS(message, user);
				}
			}
		}
		message.setMessageTypeCodes(String.join("|", messageTypeCodes));
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
		
	}
	
	protected void sendMessageIndividual(MessageDTO message, UserDTO user){
		MessageIndividualDTO messageIndividual = new MessageIndividualDTO();
		messageIndividual.setMessageCode(message.getCode());
		messageIndividual.setUser(user);
		new MessageIndividualDAO().executeAdd(messageIndividual);
	}
	
	protected void sendMessageAcademicProgramGroup(MessageDTO message, String[] messageTypeCodes){
		MessageAcademicProgramGroupDTO messageAcademicProgramGroup = new MessageAcademicProgramGroupDTO();
		messageAcademicProgramGroup.setMessage(message);
		messageAcademicProgramGroup.setAcademicProgramGroupCodes(messageTypeCodes[1]);
		new MessageAcademicProgramGroupDAO().executeAdd(messageAcademicProgramGroup);
	}
	
	protected void sendMessageAcademicProgramSubgroup(MessageDTO message, String[] messageTypeCodes){
		MessageAcademicProgramSubgroupDTO messageAcademicProgramSubgroup = new MessageAcademicProgramSubgroupDTO();
		messageAcademicProgramSubgroup.setMessage(message);
		messageAcademicProgramSubgroup.setAcademicProgramSubgroupCodes(messageTypeCodes[1]);
		new MessageAcademicProgramSubgroupDAO().executeAdd(messageAcademicProgramSubgroup);
	}
	
	protected void sendMessageCourse(MessageDTO message, String[] messageTypeCodes){
		MessageCourseDTO messageCourse = new MessageCourseDTO();
		messageCourse.setMessage(message);
		messageCourse.setCourseCode(messageTypeCodes[1]);
		new MessageCourseDAO().executeAdd(messageCourse);
	}
	
	protected void sendMessageSMS(MessageDTO message, UserDTO user){
		MessageSMSDTO messageSMS = new MessageSMSDTO();
		messageSMS.setMessageCode(message.getCode());
		messageSMS.setMessage(message.getContentSMS());
		messageSMS.setCpNumber(user.getCpNumber());
		messageSMS.setPriority(message.getPriority());
		new MessageSMSDAO().executeAdd(messageSMS);
	}
	
	public String getGeneratedCode(){
		return DateTimeUtil.getDateTimeToStr(DateTimeUtil.getCurrentTimestamp(), "YYYYMMddKKmmss") + StringUtil.getUniqueId(0, 2);
	}
	

	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		MessageDTO message = (MessageDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryMessageAdd));
			prepStmnt.setString(1, message.getCode());
			prepStmnt.setString(2, message.getContent());
			prepStmnt.setInt(3, message.getPriority());
			prepStmnt.setString(4, DateTimeUtil.getDateTimeToStr(message.getValidTimestampStart(), "YYYY-MM-dd KK-mm-ss"));
			prepStmnt.setString(5, DateTimeUtil.getDateTimeToStr(message.getValidTimestampEnd(), "YYYY-MM-dd KK-mm-ss"));
			prepStmnt.setString(6, message.getMessageTypeCodes());
			prepStmnt.setString(7, message.getContentSMS());
			prepStmnt.setString(8, message.getContentWebHeadline());
			prepStmnt.setString(9, message.getContentFaceKeeper());
			prepStmnt.setString(10, message.getSource());
			prepStmnt.setString(11, message.getAddedBy());
			prepStmnt.setTimestamp(12, message.getAddedTimestamp());
			prepStmnt.setString(13, message.getUpdatedBy());
			prepStmnt.setTimestamp(14, message.getUpdatedTimestamp());	
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
	public void executeDelete(DTOBase obj) {
		MessageDTO message = (MessageDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		delete(conn, prepStmntList, message);
	}

	@SuppressWarnings("unchecked")
	protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		MessageDTO message = (MessageDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryMessageDelete));	
			prepStmnt.setInt(1, message.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	@Override
	public void executeDeleteList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public void executeUpdate(DTOBase obj) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		MessageDTO message = (MessageDTO) obj;
		message.setBaseDataOnUpdate();
		update(conn, prepStmntList, message);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		MessageDTO message = (MessageDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryMessageUpdate));
			prepStmnt.setString(1, message.getCode());
			prepStmnt.setString(2, message.getContent());
			prepStmnt.setInt(3, message.getPriority());
			prepStmnt.setString(4, DateTimeUtil.getDateTimeToStr(message.getValidTimestampStart(), "yyyy-MM-dd"));
			prepStmnt.setString(5, DateTimeUtil.getDateTimeToStr(message.getValidTimestampEnd(), "yyyy-MM-dd"));
			prepStmnt.setString(6, message.getMessageTypeCodes());
			prepStmnt.setString(7, message.getContentSMS());
			prepStmnt.setString(8, message.getContentWebHeadline());
			prepStmnt.setString(9, message.getContentFaceKeeper());
			prepStmnt.setString(10, message.getSource());
			prepStmnt.setString(11, message.getUpdatedBy());
			prepStmnt.setTimestamp(12, message.getUpdatedTimestamp());	
			prepStmnt.setInt(13, message.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public MessageDTO getMessageByCode(String code) {
		return (MessageDTO) getDTO(qryMessageByCode, code);
	}
	
	
	public List<DTOBase> getMessageList() {
		return getDTOList(qryMessageList);
	}
	
	public List<DTOBase> getMessageListSearchByType(String searchValue) {
		return getDTOList(qryMessageListSearchByType, "%" + searchValue + "%");
	}
	
	public List<DTOBase> getMessageListSearchByContent(String searchValue) {
		return getDTOList(qryMessageListSearchByContent, "%" + searchValue + "%");
	}

	public MessageDTO getMessageByMessageTypeCodeContent(MessageDTO message) {
		List<String> paramList = new ArrayList<String>();
		paramList.add(message.getMessageTypeCodes());
		paramList.add(message.getContent());
		return (MessageDTO) getDTO(qryMessageByMessageTypeCodeContent, paramList);
	}
	
	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		MessageDTO message = new MessageDTO();
		message.setId((Integer) getDBVal(resultSet, "id"));
		message.setCode((String) getDBVal(resultSet, "code"));
		message.setContent((String) getDBVal(resultSet, "content"));
		message.setPriority((Integer) (getDBVal(resultSet, "priority")));
		message.setValidTimestampStart((Timestamp) getDBVal(resultSet, "valid_timestamp_start"));
		message.setValidTimestampEnd((Timestamp) getDBVal(resultSet, "valid_timestamp_end"));
		message.setMessageTypeCodes((String) getDBVal(resultSet, "message_type_codes"));
		message.setContentSMS((String) getDBVal(resultSet, "content_sms"));
		message.setContentWebHeadline((String) getDBVal(resultSet, "content_web_headline"));
		message.setContentFaceKeeper((String) getDBVal(resultSet, "content_face_keeper"));
		message.setSource((String) getDBVal(resultSet, "source"));
		message.setAddedBy((String) getDBVal(resultSet, "added_by"));
		message.setAddedTimestamp((Timestamp) getDBVal(resultSet, "added_timestamp"));
		message.setUpdatedBy((String) getDBVal(resultSet, "updated_by"));
		message.setAddedTimestamp((Timestamp) getDBVal(resultSet, "updated_timestamp"));
		message.setDisplayText(StringUtil.getShortDesc(message.getContent(), 20));
		return message;
	}

}