package com.laponhcet.dto;

import java.util.Date;
import java.util.List;

import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DateTimeUtil;

public class MessageDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_MESSAGE = "SESSION_MESSAGE";
	public static final String SESSION_MESSAGE_LIST = "SESSION_MESSAGE_LIST";
	public static final String SESSION_MESSAGE_PAGINATION = "SESSION_MESSAGE_PAGINATION";
	
	public static final String[] PRIORITY_LIST = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
	
	public static final String[] SCHOOL_MESSAGE_TYPE_LIST = new String[] {"INDIVIDUAL", "ACADEMIC PROGRAM GROUP", "ACADEMIC PROGRAM SUBGROUP", "COURSE"};
	public static final String[] SCHOOL_MESSAGE_TYPE_CODE_LIST = new String[] {"IND", "APG", "APS", "CRS"};
	public static final String[] SCHOOL_MESSAGE_MEDIUM_LIST = new String[] {"FACEKEEPER", "SMS", "LETTER", "WEB-HEADLINE", "CALENDAR-HEADLINE", "CALENDAR-ACADEMIC PROGRAM GROUP", "CALENDAR-ACADEMIC PROGRAM SUBGROUP", "CALENDAR-COURSE", "CALENDAR-INDIVIDUAL"};
	public static final String[] SCHOOL_MESSAGE_MEDIUM_CODE_LIST = new String[] {"FK", "SMS", "LET", "WHL", "CHL", "CAPG", "CAPSG", "CCRS", "CIND"};
	
	public static final String[] SCHOOL_MESSAGE_TYPE_INDIVIDUAL_MEDIUM_CODE_LIST = {"SMS", "LET", "CIND"};
	public static final String[] SCHOOL_MESSAGE_TYPE_ACADEMIC_PROGRAM_GROUP_MEDIUM_CODE_LIST = {"FK", "SMS", "WHL", "CAPG"};
	public static final String[] SCHOOL_MESSAGE_TYPE_ACADEMIC_PROGRAM_SUBGROUP_MEDIUM_CODE_LIST = {"FK", "SMS", "WHL", "CAPSG"};
	public static final String[] SCHOOL_MESSAGE_TYPE_COURSE_MEDIUM_CODE_LIST = {"FK", "SMS", "WHL", "CCRS"};
	
	public static final String MESSAGE_SOURCE_WEB = "WEB";
	public static final String MESSAGE_SOURCE_SMS = "SMS";
	public static final String MESSAGE_SOURCE_FACEKEEPER = "FACEKEEPER";
	
	private String content;
	private int priority;
	private Date validTimestampStart;
	private Date validTimestampEnd;
	private String messageTypeCodes;
	private String contentSMS;
	private String contentWebHeadline;
	private String contentFaceKeeper;
	private String source;
	private List<DTOBase> recipientList;

	//Add specific obj list here
	
	public MessageDTO() {
		super();
		this.content = "";
		this.priority = 1;
		this.validTimestampStart = DateTimeUtil.getStrToDateTime(DateTimeUtil.getDateTimeToStr(DateTimeUtil.getCurrentTimestamp(), "yyyy-MM-dd"), "yyyy-MM-dd");
		this.validTimestampEnd = DateTimeUtil.getStrToDateTime("9999-12-31", "yyyy-MM-dd");
		this.messageTypeCodes = "";
		this.contentSMS = "";
		this.contentWebHeadline = "";
		this.contentFaceKeeper = "";
		this.source = MESSAGE_SOURCE_WEB;
	}
	
	public MessageDTO getMessage() {
		MessageDTO message = new MessageDTO();
		message.setId(super.getId());
		message.setCode(super.getCode());
		message.setContent(this.content);
		message.setPriority(this.priority);
		message.setValidTimestampStart(this.validTimestampStart);
		message.setValidTimestampEnd(this.validTimestampEnd);
		message.setMessageTypeCodes(this.messageTypeCodes);
		message.setContentSMS(this.contentSMS);
		message.setContentWebHeadline(this.contentWebHeadline);
		message.setContentFaceKeeper(this.contentFaceKeeper);
		message.setSource(this.source);
		return message;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getValidTimestampStart() {
		return validTimestampStart;
	}

	public void setValidTimestampStart(Date validTimestampStart) {
		this.validTimestampStart = validTimestampStart;
	}

	public Date getValidTimestampEnd() {
		return validTimestampEnd;
	}

	public void setValidTimestampEnd(Date validTimestampEnd) {
		this.validTimestampEnd = validTimestampEnd;
	}

	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String getMessageTypeCodes() {
		return messageTypeCodes;
	}
	
	public void setMessageTypeCodes(String messageTypeCodes) {
		this.messageTypeCodes = messageTypeCodes;
	}

	public String getContentSMS() {
		return contentSMS;
	}

	public void setContentSMS(String contentSMS) {
		this.contentSMS = contentSMS;
	}

	public String getContentWebHeadline() {
		return contentWebHeadline;
	}

	public void setContentWebHeadline(String contentWebHeadline) {
		this.contentWebHeadline = contentWebHeadline;
	}

	public String getContentFaceKeeper() {
		return contentFaceKeeper;
	}

	public void setContentFaceKeeper(String contentFaceKeeper) {
		this.contentFaceKeeper = contentFaceKeeper;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<DTOBase> getRecipientList() {
		return recipientList;
	}

	public void setRecipientList(List<DTOBase> recipientList) {
		this.recipientList = recipientList;
	}
}
