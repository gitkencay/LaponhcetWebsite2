package com.laponhcet.action.staff;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.StaffDTO;
import com.laponhcet.util.StaffUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class UpdateStaffAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		if (!sessionInfo.isPreviousLinkUpdateSubmit()) {
			Pagination pagination = (Pagination) getSessionAttribute(StaffDTO.SESSION_STAFF_PAGINATION);
			StaffDTO staff = StaffUtil.getStaff(DTOUtil.getObjById(pagination.getCurrentPageRecordList(), getRequestInt("txtSelectedRecord")));

			setSessionAttribute(StaffDTO.SESSION_STAFF + "_ORIG", staff);
			setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST, new AcademicProgramDAO().getAcademicProgramList());
			setSessionBeforeTrans(StaffDTO.SESSION_STAFF, staff.getStaff());
		}
	}
}


