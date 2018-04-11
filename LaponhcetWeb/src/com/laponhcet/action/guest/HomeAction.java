package com.laponhcet.action.guest;

import com.laponhcet.dto.RegisterDTO;
import com.mytechnopal.base.ActionBase;

public class HomeAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		//setSessionBeforeTrans(RegisterDTO.SESSION_REGISTER, new RegisterDTO());
	}
}