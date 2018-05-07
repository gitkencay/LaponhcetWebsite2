package com.laponhcet.action.student;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.SchoolDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.SchoolDTO;
import com.laponhcet.dto.StudentDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.dao.CityDAO;
import com.mytechnopal.dao.ReligionDAO;
import com.mytechnopal.dto.CityDTO;
import com.mytechnopal.dto.ReligionDTO;

public class AddStudentAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0095", "US0057", "US0062"}, new String[] {"US0058", "US0063", "US0065"}, new String[] {"US0059", "US0064", "US0066"}, "US0060", "US0061");
		if(!sessionInfo.isPreviousLinkAddSubmit()) {
			setSessionAttribute(CityDTO.SESSION_CITY_LIST, new CityDAO().getCityList());
			setSessionAttribute(ReligionDTO.SESSION_RELIGION_LIST, new ReligionDAO().getReligionList());
			setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST, new AcademicProgramDAO().getAcademicProgramList());
			setSessionAttribute(SchoolDTO.SESSION_SCHOOL_LIST, new SchoolDAO().getSchoolList());
			setSessionBeforeTrans(StudentDTO.SESSION_STUDENT, new StudentDTO());
		}
	}
}