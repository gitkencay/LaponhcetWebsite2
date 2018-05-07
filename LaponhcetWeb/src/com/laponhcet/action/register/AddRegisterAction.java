package com.laponhcet.action.register;

import com.laponhcet.dto.RegisterDTO;
import com.mytechnopal.base.ActionBase;

public class AddRegisterAction extends ActionBase {

	private static final long serialVersionUID = 1L;

	protected void setSessionVars(){
		sessionInfo.setTransitionLink(new String[] {"UE0012", "UE0002", "UE0007"}, new String[] {"UE0003", "UE0008", "UE0010"}, new String[] {"UE0004", "UE0009", "UE0011"}, "UE0005", "UE0006");
		if((!sessionInfo.isPreviousLinkAddSubmit())) {
			setSessionBeforeTrans(RegisterDTO.SESSION_REGISTER, new RegisterDTO());
		}
	}
}
