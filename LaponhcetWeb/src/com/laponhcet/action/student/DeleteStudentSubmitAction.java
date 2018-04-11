package com.laponhcet.action.student;

import com.laponhcet.dto.StudentDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.dao.UpdateKeyDAO;

public class DeleteStudentSubmitAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		StudentDTO student = (StudentDTO) getSelectedPaginationObjById(StudentDTO.SESSION_STUDENT_PAGINATION, getRequestInt("txtSelectedRecord"));
		student.setUpdateKey(new UpdateKeyDAO().getUpdateKeyByUserCode(student.getCode()));
		setSessionBeforeTrans(StudentDTO.SESSION_STUDENT, student);
	}
}
