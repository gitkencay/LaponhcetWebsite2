package com.laponhcet.action.message;

import com.laponhcet.dao.MessageDAO;
import com.laponhcet.dto.MessageDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;

public class AddMessageConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
	}
	
	protected void executeLogic() {
		execute(MessageDTO.SESSION_MESSAGE, new MessageDAO(), DAOBase.DAO_ACTION_ADD);
	}
}
