package com.laponhcet.action.fee;

import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dao.FeeStudentSpecificDAO;
import com.laponhcet.dao.SemesterDAO;
import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.dto.FeeStudentSpecificDTO;
import com.laponhcet.dto.SemesterDTO;
import com.laponhcet.dto.StudentDTO;
import com.laponhcet.util.AcademicYearUtil;
import com.laponhcet.util.SemesterUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;

public class SelectStudentStudentSpecificFeeSubmitAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setCurrentLink(sessionInfo.getUpdateLink());
	}
	
	protected void executeLogic() {
		Pagination pagination = (Pagination) getSessionAttribute(StudentDTO.SESSION_STUDENT_PAGINATION);
		List<DTOBase> feeStudentSpecificList = (List<DTOBase>) getSessionAttribute(FeeStudentSpecificDTO.SESSION_FEESTUDENTSPECIFIC_LIST);
		List<DTOBase> academicYearList = (List<DTOBase>) getSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_LIST);
				
		FeeStudentSpecificDTO feeStudentSpecific = (FeeStudentSpecificDTO) getSessionAttribute(FeeStudentSpecificDTO.SESSION_FEESTUDENTSPECIFIC);
		StudentDTO student = (StudentDTO) getSelectedPaginationObjById(StudentDTO.SESSION_STUDENT_PAGINATION, getRequestInt("txtSelectedRecord"));
		feeStudentSpecific.setStudent(student);
		
		AcademicYearDTO academicYear = AcademicYearUtil.getAcademicYearCurrentByAcademicProgramCode(academicYearList, feeStudentSpecific.getStudent().getAcademicProgram().getCode()); 
		feeStudentSpecific.setAcademicYear(academicYear);
		List<DTOBase> semesterList = new SemesterDAO().getSemesterListByAcademicYearCode(academicYear.getCode());
		if(semesterList.size() >= 1) {
			feeStudentSpecific.setAcademicYear(new AcademicYearDTO());
			feeStudentSpecific.setSemester(SemesterUtil.getSemesterCurrent(semesterList));
			feeStudentSpecificList = new FeeStudentSpecificDAO().getFeeStudentSpecificListByStudentCodeSemesterCode(feeStudentSpecific.getStudent().getCode(), feeStudentSpecific.getSemester().getCode());
		}
		else {
			feeStudentSpecific.setSemester(new SemesterDTO());
			feeStudentSpecific.setAcademicYear(AcademicYearUtil.getAcademicYearCurrent(academicYearList));
			feeStudentSpecificList = new FeeStudentSpecificDAO().getFeeStudentSpecificListByStudentCodeAcademicYearCode(feeStudentSpecific.getStudent().getCode(), feeStudentSpecific.getAcademicYear().getCode());
		}
		//reset pagination record list
		pagination.setRecordList(new ArrayList<DTOBase>());
	}
}
