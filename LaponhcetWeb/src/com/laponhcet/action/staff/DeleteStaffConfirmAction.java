package com.laponhcet.action.staff;

import java.util.List;

import com.laponhcet.dao.StaffDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.StaffDTO;
import com.laponhcet.util.StaffUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class DeleteStaffConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void validateInput() {
		validateTrans();
	}
	protected void setSessionVars() {
		 if(actionResponse.isSuccess()) {
		Pagination pagination = (Pagination) getSessionAttribute(StaffDTO.SESSION_STAFF_PAGINATION);
		pagination.updateList((StaffDTO) getSessionAttribute(StaffDTO.SESSION_STAFF), DAOBase.DAO_ACTION_DELETE);
		StaffUtil.setPaginationRecord(sessionInfo, pagination);
	 }
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	protected void executeLogic() {
		execute(StaffDTO.SESSION_STAFF, new StaffDAO(), DAOBase.DAO_ACTION_DELETE);
	}
}
