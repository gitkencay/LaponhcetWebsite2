package com.laponhcet.action.student;

import java.util.List;

import com.laponhcet.dao.StudentDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.StudentDTO;
import com.laponhcet.util.StudentUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class UpdateStudentConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void init() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		if(actionResponse.isSuccess()) {
			Pagination pagination = (Pagination) getSessionAttribute(StudentDTO.SESSION_STUDENT_PAGINATION);
			pagination.updateList((StudentDTO) getSessionAttribute(StudentDTO.SESSION_STUDENT), DAOBase.DAO_ACTION_UPDATE);
			StudentUtil.setPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST), false);
		}
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	
	protected void executeLogic() {
		execute(StudentDTO.SESSION_STUDENT, new StudentDAO(), DAOBase.DAO_ACTION_UPDATE);
	}
}
