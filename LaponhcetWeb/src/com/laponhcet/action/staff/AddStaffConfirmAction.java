package com.laponhcet.action.staff;

import com.laponhcet.dao.StaffDAO;
import com.laponhcet.dto.StaffDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;

public class AddStaffConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
	}
	protected void executeLogic() {
		execute(StaffDTO.SESSION_STAFF, new StaffDAO(), DAOBase.DAO_ACTION_ADD);
	}
}
