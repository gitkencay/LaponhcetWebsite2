package com.laponhcet.action.guest.facekeeper.school;

import com.laponhcet.dao.FaceLogDAO;
import com.laponhcet.dao.MessageSMSDAO;
import com.mytechnopal.base.ActionBase;

public class FaceLogAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void init() {
		//delete all face_log record whose timelog is not current date
		new FaceLogDAO().deleteByNotCurrentDate();
		//delete all sms_outbox record whose is_sent is true
		new MessageSMSDAO().deleteByIsSent(true);
	}
}
