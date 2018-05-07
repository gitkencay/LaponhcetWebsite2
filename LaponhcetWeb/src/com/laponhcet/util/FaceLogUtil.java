package com.laponhcet.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.laponhcet.dto.FaceLogDTO;
import com.laponhcet.dto.UserRFIDDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.Report;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;
import com.mytechnopal.util.WebUtil;

public class FaceLogUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public static UserRFIDDTO getUserRFIDByRFID(List<DTOBase> userRFIDList, String rfid) {
		for(DTOBase userRFIDObj: userRFIDList) {
			UserRFIDDTO userRFID = (UserRFIDDTO) userRFIDObj;
			if(userRFID.getRfid().equalsIgnoreCase(rfid)) {
				return userRFID;
			}
		}
		return null;
	}
	
	public static List<DTOBase> getPresentUserRFIDList(List<DTOBase> userRFIDList, List<DTOBase> faceLogList) {
		List<DTOBase> resultList = new ArrayList<DTOBase>();
		for(DTOBase userRFIDObj: userRFIDList) {
			UserRFIDDTO userRFID = (UserRFIDDTO) userRFIDObj;
			if(getUserRFIDByRFID(resultList, userRFID.getRfid()) == null) {
				for(DTOBase faceLogObj: faceLogList){
					FaceLogDTO faceLog = (FaceLogDTO) faceLogObj;
					if(faceLog.getUserRFID().getRfid().equalsIgnoreCase(userRFID.getRfid())) {
						resultList.add(userRFID);
						break;
					}
				}
			}
		}
		return resultList;
	}
	
	private static List<DTOBase> getFaceLogListByRFID(List<DTOBase> faceLogList, String rfid) {
		List<DTOBase> resultList = new ArrayList<DTOBase>();
		for(DTOBase faceLogObj: faceLogList){
			FaceLogDTO faceLog = (FaceLogDTO) faceLogObj;
			if(faceLog.getUserRFID().getRfid().equalsIgnoreCase(rfid)) {
				resultList.add(faceLog);
			}
		}
		return resultList;
	}
	
	private static List<DTOBase> getFaceLogListAM(List<DTOBase> faceLogList) {
		Date timeAMStart = DateTimeUtil.getStrToDateTime("1970-01-01 00:00", "yyyy-MM-dd kk:mm");
		Date timeAMEnd = DateTimeUtil.getStrToDateTime("1970-01-01 11:59", "yyyy-MM-dd kk:mm");
		List<DTOBase> resultList = new ArrayList<DTOBase>();
		for(DTOBase faceLogObj: faceLogList){
			FaceLogDTO faceLog = (FaceLogDTO) faceLogObj;
			if(DateTimeUtil.isTimeWithin(faceLog.getTimeLog(), timeAMStart, timeAMEnd)) {
				resultList.add(faceLog);
			}
		}
		return resultList;
	}
	
	private static List<DTOBase> getFaceLogListPM(List<DTOBase> faceLogList) {
		Date timeAMStart = DateTimeUtil.getStrToDateTime("1970-01-01 12:00", "yyyy-MM-dd kk:mm");
		Date timeAMEnd = DateTimeUtil.getStrToDateTime("1970-01-01 23:59", "yyyy-MM-dd kk:mm");
		List<DTOBase> resultList = new ArrayList<DTOBase>();
		for(DTOBase faceLogObj: faceLogList){
			FaceLogDTO faceLog = (FaceLogDTO) faceLogObj;
			if(DateTimeUtil.isTimeWithin(faceLog.getTimeLog(), timeAMStart, timeAMEnd)) {
				resultList.add(faceLog);
			}
		}
		return resultList;
	}
	
	private static List<DTOBase> getFaceLogListByDate(List<DTOBase> faceLogList, Date date) {
		List<DTOBase> resultList = new ArrayList<DTOBase>();
		for(DTOBase faceLogObj: faceLogList){
			FaceLogDTO faceLog = (FaceLogDTO) faceLogObj;
			if(DateTimeUtil.isDateEqual(faceLog.getTimeLog(), date)) {
				resultList.add(faceLog);
			}
		}
		return resultList;
	}
	
	private static String[] getFaceLogListStrArr(List<DTOBase> faceLogList) {
		String[] strArr = new String[faceLogList.size()];
		int ctrEven = 0;
		int ctrOdd = 1;
		for(int i=0; i<faceLogList.size();i++) {
			FaceLogDTO faceLog = (FaceLogDTO) faceLogList.get(i);
			if(faceLog.isIn()) {
				strArr[ctrEven] = "<small>" + DateTimeUtil.getDateTimeToStr(faceLog.getTimeLog(), "hh:mm") + "</small>";
				ctrEven = ctrEven +2;
			}
			else {
				strArr[ctrOdd] = "<small>" + DateTimeUtil.getDateTimeToStr(faceLog.getTimeLog(), "hh:mm") + "</small>";
				ctrOdd = ctrOdd +2;
			}
		}
		return strArr;
	}
	
	public static void setPaginationRecord(Pagination pagination, List<DTOBase> faceLogList) {
		for(DTOBase dtoObj: pagination.getCurrentPageRecordList()) {
			UserRFIDDTO userRFID = (UserRFIDDTO) dtoObj;
			String paginationContent = "";
			paginationContent = getPaginationRecord(userRFID, getFaceLogListByRFID(faceLogList, userRFID.getRfid()));
			userRFID.setPaginationRecord(new String[]{paginationContent});
		}
	}
	
	
	private static String getPaginationRecord(UserRFIDDTO userRFID, List<DTOBase> faceLogList) {
		StringBuffer str = new StringBuffer();
		str.append("    <div class=\"contact-box\">\n");
		str.append("        <div class=\"col-sm-4\">\n");
		str.append("            <div class=\"text-center\">\n");
		str.append("               <img alt=\"image\" class=\"m-t-xs img-responsive\" src=\" " + userRFID.getProfilePict() + "\">\n");
		str.append("            </div>\n");
		str.append("        </div>\n");
		str.append("        <div class=\"col-sm-8\">\n");
		str.append("            <h3><strong>" + StringUtil.getFullName(userRFID.getLastName(), userRFID.getFirstName(), userRFID.getMiddleName(), userRFID.getSuffixName(), false, true) + "</strong></h3>\n");
		str.append("            <p><i class=\"fa fa-id-card-o\"></i> &nbsp;" + userRFID.getCode() + "<br>\n");
		str.append("        </div>\n");
		str.append("        <div class=\"clearfix\"></div>\n");
		str.append(" 		<div class=\"col-sm-6 m-t-xs no-padding \">\n");
		str.append("	     	<div class=\"col-sm-12 b-r-md p-xxs bg-info text-center\"><strong>A.M.</strong></div>\n");
		str.append(				WebUtil.getTable("table table-striped text-center m-t-xs", new String[] {"IN", "OUT"}, getFaceLogListStrArr(getFaceLogListAM(faceLogList)), new String[] {"text-center", "text-center"}, 2));
		str.append("	     </div>\n");
		str.append("	     <div class=\"col-sm-6 m-t-xs no-padding\">\n");
		str.append("	    	<div class=\"col-sm-12 b-r-md p-xxs bg-warning text-center\"><strong>P.M.</strong></div>\n");
		str.append(				WebUtil.getTable("table table-striped text-center m-t-xs", new String[] {"IN", "OUT"}, getFaceLogListStrArr(getFaceLogListPM(faceLogList)), new String[] {"text-center", "text-center"}, 2));
		str.append("	     </div>\n");
		str.append("        <div class=\"clearfix\"></div>\n");
		str.append("   </div>\n");
		return str.toString();
	}
	
	public static void setPaginationRecord(Pagination pagination, List<DTOBase> faceLogList, Report report) {
		for(DTOBase dtoObj: pagination.getCurrentPageRecordList()) {
			UserRFIDDTO userRFID = (UserRFIDDTO) dtoObj;
			String paginationContent = "";
			paginationContent = getPaginationRecord(userRFID, getFaceLogListByRFID(faceLogList, userRFID.getRfid()), report);
			userRFID.setPaginationRecord(new String[]{paginationContent});
		}
	}
	
	private static String getPaginationRecord(UserRFIDDTO userRFID, List<DTOBase> faceLogList, Report report) {
		StringBuffer str = new StringBuffer();
		str.append("    <div class=\"contact-box no-padding\">\n");
		str.append("        <div class=\"col-sm-4\">\n");
		str.append("            <div class=\"text-center\">\n");
		str.append("               <img alt=\"image\" class=\"m-t-xs img-responsive\" src=\" " + userRFID.getProfilePict() + "\">\n");
		str.append("            </div>\n");
		str.append("        </div>\n");
		str.append("        <div class=\"col-sm-8\">\n");
		str.append("            <h3><strong>" + StringUtil.getFullName(userRFID.getLastName(), userRFID.getFirstName(), userRFID.getMiddleName(), userRFID.getSuffixName(), false, true) + "</strong></h3>\n");
		str.append("            <p><i class=\"fa fa-id-card-o\"></i> &nbsp;" + userRFID.getCode() + "<br>\n");
		str.append("        </div>\n");
		str.append("        <div class=\"clearfix\"></div>\n");
		str.append(" 		<div class=\"col-sm-4 no-padding \"></div>\n");
		str.append(" 		<div class=\"col-sm-4 no-padding \">\n");
		str.append("	     	<div class=\"col-sm-12 b-r-md p-xxs bg-info text-center\"><strong>A.M.</strong></div>\n");
		str.append(" 			<div class=\"col-sm-6 text-center no-padding\">IN</div>\n");
		str.append(" 			<div class=\"col-sm-6 text-center no-padding\">OUT</div>\n");
		str.append("	     </div>\n");
		str.append("	     <div class=\"col-sm-4 no-padding\">\n");
		str.append("	    	<div class=\"col-sm-12 b-r-md p-xxs bg-warning text-center\"><strong>P.M.</strong></div>\n");
		str.append(" 			<div class=\"col-sm-6 text-center no-padding\">IN</div>\n");
		str.append(" 			<div class=\"col-sm-6 text-center no-padding\">OUT</div>\n");
		str.append("	     </div>\n");
		str.append("        <div class=\"clearfix\"></div>\n");
		int days = (DateTimeUtil.getNumberOfDays(report.getDateFrom(), report.getDateTo())) + 1;
		Date date = report.getDateFrom();
		for(int i=1; i<=days; i++) {
			List<DTOBase> faceLogListByDate = getFaceLogListByDate(faceLogList, date);
			if(faceLogListByDate.size() >= 1) {
				str.append("        <div class='col-sm-4'><small>" + DateTimeUtil.getDateTimeToStr(date, "MM/dd/yyyy") +"</small></div>");
				str.append(" 		<div class=\"col-sm-4 no-padding \">\n");
				str.append(				WebUtil.getTableDiv(getFaceLogListStrArr(getFaceLogListAM(faceLogListByDate)), 2));
				str.append("		</div>\n");
				str.append("		<div class=\"col-sm-4 m-t-xs no-padding\">\n");
				str.append(				WebUtil.getTableDiv(getFaceLogListStrArr(getFaceLogListPM(faceLogListByDate)), 2));	
				str.append("		</div>\n");
			}
			date = DateTimeUtil.addDay(date, 1);
		}
		str.append("   </div>\n");
		return str.toString();
	}
	
	public static void main(String[] a) {
		Date date = DateTimeUtil.getStrToDateTime("1970-01-01 11:59", "yyyy-MM-dd kk:mm");
		Date fromDate = DateTimeUtil.getStrToDateTime("1970-01-01 00:00", "yyyy-MM-dd kk:mm");
		Date toDate = DateTimeUtil.getStrToDateTime("1970-01-01 11:59", "yyyy-MM-dd kk:mm");
		System.out.println(DateTimeUtil.isTimeWithin(date, fromDate, toDate));
	}
}
