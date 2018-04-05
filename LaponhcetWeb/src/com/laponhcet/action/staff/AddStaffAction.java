package com.laponhcet.action.staff;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.StaffDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.dao.CityDAO;
import com.mytechnopal.dao.ReligionDAO;
import com.mytechnopal.dto.CityDTO;
import com.mytechnopal.dto.ReligionDTO;

public class AddStaffAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0097", "US0079", "US0084" }, new String[] { "US0080", "US0085", "US0087" }, new String[] { "US0081", "US0086", "US0088" }, "US0082", "US0083");
		if(!sessionInfo.isPreviousLinkAddSubmit()) {
			setSessionAttribute(CityDTO.SESSION_CITY_LIST, new CityDAO().getCityList());
			setSessionAttribute(ReligionDTO.SESSION_RELIGION_LIST, new ReligionDAO().getReligionList());
			setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST, new AcademicProgramDAO().getAcademicProgramList());
			setSessionBeforeTrans(StaffDTO.SESSION_STAFF, new StaffDTO());
		}
		
	}

}
