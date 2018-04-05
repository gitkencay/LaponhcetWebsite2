package com.laponhcet.action.staff;

import com.laponhcet.dto.StaffDTO;
import com.laponhcet.util.StaffUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class DeleteStaffSubmitAction extends StaffAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		// is previous link from the list
if (!sessionInfo.isPreviousLinkUpdateSubmit()) {
		Pagination pagination = (Pagination) getSessionAttribute(StaffDTO.SESSION_STAFF_PAGINATION);
		DTOBase userObj = DTOUtil.getObjById(pagination.getCurrentPageRecordList(), getRequestInt("txtSelectedRecord"));
		StaffDTO staff = StaffUtil.getStaff(userObj);
		setSessionBeforeTrans(StaffDTO.SESSION_STAFF, staff.getStaff());
}
	}
}
