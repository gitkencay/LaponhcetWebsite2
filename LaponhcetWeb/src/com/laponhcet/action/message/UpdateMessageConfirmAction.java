package com.laponhcet.action.message;

import java.util.List;

import com.laponhcet.dao.MessageDAO;
import com.laponhcet.dto.MessageAcademicProgramGroupDTO;
import com.laponhcet.dto.MessageAcademicProgramSubgroupDTO;
import com.laponhcet.dto.MessageCourseDTO;
import com.laponhcet.dto.MessageDTO;
import com.laponhcet.dto.MessageIndividualDTO;
import com.laponhcet.util.MessageUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class UpdateMessageConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void init() {
		validateTrans();
	}
	
	@SuppressWarnings("unchecked")
	protected void setSessionVars() {
		if(actionResponse.isSuccess()) {
			Pagination pagination = (Pagination) getSessionAttribute(MessageDTO.SESSION_MESSAGE_PAGINATION);
			pagination.updateList((MessageDTO) getSessionAttribute(MessageDTO.SESSION_MESSAGE), DAOBase.DAO_ACTION_UPDATE);
			List<DTOBase> messageIndividualList = (List<DTOBase>) getSessionAttribute(MessageIndividualDTO.SESSION_MESSAGE_INDIVIDUAL_LIST);
			List<DTOBase> messageAcademicProgramGroupList = (List<DTOBase>) getSessionAttribute(MessageAcademicProgramGroupDTO.SESSION_MESSAGE_ACADEMIC_PROGRAM_GROUP_LIST);
			List<DTOBase> messageAcademicProgramSubgroupList = (List<DTOBase>) getSessionAttribute(MessageAcademicProgramSubgroupDTO.SESSION_MESSAGE_ACADEMIC_PROGRAM_SUBGROUP_LIST);
			List<DTOBase> messageCourseList = (List<DTOBase>) getSessionAttribute(MessageCourseDTO.SESSION_MESSAGE_COURSE_LIST);
			MessageUtil.setPaginationRecord(sessionInfo, pagination, messageIndividualList, messageAcademicProgramGroupList, messageAcademicProgramSubgroupList, messageCourseList);
		}
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	
	protected void executeLogic() {
		execute(MessageDTO.SESSION_MESSAGE, new MessageDAO(), DAOBase.DAO_ACTION_UPDATE);
	}
}
