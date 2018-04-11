package com.laponhcet.action.student;

import java.util.List;
import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.SchoolDAO;
import com.laponhcet.dao.StudentDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.SchoolDTO;
import com.laponhcet.dto.StudentDTO;
import com.laponhcet.util.StudentUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.CityDAO;
import com.mytechnopal.dao.ReligionDAO;
import com.mytechnopal.dto.CityDTO;
import com.mytechnopal.dto.ReligionDTO;

public class ListStudentAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0095", "US0057", "US0062"}, new String[] {"US0058", "US0063", "US0065"}, new String[] {"US0059", "US0064", "US0066"}, "US0060", "US0061");
		Pagination pagination = null;
		if(!sessionInfo.isPreviousLinkView() && !sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit()) {
			pagination = new Pagination();
			List<DTOBase> studentList = new StudentDAO().getStudentList();
			pagination.setName(StudentDTO.SESSION_STUDENT_PAGINATION);
			pagination.setSearchCriteria(StudentDTO.PAGINATION_SEARCH_CRITERIA_LIST[0]);
			/*pagination.setColumnNameList(new String[] {"Profile Pict", "Program", "ID", "Last Name","First Name", "Middle Name", ""});
			pagination.setColumnWidthList(new String[] {"10", "10", "10","18" ,"18", "17", "17"});	*/
			pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
			pagination.setRecordListUnfiltered(studentList);
			pagination.setRecordList(studentList);
			//pagination.setAjaxResultDetailsList(new String[] {"profile_pict", "program", "code", "last_name", "first_name", "middle_name", Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON});
			pagination.setAjaxResultDetailsList(new String[] {"info", Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON});

			setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST, new AcademicProgramDAO().getAcademicProgramList());
			setSessionAttribute(CityDTO.SESSION_CITY_LIST, new CityDAO().getCityList());
			setSessionAttribute(ReligionDTO.SESSION_RELIGION_LIST, new ReligionDAO().getReligionList());
			setSessionAttribute(SchoolDTO.SESSION_SCHOOL_LIST, new SchoolDAO().getSchoolList());
			setSessionAttribute(StudentDTO.SESSION_STUDENT, new StudentDTO());
		}
		else{
			pagination = (Pagination)getSessionAttribute(StudentDTO.SESSION_STUDENT_PAGINATION);
		}
		StudentUtil.setPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST), false);
		setSessionAttribute(StudentDTO.SESSION_STUDENT_PAGINATION, pagination);
	}
}