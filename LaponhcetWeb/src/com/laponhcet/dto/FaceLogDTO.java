package com.laponhcet.dto;

import java.sql.Timestamp;

import com.mytechnopal.Report;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DateTimeUtil;

public class FaceLogDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_FACELOG = "SESSION_FACELOG";
	public static final String SESSION_FACELOG_LIST = "SESSION_FACELOG_LIST";
	public static final String SESSION_FACELOG_PAGINATION = "SESSION_FACELOG_PAGINATION";
	
	public static final String[] PAGINATION_SEARCH_CRITERIA_LIST = new String[] {"Name", "ID Number"};
	public static final String[] PAGINATION_PERIOD_CRITERIA_LIST = new String[] {Report.REPORT_DATE_BY_DAY, Report.REPORT_DATE_FROM_TO, Report.REPORT_DATE_BY_MONTH};
	
	private UserRFIDDTO userRFID;
	private Timestamp timeLog;
	private String pict;
	private boolean isIn;
	private String location;
	private boolean isProcess;
	private MessageSMSDTO messageSMS;
	
	public FaceLogDTO() {
		super();
		this.userRFID = new UserRFIDDTO();
		this.timeLog = DateTimeUtil.getCurrentTimestamp();
		this.pict = "";
		this.isIn = false;
		this.location = "";
		this.isProcess = false;
		this.messageSMS = new MessageSMSDTO();
	}

	public FaceLogDTO getFaceLog() {
		FaceLogDTO faceLog = new FaceLogDTO();
		faceLog.setId(super.getId());
		faceLog.setCode(super.getCode());
		faceLog.setUserRFID(this.userRFID);
		faceLog.setTimeLog(this.timeLog);
		faceLog.setPict(this.pict);
		faceLog.setIn(this.isIn);
		faceLog.setLocation(this.location);
		faceLog.setProcess(this.isProcess);
		faceLog.setMessageSMS(this.messageSMS);
		return faceLog;
	}
	
	public Timestamp getTimeLog() {
		return timeLog;
	}

	public void setTimeLog(Timestamp timeLog) {
		this.timeLog = timeLog;
	}

	public boolean isIn() {
		return isIn;
	}

	public void setIn(boolean isIn) {
		this.isIn = isIn;
	}

	public String getPict() {
		return pict;
	}

	public void setPict(String pict) {
		this.pict = pict;
	}

	public UserRFIDDTO getUserRFID() {
		return userRFID;
	}

	public void setUserRFID(UserRFIDDTO userRFID) {
		this.userRFID = userRFID;
	}

	public boolean isProcess() {
		return isProcess;
	}

	public void setProcess(boolean isProcess) {
		this.isProcess = isProcess;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public MessageSMSDTO getMessageSMS() {
		return messageSMS;
	}

	public void setMessageSMS(MessageSMSDTO messageSMS) {
		this.messageSMS = messageSMS;
	}
}
