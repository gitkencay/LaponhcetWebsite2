package com.laponhcet.action.semester;

import java.util.List;

import com.laponhcet.dao.SemesterDAO;
import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.dto.SemesterDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.DateTimeUtil;

public class SemesterAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setInput() {
		SemesterDTO semester = (SemesterDTO) getSessionAttribute(SemesterDTO.SESSION_SEMESTER);
		semester.setAcademicYear((AcademicYearDTO) DTOUtil.getObjById((List<DTOBase>) getSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_LIST), getRequestInt("cboAcademicYear")));
		semester.setName(getRequestInt("cboName"));
		semester.setDateStart(DateTimeUtil.getStrToDateTime(getRequestString("txtDateStart"), "MM/dd/yyyy"));
		semester.setDateEnd(DateTimeUtil.getStrToDateTime(getRequestString("txtDateEnd"), "MM/dd/yyyy"));
	}
	
	protected void validateInput() {
		if(!sessionInfo.isCurrentLinkDeleteSubmit()){
			SemesterDTO semester = (SemesterDTO) getSessionAttribute(SemesterDTO.SESSION_SEMESTER);
			List<DTOBase> academicYearList = (List<DTOBase>) getSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_LIST);
			AcademicYearDTO academicYear =  (AcademicYearDTO) DTOUtil.getObjByCode(academicYearList, semester.getAcademicYear().getCode());
			
			if(!DateTimeUtil.isDateTimeWithin(semester.getDateStart(), academicYear.getDateStart(), academicYear.getDateEnd())) {
				actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Starting Date should be between " + academicYear.getDateStart() + " And " + academicYear.getDateEnd());
			}
			else if(!DateTimeUtil.isDateTimeWithin(semester.getDateEnd(), semester.getDateStart(), academicYear.getDateEnd())) {
				actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Ending Date should be between " + DateTimeUtil.getDateTimeToStr(semester.getDateStart(), "yyyy-MM-dd") + " And " + academicYear.getDateEnd());
			}
			else {
				SemesterDTO semesterExist = new SemesterDAO().getSemesterByAcademicYearCodeName(semester.getAcademicYear().getCode(), semester.getName());
				SemesterDTO semesterExistDates = new SemesterDAO().getSemesterByDateEndDateStart(DateTimeUtil.getDateTimeToStr(semester.getDateStart(), "yyyy-MM-dd"), DateTimeUtil.getDateTimeToStr(semester.getDateEnd(), "yyyy-MM-dd"));
				if(sessionInfo.isPreviousLinkAdd()) {
					if(semesterExist != null) {
						actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Name with the same Academic year");
					}
					else {
						if(semesterExistDates != null) {
							actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "this dates is for " + SemesterDTO.SEMESTER_DESCRIPTION_LIST[semesterExistDates.getName()]);
						}
					}
				}
			}
		}
	}
}
