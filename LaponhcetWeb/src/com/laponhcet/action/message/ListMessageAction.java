package com.laponhcet.action.message;

import java.util.List;

import com.laponhcet.dao.MessageAcademicProgramGroupDAO;
import com.laponhcet.dao.MessageAcademicProgramSubgroupDAO;
import com.laponhcet.dao.MessageCourseDAO;
import com.laponhcet.dao.MessageDAO;
import com.laponhcet.dao.MessageIndividualDAO;
import com.laponhcet.dto.MessageAcademicProgramGroupDTO;
import com.laponhcet.dto.MessageAcademicProgramSubgroupDTO;
import com.laponhcet.dto.MessageCourseDTO;
import com.laponhcet.dto.MessageDTO;
import com.laponhcet.dto.MessageIndividualDTO;
import com.laponhcet.util.MessageUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;

public class ListMessageAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"U00012", "U00007", "U00013"}, new String[] {"U00008", "U00014", "U00016"}, new String[] {"U00009", "U00015", "U00017"}, "U00010", "U00011");
		Pagination pagination = null;
		if(!sessionInfo.isPreviousLinkView() && !sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit()) {
			pagination = new Pagination();
			List<DTOBase> messageList = new MessageDAO().getMessageList();
			pagination.setName(MessageDTO.SESSION_MESSAGE_PAGINATION);
			pagination.setSearchCriteria(MessageDTO.PAGINATION_SEARCH_CRITERIA_LIST[0]);
			pagination.setColumnNameList(new String[] {"Code","Type", "Message"});
			pagination.setColumnWidthList(new String[] {"17","18","65"});
			pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
			pagination.setRecordListUnfiltered(messageList);
			pagination.setRecordList(messageList);
			pagination.setAjaxResultDetailsList(new String[] {"code", "type", "message"});

			setSessionAttribute(MessageIndividualDTO.SESSION_MESSAGE_INDIVIDUAL_LIST, new MessageIndividualDAO().getMessageIndividualList());
			setSessionAttribute(MessageAcademicProgramGroupDTO.SESSION_MESSAGE_ACADEMIC_PROGRAM_GROUP_LIST, new MessageAcademicProgramGroupDAO().getMessageAcademicProgramGroupList());
			setSessionAttribute(MessageAcademicProgramSubgroupDTO.SESSION_MESSAGE_ACADEMIC_PROGRAM_SUBGROUP_LIST, new MessageAcademicProgramSubgroupDAO().getMessageAcademicProgramSubgroupList());
			setSessionAttribute(MessageCourseDTO.SESSION_MESSAGE_COURSE_LIST, new MessageCourseDAO().getMessageCourseList());
			setSessionAttribute(MessageDTO.SESSION_MESSAGE, new MessageDTO());
		}
		else{
			pagination = (Pagination) getSessionAttribute(MessageDTO.SESSION_MESSAGE_PAGINATION);
		}
		MessageUtil.setPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(MessageIndividualDTO.SESSION_MESSAGE_INDIVIDUAL_LIST), (List<DTOBase>) getSessionAttribute(MessageAcademicProgramGroupDTO.SESSION_MESSAGE_ACADEMIC_PROGRAM_GROUP_LIST), (List<DTOBase>) getSessionAttribute(MessageAcademicProgramSubgroupDTO.SESSION_MESSAGE_ACADEMIC_PROGRAM_SUBGROUP_LIST), (List<DTOBase>) getSessionAttribute(MessageCourseDTO.SESSION_MESSAGE_COURSE_LIST));
		setSessionAttribute(MessageDTO.SESSION_MESSAGE_PAGINATION, pagination);
	}
	
}
