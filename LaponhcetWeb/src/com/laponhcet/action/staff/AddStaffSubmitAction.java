package com.laponhcet.action.staff;

public class AddStaffSubmitAction extends StaffAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
}
