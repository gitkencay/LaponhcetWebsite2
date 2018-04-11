package com.laponhcet.action.fee;


import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.FeeDAO;
import com.laponhcet.dao.StudentDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.FeeDTO;
import com.laponhcet.dto.FeeStudentSpecificDTO;
import com.laponhcet.dto.StudentDTO;
import com.laponhcet.util.FeeUtil;
import com.laponhcet.util.StudentUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;

public class UpdateStudentSpecificFeeAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"", "", "US0145"}, new String[] {"", "US0146", ""}, new String[] {"", "US0147", ""}, "", "US0148");	
		Pagination pagination = null;
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			pagination = new Pagination();
			List<DTOBase> studentList = new StudentDAO().getStudentList();
			pagination.setName(StudentDTO.SESSION_STUDENT_PAGINATION);
			pagination.setSearchCriteria(StudentDTO.PAGINATION_SEARCH_CRITERIA_LIST[0]);
			pagination.setColumnNameList(new String[] {"ID", "Last Name","First Name", "MI", "Program", ""});
			pagination.setColumnWidthList(new String[] {"8","25" ,"25", "5", "32", "5"});	
			pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
			pagination.setRecordListUnfiltered(studentList);
			pagination.setRecordList(studentList);
			pagination.setAjaxResultDetailsList(new String[] {"id", "last_name", "first_name", "middle_initial", "academic_program", Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON});
			
			setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST, new AcademicProgramDAO().getAcademicProgramList());
			setSessionAttribute(StudentDTO.SESSION_STUDENT_PAGINATION, pagination);
			setSessionAttribute(FeeDTO.SESSION_FEE_LIST, FeeUtil.getFeeListChildren(new FeeDAO().getFeeList()));
			setSessionAttribute(FeeStudentSpecificDTO.SESSION_FEESTUDENTSPECIFIC_LIST, new ArrayList<DTOBase>());
			setSessionBeforeTrans(FeeStudentSpecificDTO.SESSION_FEESTUDENTSPECIFIC, new FeeStudentSpecificDTO());
		}
		else {
			pagination = (Pagination) getSessionAttribute(StudentDTO.SESSION_STUDENT_PAGINATION);
		}
		StudentUtil.setPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST), true);
	}
}