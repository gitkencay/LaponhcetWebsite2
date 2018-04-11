package com.laponhcet.util;

import java.util.Date;
import java.util.List;

import com.laponhcet.dao.AcademicYearDAO;
import com.laponhcet.dao.SemesterDAO;
import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.dto.SemesterDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.base.DataAndSessionBase;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.DateTimeUtil;

public class SemesterUtil extends DataAndSessionBase {
	private static final long serialVersionUID = 1L;
	
	public static void setPaginationRecord(SessionInfo sessionInfo, Pagination pagination, List<DTOBase> academicYearList) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			SemesterDTO semester = (SemesterDTO) dto;
			AcademicYearDTO academicYear =  (AcademicYearDTO) DTOUtil.getObjByCode(academicYearList, semester.getAcademicYear().getCode());
			semester.setPaginationRecord(new String[]{academicYear.getDisplayText(), semester.SEMESTER_DESCRIPTION_LIST[semester.getName()], DateTimeUtil.getDateTimeToStr(semester.getDateStart(), "MM-dd-yyyy"), DateTimeUtil.getDateTimeToStr(semester.getDateEnd(), "MM-dd-yyyy"), pagination.getLinkButtonStr(sessionInfo, semester.getId()).replace("~", ",")});
		}
	}
	
	public static List<DTOBase> getSemesterListByAcademicYear(List<DTOBase> semesterList, List<DTOBase> academicYearList) {
		for(DTOBase semesterObj: semesterList) {
			SemesterDTO semester = (SemesterDTO) semesterObj;
			semester.setAcademicYear((AcademicYearDTO) DTOUtil.getObjByCode(academicYearList, semester.getAcademicYear().getCode()));
			semester.setDisplayText(semester.getDescription1());
		}
		return semesterList;
	}
	
	public static SemesterDTO getSemesterCurrent(List<DTOBase> semesterList) {
		for(DTOBase semesterObj: semesterList) {
			SemesterDTO semester = (SemesterDTO) semesterObj;
			Date dateTimeCurrent = DateTimeUtil.getStrToDateTime(DateTimeUtil.getDateTimeToStr(DateTimeUtil.getCurrentTimestamp(), "MM/dd/yyyy") + " 00:00", "MM/dd/yyyy kk:mm");
			if(DateTimeUtil.isDateTimeWithin(dateTimeCurrent, semester.getDateStart(), semester.getDateEnd())) {
				return semester;
			}
		}
		return null;
	}
	
	public static SemesterDTO getSemesterCurrent() {
		for(DTOBase semesterObj: new SemesterDAO().getSemesterList()) {
			SemesterDTO semester = (SemesterDTO) semesterObj;
			Date dateTimeCurrent = DateTimeUtil.getStrToDateTime(DateTimeUtil.getDateTimeToStr(DateTimeUtil.getCurrentTimestamp(), "MM/dd/yyyy") + " 00:00", "MM/dd/yyyy kk:mm");
			if(DateTimeUtil.isDateTimeWithin(dateTimeCurrent, semester.getDateStart(), semester.getDateEnd())) {
				semester.setAcademicYear(new AcademicYearDAO().getAcademicYearByCode(semester.getAcademicYear().getCode()));
				return semester;
			}
		}
		return null;
	}
}
